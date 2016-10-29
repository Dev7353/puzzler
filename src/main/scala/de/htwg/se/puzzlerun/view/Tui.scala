package de.htwg.se.puzzlerun.view

import de.htwg.se.puzzlerun.model.Cell

class Tui{
  def draw(field: Array[Array[Cell]]){
   for(i <- field.indices){
     for(j <- field(0).indices){
      print(field(i)(j) + "\t")
     }
     println()
   }

    print(" ")
  }
}


