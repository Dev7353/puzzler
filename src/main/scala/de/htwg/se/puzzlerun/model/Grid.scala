/**
  * Created by kimeng on 21.10.2016.
  */
package de.htwg.se.puzzlerun.model

class Grid(length: Int, height: Int) {

  val grid = Array.ofDim(lenght, height)

  def get_cell(x: Int, y: Int): Cell ={
    return grid[x][y]
  }

}
