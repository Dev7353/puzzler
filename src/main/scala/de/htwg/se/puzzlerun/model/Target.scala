package de.htwg.se.puzzlerun.model
import de.htwg.se.puzzlerun.model.Cell

/**
 * Created by kmg on 28.10.16.
 */

case class Target(x: Int, y: Int) extends Cell {

  val coordinate = (x, y)

  override def toString: String = "X"
}