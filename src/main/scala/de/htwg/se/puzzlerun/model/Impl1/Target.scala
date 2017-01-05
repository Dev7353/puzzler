package de.htwg.se.puzzlerun.model.Impl1

case class Target(x: Int, y: Int) extends Cell {

  val coordinate = (x, y)

  override def toString: String = "X"
}