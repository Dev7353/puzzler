/**
  * Created by kimeng on 21.10.2016.
  */
package de.htwg.se.puzzlerun.model

case class Player(x: Int, y: Int) extends Cell{

  var coordinate = (x, y)

  override def toString: String = "p"

}
