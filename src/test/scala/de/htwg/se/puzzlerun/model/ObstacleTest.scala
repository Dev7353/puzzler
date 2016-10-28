package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 25.10.2016.
  */
class ObstacleTest extends FlatSpec with Matchers {
  val moveArray = new Array[Move](3)
  "An obstacle" should "have coordinates and allowed moves" in {
    val obstacle = new Obstacle(19, 5, moveArray)
    obstacle.x should be(19)
    obstacle.y should be(5)
    obstacle.allowed_moves should be("None")
  }
}
