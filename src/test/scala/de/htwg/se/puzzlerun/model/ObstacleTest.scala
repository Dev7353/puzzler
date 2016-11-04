package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 25.10.2016.
  */
class ObstacleTest extends WordSpec with Matchers {

  "An obstacle" should {

    val obstacle = Obstacle(19, 5)

    "have coordinates 19 and 5" in {
      obstacle.x should be(19)
      obstacle.y should be(5)
    }
    "and should print o" in {
      obstacle.toString should be("o")
    }
  }
}
