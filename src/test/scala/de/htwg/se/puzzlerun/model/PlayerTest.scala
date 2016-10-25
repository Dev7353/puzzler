package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 21.10.2016.
  */
class PlayerTest extends FlatSpec with Matchers {

  "A player" should "have coordinates" in {
    Player(0, 0).x should be(0)
    Player(0, 0).y should be(0)
  }

}
