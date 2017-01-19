package de.htwg.se.puzzlerun.controller

import de.htwg.se.puzzlerun.controller.Impl1.Controller
import de.htwg.se.puzzlerun.model._
import org.scalatest._

import scala.collection.mutable
import scala.io.Source

/**
 * Created by kmg on 18.11.16.
 */
class ControllerTest extends WordSpec with Matchers {
  var controller = new Controller("level00.json")

  "CheckCell" should {

    "set state to 'Obstacle reached'" in {

      controller.checkCell(1, 1)
      assert(controller.state.equals("Obstacle reached"))

      controller.checkCell(5, 5)
      assert(controller.state.equals("Obstacle reached"))

    }

    "set state to 'Target reached'" in {

      controller.checkCell(0, 0)
      assert(controller.state.equals("Target reached"))

    }

    "set state to ''" in {

      controller.checkCell(1, 0)
      assert(controller.state.equals(""))

    }
  }

  "Move" should {

    "set player to coordinate" in {

      controller.move(1, 1);
      assert(controller.player.coordinate == (1, 1))
    }
  }

  "Movements" should {

    "down" in {
      val before = controller.player.coordinate
      controller.down()
      val after = controller.player.coordinate

      val xb = before._1
      val xa = after._1

      val yb = before._2
      val ya = after._2

      assert((yb - ya) == 0)
      assert((xb - xa) == -1)

      assert(controller.moves.exists(_ == "Down" -> 4))

    }

    "up" in {
      val before = controller.player.coordinate
      controller.up()
      val after = controller.player.coordinate

      val xb = before._1
      val xa = after._1

      val yb = before._2
      val ya = after._2

      assert((yb - ya) == 0)
      assert((xb - xa) == 1)

      assert(controller.moves.exists(_ == "Up" -> 4))
    }

    "left" in {
      val before = controller.player.coordinate
      controller.left()
      val after = controller.player.coordinate

      val xb = before._1
      val xa = after._1

      val yb = before._2
      val ya = after._2

      assert((yb - ya) == 1)
      assert((xb - xa) == 0)
      assert(controller.moves.exists(_ == "Left" -> 4))
    }

    "right" in {

      val before = controller.player.coordinate
      controller.right()
      val after = controller.player.coordinate

      val xb = before._1
      val xa = after._1

      val yb = before._2
      val ya = after._2

      assert((xb - xa) == 0)
      assert((yb - ya) == -1)

      assert(controller.moves.exists(_ == "Right" -> 4))

    }
  }

  "Check Moves" should {

    "returns 0" in {

      assert(controller.checkMoves(1) == 0)
      assert(!controller.state.equals("Moves depleted"))
    }

    "returns 0 and set state to 3" in {
      assert(controller.checkMoves(0) == 0)
      assert(controller.state.equals("Moves depleted"))
    }

  }

  "Check Eingabe length" should {

    "checkEingabelength returns true" in {

      assert(controller.checkEingabeLength(0))
    }

    "checkEingabelength returns false" in {
      assert(controller.checkEingabeLength(5) == false)
    }
  }
  "generateJSONLevel" should {
    controller.generateJSONLevel("level00.config")
    "create a JSON file" in {
      def getCurrentDirectory = new java.io.File(".").getCanonicalPath
      val filename: String = getCurrentDirectory + "/src/levels/" + "level00.json"
      assert(Source.fromFile(filename).nonEmpty)
    }
  }

}
