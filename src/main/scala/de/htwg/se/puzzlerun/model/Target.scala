package de.htwg.se.puzzlerun.model

trait ITarget

case class Target(x: Int, y: Int) extends Cell with ITarget{

  val coordinate = (x, y)

  override def toString: String = "X"
}