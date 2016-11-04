package de.htwg.se.puzzlerun.controller

import com.sun.prism.impl.Disposer
import de.htwg.se.puzzlerun.model._

class Controller(grid: Grid, player: Player, obstacles: List[Obstacle], target: Target){
  val grid_lenght = grid.length - 1
  val grid_height = grid.height - 1
  var state = None: Option[Boolean]
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
        defeat()
      } else if (grid.getCell(x, y).isInstanceOf[Target]) {
        victory()
      } else {
        state = Some(true)
      }
    } catch {
      case bound: java.lang.ArrayIndexOutOfBoundsException => defeat()
    }
  }

  def defeat(): Unit = {
    print("Defeat!\n")
    state = Some(false)
  }

  def victory(): Unit = {
    print("Victory!\n")
    state = Some(false)
  }
}