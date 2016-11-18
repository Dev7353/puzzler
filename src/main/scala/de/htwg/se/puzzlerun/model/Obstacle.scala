/**
 * Created by kimeng on 21.10.2016.
 */
package de.htwg.se.puzzlerun.model

case class Obstacle(x: Int, y: Int) extends Cell {
  val coordinate = (x, y)

  override def toString: String = "o"
}
