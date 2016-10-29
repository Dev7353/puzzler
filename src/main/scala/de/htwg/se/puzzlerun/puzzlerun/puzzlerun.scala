/**
  * Created by kmg on 27.10.16.
  */
package de.htwg.se.puzzlerun.puzzlerun
import scala.io.StdIn
import de.htwg.se.puzzlerun.model._
import de.htwg.se.puzzlerun.view.Tui
import de.htwg.se.puzzlerun.controller.Controller

import scala.io.StdIn._

object puzzlerun{

  def gameloop(controller: Controller, tui: Tui): Unit ={

    print("hoch: \tw \n" +
      "runter: \ts\n" +
      "links: \ta\n" +
      "rechts: \td\n")

    while(true){
      tui.draw(controller.getGrid())
      var eingabe = readLine("Eingabe: ").toString.charAt(0)

      eingabe match{
        case 'w' => controller.up()
        case 'a' => controller.left()
        case 's' => controller.down()
        case 'd' => controller.right()
        case _ => print("Falsche Eingabe.")

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

    gameloop(controller, tui)


  }

}

