package de.htwg.se.puzzlerun.controller.Impl1

import java.io.{FileInputStream, InputStream}
import java.util.Properties

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.model.Impl1._

import scala.collection.mutable.Map
import scala.swing.Publisher

class Controller(path: String) extends IController with Publisher {
  var state = ""
  var level = 0
  var grid: Grid = _
  var obstacles = scala.collection.mutable.MutableList[Obstacle]()
  var target: Target = _
  var player: Player = _
  var moves: Map[String, Int] = _
  generate_level(path)
  wrap()

  def wrap(): Unit = {
    val grid = this.grid.createGrid()
    for (i <- grid.indices; j <- grid(0).indices) {

      for (obstacle <- this.obstacles) {

        if ((i, j) == obstacle.coordinate) {
          this.grid.setCell(i, j, obstacle)
        }
      }

      if ((i, j) == target.coordinate) {
        this.grid.setCell(i, j, this.target)
      }

      if ((i, j) == this.player.coordinate) {
        this.grid.setCell(i, j, this.player)
      }

    }
  }

  def move(x: Int, y: Int): Unit = {

    checkCell(x, y)
    this.grid.setCell(player.x, player.y, new Cell)
    player.coordinate = (x, y)
    wrap()
    notifyObservers
  }

  def up(): Unit = {
    /*
    Moves the player one field up.
     */
    val y = this.player.coordinate._2
    val x = this.player.coordinate._1 - 1
    move(x, y)
    val newAmount = checkMoves(moves.get("Up").get)
    moves.put("Up", newAmount)

  }

  def down(): Unit = {
    /*
    Moves the player one field down.
     */
    val y = this.player.coordinate._2
    val x = this.player.coordinate._1 + 1
    move(x, y)
    val newAmount = checkMoves(moves.get("Down").get)
    moves.put("Down", newAmount)
  }

  def right(): Unit = {
    /*
    Moves the player one field to the right.
     */
    val y = this.player.coordinate._2 + 1
    val x = this.player.coordinate._1
    move(x, y)
    val newAmount = checkMoves(moves.get("Right").get)
    moves.put("Right", newAmount)
  }

  def left(): Unit = {
    /*
    Moves the player one field to the left
     */
    val y = this.player.coordinate._2 - 1
    val x = this.player.coordinate._1
    move(x, y)
    val newAmount = checkMoves(moves.get("Left").get)
    moves.put("Left", newAmount)
  }

  def checkCell(x: Int, y: Int): Unit = {
    /*
    Checks whether the cell to be accessed is of type Obstacle or Target.
    Sets state accordingly for the game loop.
     */
    try {
      if (grid.getCell(x, y).isInstanceOf[Obstacle]) {
        state = "Obstacle reached"
      } else if (grid.getCell(x, y).isInstanceOf[Target]) {
        state = "Target reached"
        //generate_level("level01.config")
      }

    } catch {
      case bound: java.lang.ArrayIndexOutOfBoundsException => state = "Obstacle reached"
    }
  }

  def checkMoves(amount: Int): Int = {
    /*
    Checks whether the player has exceeded the moves limit.
     */
    if (amount == 0) state = "Moves depleted"
    var newAmount = amount - 1
    if (newAmount < 0) newAmount = 0 // Keep moves at 0 and don't go negative
    newAmount
  }

  def checkEingabeLength(eingabeLength: Int): Boolean = {

    eingabeLength == 0
  }

  def generate_level(path: String): Unit = {
    var prop: Properties = new Properties()
    def getCurrentDirectory = new java.io.File(".").getCanonicalPath
    val filename: String = getCurrentDirectory + "/src/levels/" + path
    val is: InputStream = new FileInputStream(filename)

    prop.load(is)

    var grid_size = prop.getProperty("grid").split(",")
    this.grid = Grid(grid_size(0).toInt, grid_size(1).toInt)

    val playerCoord = prop.getProperty("player").split(",")
    this.player = Player(playerCoord(0).toInt, playerCoord(1).toInt)

    var target_coord = prop.getProperty("target").split(",")
    this.target = Target(target_coord(0).toInt, target_coord(1).toInt)

    var obstacles_list = prop.getProperty("obstacles").split(" ")
    for (entry <- obstacles_list) {
      var buffer = entry.split(",")
      this.obstacles += Obstacle(buffer(0).toInt, buffer(1).toInt)
    }
    var moves_list = prop.getProperty("moves").split(" ")
    this.moves = Map("Up" -> moves_list(0).toInt, "Down" -> moves_list(1).toInt, "Left" -> moves_list(2).toInt, "Right" -> moves_list(3).toInt)
  }
}