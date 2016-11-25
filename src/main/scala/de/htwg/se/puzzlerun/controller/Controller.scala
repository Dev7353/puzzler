package de.htwg.se.puzzlerun.controller

import java.io.InputStream
import java.util.Properties
import java.io.FileInputStream

import com.sun.prism.impl.Disposer
import de.htwg.se.puzzlerun.model._

import scala.collection.mutable.Map
import de.htwg.se.puzzlerun.util.Observable

import scala.io.Source

class Controller(path: String) extends Observable {
  var state = 0 // 0 = Continue; 1 = Defeat 2 = Victory
  var grid: Grid
  var player: Player
  var target: Target
  var obstacles: List[Obstacle]
  var allowedMoves: Map[String, Int]

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
        state = 1
      } else if (grid.getCell(x, y).isInstanceOf[Target]) {
        state = 2
      } else {
        state = 0
      }
    } catch {
      case bound: java.lang.ArrayIndexOutOfBoundsException => state = 1
    }
  }

  def checkMoves(amount: Int): Int = {
    /*
    Checks whether the player has exceeded the moves limit.
     */
    if (amount == 0) state = 3
    var newAmount = amount - 1
    if (newAmount < 0) newAmount = 0 // Keep moves at 0 and don't go negative
    newAmount
  }

  def checkEingabeLength(eingabeLength: Int): Boolean = {

    eingabeLength == 0
  }

  def generate_level(path: String): Unit={
    var prop: Properties = new Properties()
    val filename: String = "/Users/kmg/projects/puzzlerun/src/levels/level00.config"
    val is: InputStream =  new FileInputStream(filename)

    prop.load(is)

    var grid_size = prop.getProperty("grid").split(",")
    val grid = Grid(grid_size(0).toInt, grid_size(1).toInt)

    var player_coord = prop.getProperty("player").split(",")
    val player = Player(player_coord(0).toInt, player_coord(1).toInt)

    var target_coord = prop.getProperty("target").split(",")
    val target = Target(target_coord(0).toInt, target_coord(1).toInt)


  }
}