/**
  * Created by kimeng on 21.10.2016.
  */
package de.htwg.se.puzzlerun.model

case class Grid(length: Int, height: Int) {
  var grid = Array.ofDim[Cell](length, height)
  def getCell(x: Int, y: Int): Cell ={
    grid(x)(y)
  }

  def setCell(x: Int, y: Int, obj: Cell): Cell ={
    grid(x)(y) = obj
    grid(x)(y)
  }

  def createGrid(): Array[Array[Cell]] = {
    for(i <- grid.indices){
      for(j <- grid(0).indices){
        grid(i)(j) = new Cell()
      }
    }
    grid
  }

  override def toString: String = {

    var output = ""

    val field = grid
    for(i <- field.indices){
      for(j <- field(0).indices){
        output += field(i)(j) + "\t"
      }
      output += "\n"
    }
     output
  }
}
