/**
  * Created by kimeng on 21.10.2016.
  */
package de.htwg.se.puzzlerun.model

case class Obstacle(x: Int, y: Int, move: Array[Move]){
  val coordinate = (x, y)
  val allowed_moves = "None"
}
