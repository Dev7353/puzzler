package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 21.10.2016.
  */
class PlayerTest extends FlatSpec with Matchers {

  "A player" should "have coordinates" in {
    val player = new Player(0,0)
    player.x should be(0)
    player.y should be(0)
  }

}
