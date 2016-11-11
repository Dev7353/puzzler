package de.htwg.se.puzzlerun.controller

import com.sun.prism.impl.Disposer
import de.htwg.se.puzzlerun.model._
import scala.collection.mutable.Map

class Controller(grid: Grid, player: Player, obstacles: List[Obstacle], target: Target, var moves:Map[String, Int]){
  val grid_lenght = grid.length - 1
  val grid_height = grid.height - 1
  var state = 0 // 0 = Continue; 1 = Defeat 2 = Victory
  wrap()

  def wrap(): Unit ={
    val grid = this.grid.createGrid()
    for(i <- grid.indices; j <- grid(0).indices){

      for(obstacle <- this.obstacles){

        if((i, j) == obstacle.coordinate){
          this.grid.setCell(i, j, obstacle)
        }
      }

      if((i, j) == this.player.coordinate){
        this.grid.setCell(i, j, this.player)
      }

      if((i, j) == target.coordinate){
        this.grid.setCell(i, j, this.target)
      }

    }

  }
  def getGrid(): Grid ={
    /*
    Returns the grid.
     */
    this.grid
  }

  def up(): Unit ={
    /*
    Moves the player one field up.
     */
    val y = this.player.coordinate._2
    val x = this.player.coordinate._1 - 1
    checkCell(x, y)
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (x, y)
    wrap()
    checkMoves(moves.get("Up").get)
    val newAmount = moves.get("Up").get - 1
    moves.put("Up", newAmount)
  }

  def down(): Unit ={
    /*
    Moves the player one field down.
     */
    val y = this.player.coordinate._2
    val x = this.player.coordinate._1 + 1
    checkCell(x, y)
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (x, this.player.coordinate._2)
    wrap()
    checkMoves(moves.get("Down").get)
    val newAmount = moves.get("Down").get - 1
    moves.put("Down", newAmount)
  }

  def right(): Unit ={
    /*
    Moves the player one field to the right.
     */
    val y = this.player.coordinate._2 + 1
    val x = this.player.coordinate._1
    checkCell(x, y)
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (this.player.coordinate._1, y)
    wrap()
    checkMoves(moves.get("Right").get)
    val newAmount = moves.get("Right").get - 1
    moves.put("Right", newAmount)
  }

  def left(): Unit ={
    /*
    Moves the player one field to the left
     */
    val y = this.player.coordinate._2 - 1
    val x = this.player.coordinate._1
    checkCell(x, y)
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (this.player.coordinate._1, y)
    wrap()
    checkMoves(moves.get("Left").get)
    val newAmount = moves.get("Left").get - 1
    moves.put("Left", newAmount)
  }

  def checkCell(x: Int, y: Int): Unit ={
    /*
    Checks whether the cell to be accessed is of type Obstacle or Target.
    Sets state accordingly for the game loop.
     */
    try {
      if (grid.getCell(x, y).isInstanceOf[Obstacle]) {
        state = 1
      } else if (grid.getCell(x, y).isInstanceOf[Target]) {
        state = 2
      }
    } catch {
      case bound: java.lang.ArrayIndexOutOfBoundsException => state = 1
    }
  }
  def checkMoves(amount: Int): Unit ={
    if(amount == 0) state = 3
  }
}