package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 01.11.2016.
  */
class TargetTest extends WordSpec with Matchers {
  "A new target" should {
    val newTarget = Target(5, 5)

    "have coordinates" in {
      newTarget.x should equal(5)
      newTarget.y should equal(5)
    }

    "and should print X" in {
      newTarget.toString should be("X")
    }
  }
}
