/**
 * Created by kimeng on 21.10.2016.
 */
package de.htwg.se.puzzlerun.model

trait IObstacle

case class Obstacle(x: Int, y: Int) extends Cell with IObstacle{
  val coordinate = (x, y)

  override def toString: String = "o"
}
