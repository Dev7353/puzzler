package de.htwg.se.puzzlerun.controller

import de.htwg.se.puzzlerun.model.{Grid, Obstacle, Player, Target}
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import scala.collection.mutable

/**
  * Created by kmg on 18.11.16.
  */
class ControllerTest extends WordSpec with Matchers {

  val grid = Grid(4, 4)
  val player = Player(3, 3)
  val target = Target(0, 0)
  val obstacles:List[Obstacle] = List(Obstacle(1,1), Obstacle(2,1),Obstacle(2,0),Obstacle(3,2),Obstacle(1,3))
  val allowedMoves = mutable.Map("Up" -> 5, "Down" -> 5, "Left" -> 5, "Right" -> 5)
  var controller = new Controller(grid, player, obstacles, target, allowedMoves)

  "ControllerTest" should {

    "checkCell" in {


    }

    "move" in {

    }

    "grid" in {

    }

    "grid_$eq" in {

    }

    "down" in {

    }

    "state" in {

    }

    "state_$eq" in {

    }

    "up" in {

    }

    "grid_height" in {

    }

    "left" in {

    }

    "checkMoves" in {

    }

    "wrap" in {

    }

    "grid_lenght" in {

    }

    "right" in {

    }

    "moves" in {

    }

    "moves_$eq" in {

    }

  }
}
