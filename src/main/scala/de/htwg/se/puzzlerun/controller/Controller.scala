package de.htwg.se.puzzlerun.controller

import de.htwg.se.puzzlerun.model._

class Controller(grid: Grid, player: Player, obstacles: List[Obstacle], target: Target){
  wrap()

  def wrap(): Unit ={
    val grid = this.grid.grid
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
  def getGrid(): Array[Array[Cell]] ={

    this.grid.grid
  }

  def up(): Unit ={
    val x = this.player.coordinate._1 - 1
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (x, this.player.coordinate._2)
    wrap()
  }

  def down(): Unit ={
    val x = this.player.coordinate._1 + 1
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (x, this.player.coordinate._2)
    wrap()
  }

  def right(): Unit ={
    val y = this.player.coordinate._2 + 1
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (this.player.coordinate._1, y)
    wrap()
  }

  def left(): Unit ={
    val y = this.player.coordinate._2 - 1
    grid.setCell(this.player.coordinate._1, this.player.coordinate._2, new Cell)
    this.player.coordinate = (this.player.coordinate._1, y)
    wrap()
  }

}