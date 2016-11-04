package de.htwg.se.puzzlerun.controller

import com.sun.prism.impl.Disposer
import de.htwg.se.puzzlerun.model._

class Controller(grid: Grid, player: Player, obstacles: List[Obstacle], target: Target){
  val grid_lenght = grid.length - 1
  val grid_height = grid.height - 1
  var state = 0 // 0 running 1 defeat 2 victory
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
  def get_grid(): Grid ={

    this.grid
  }

  def up(): Unit ={
    val y = this.player.coordinate._2
    val x = this.player.coordinate._1 - 1
    checkCell(x, y)
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (x, y)
    wrap()
  }

  def down(): Unit ={
    val y = this.player.coordinate._2
    val x = this.player.coordinate._1 + 1
    checkCell(x, y)
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (x, this.player.coordinate._2)
    wrap()
  }

  def right(): Unit ={
    val y = this.player.coordinate._2 + 1
    val x = this.player.coordinate._1
    checkCell(x, y)
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (this.player.coordinate._1, y)
    wrap()
  }

  def left(): Unit ={
    val y = this.player.coordinate._2 - 1
    val x = this.player.coordinate._1
    checkCell(x, y)
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (this.player.coordinate._1, y)
    wrap()
  }

  def checkCell(x: Int, y: Int): Unit = {
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
}