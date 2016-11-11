package de.htwg.se.puzzlerun.controller

import de.htwg.se.puzzlerun.model._

import scala.collection.mutable.Map
import de.htwg.se.puzzlerun.util.Observable


class Controller(var grid: Grid, player: Player,
                 obstacles: List[Obstacle], target: Target,
                 var moves:Map[String, Int]) extends Observable {
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

  def move(x: Int, y: Int): Unit ={

    checkCell(x, y)
    this.grid.setCell(player.x, player.y, new Cell)
    player.coordinate = (x, y)
    wrap()
    notifyObservers
  }

  def up(): Unit ={
    /*
    Moves the player one field up.
     */
    val y = this.player.coordinate._2
    val x = this.player.coordinate._1 - 1
    move(x, y)
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
    move(x, y)
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
    move(x, y)
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
    move(x, y)
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
    /*
    Checks whether the player has exceeded the moves limit.
     */
    if(amount == 0) state = 3
  }
}