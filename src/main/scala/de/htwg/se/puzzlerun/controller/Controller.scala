package de.htwg.se.puzzlerun.controller

import de.htwg.se.puzzlerun.model._

case class Controller(grid: Grid, player: Player, obstacles: List[Obstacle], target: Target){

  def getGrid(): Array[Array[Cell]] ={

    this.grid.grid
  }

  def up(): Unit ={

  }

  def down(): Unit ={

  }

  def right(): Unit ={

  }

  def left(): Unit ={
    
  }
}