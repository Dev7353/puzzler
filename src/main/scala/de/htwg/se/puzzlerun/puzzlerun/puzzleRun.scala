/**
  * Created by kmg on 27.10.16.
  */
package de.htwg.se.puzzlerun.puzzlerun
import scala.io.StdIn
import de.htwg.se.puzzlerun.model._
import de.htwg.se.puzzlerun.view.Tui
import de.htwg.se.puzzlerun.controller.Controller
import scala.util.control._

import scala.io.StdIn._

object puzzleRun{

  def gameLoop(controller: Controller, tui: Tui): Unit ={

    print("hoch: \t\t w\n" +
      "runter: \t s\n" +
      "links: \t\t a\n" +
      "rechts: \t d\n")
    print("\n")

    val loop = new Breaks
    loop.breakable {
      while(true) {
        tui.draw(controller.get_grid())
        var eingabe = readLine("Eingabe: ").toCharArray

        for (c <- eingabe) {
          c match {
            case 'w' => controller.up()
            case 'a' => controller.left()
            case 's' => controller.down()
            case 'd' => controller.right()
            case _ => print("Falsche Eingabe.")
          }
          controller.state match {
            case Some(false) => loop.break;
            case Some(true) => tui.draw(controller.get_grid())
          }

          print("____________________________\n")
        }
    }

    }
  }
  def main(args: Array[String]): Unit = {

    val grid = Grid(4, 4)
    val player = Player(3, 3)
    val target = Target(0, 0)
    val obstacles:List[Obstacle] = List(Obstacle(1,1), Obstacle(2,1),Obstacle(2,0),Obstacle(3,2),Obstacle(1,3))
    val tui = new Tui()
    var controller = new Controller(grid, player, obstacles, target )

    gameLoop(controller, tui)


  }

}

