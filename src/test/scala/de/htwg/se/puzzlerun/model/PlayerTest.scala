package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 21.10.2016.
  */
class PlayerTest extends WordSpec with Matchers {

  "A new player" should {
    val player = new Player(0,0)

    "have coordinates" in {
      player.x should be(0)
      player.y should be(0)
    }

    "and should print p" in {
      player.toString should be("p")
    }
  }
}
