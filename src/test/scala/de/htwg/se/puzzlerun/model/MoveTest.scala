package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 25.10.2016.
  */
class MoveTest extends FlatSpec with Matchers {
  "A move" should "have step 1" in {
    var move = new Move()
    move.step should be(1)
  }
}
