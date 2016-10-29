package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 25.10.2016.
  */
class ObstacleTest extends FlatSpec with Matchers {

  "An obstacle" should "have coordinates and allowed moves" in {
    val obstacle = Obstacle(19, 5)
    obstacle.x should be(19)
    obstacle.y should be(5)
  }
}
