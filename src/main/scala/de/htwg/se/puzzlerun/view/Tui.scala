package de.htwg.se.puzzlerun.view

import de.htwg.se.puzzlerun.model.Grid

class Tui{
  def draw(grid: Grid){
    val field = grid.grid
   for(i <- field.indices){
     for(j <- field(0).indices){
      print(field(i)(j) + "\t")
     }
     println()
   }

    print("\n")
  }
}


