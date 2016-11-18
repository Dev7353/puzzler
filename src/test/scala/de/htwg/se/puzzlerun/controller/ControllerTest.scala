package de.htwg.se.puzzlerun.controller

import de.htwg.se.puzzlerun.model.{ Grid, Obstacle, Player, Target }
import org.scalatest._
import scala.collection.mutable

/**
 * Created by kmg on 18.11.16.
 */
class ControllerTest extends WordSpec with Matchers {

  val grid = Grid(4, 4)
  val player = Player(3, 3)
  val target = Target(0, 0)
  val obstacles: List[Obstacle] = List(Obstacle(1, 1), Obstacle(2, 1), Obstacle(2, 0), Obstacle(3, 2), Obstacle(1, 3))
  val allowedMoves = mutable.Map("Up" -> 5, "Down" -> 5, "Left" -> 5, "Right" -> 5)
  var controller = new Controller(grid, player, obstacles, target, allowedMoves)

  "CheckCell" should {

    "set state to 1" in {

      controller.checkCell(1, 1)
      assert(controller.state == 1)

      controller.checkCell(5, 5)
      assert(controller.state == 1)

    }

    "set state to 2" in {

      controller.checkCell(0, 0)
      assert(controller.state == 2)

    }

    "set state to 0" in {

      controller.checkCell(1, 0)
      assert(controller.state == 0)

    }
  }

  "Move" should {

    "set player to coordinate" in {

      controller.move(1, 0);
      assert(controller.player.coordinate == (1, 0))
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
      assert(controller.state != 3)
    }

    "returns 0 and set state to 3" in {
      assert(controller.checkMoves(0) == 0)
      assert(controller.state == 3)
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

}
