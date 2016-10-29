/**
  * Created by kimeng on 21.10.2016.
  */
package de.htwg.se.puzzlerun.model

case class Grid(length: Int, height: Int) {

  val grid = Array.ofDim[Cell](length, height)

  for(i <- grid.indices){
    for(j <- grid(0).indices){

      grid(i)(j) = new Cell()
    }
  }
  def getCell(x: Int, y: Int): Cell ={
    grid(x)(y)
  }

  def setCell(x: Int, y: Int, obj: Cell): Unit ={

    grid(x)(y) = obj
  }
}
