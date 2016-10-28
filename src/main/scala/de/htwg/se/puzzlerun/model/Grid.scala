/**
  * Created by kimeng on 21.10.2016.
  */
package de.htwg.se.puzzlerun.model

class Grid(length: Int, height: Int) {

  val grid = Array.ofDim[Cell](length, height)

  def get_cell(x: Int, y: Int): Cell ={
    grid(x)(y)
  }

}
