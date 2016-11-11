/**
  * Created by kmg on 27.10.16.
  */
package de.htwg.se.puzzlerun.puzzlerun
import scala.io.StdIn
import de.htwg.se.puzzlerun.model._
import de.htwg.se.puzzlerun.view.Tui
import de.htwg.se.puzzlerun.controller.Controller
import scala.util.control._
import scala.collection.mutable.Map

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
        tui.draw(controller.getGrid())
        var eingabe = readLine("Eingabe: \n").toCharArray
        var eingabeLength = eingabe.length
        for (c <- eingabe) {
          c match {
            case 'w' => controller.up()
            case 'a' => controller.left()
            case 's' => controller.down()
            case 'd' => controller.right()
            case _ => print("Falsche Eingabe.\n")
          }
          eingabeLength -= 1

          controller.state match {
            case 0 =>
              if(eingabeLength == 0) {
                controller.moves.map(key => print(key._1 + "\t" + key._2 + "\n"))
                tui.draw(controller.getGrid())
                print("Du hast verloren! Du bist nicht am Ziel angekommen.\n")
                loop.break()
              }
            case 1 =>
              controller.moves.map(key => print(key._1 + "\t" + key._2 + "\n\n"))
              tui.draw(controller.getGrid())
              print("Du hast verloren! Du bist auf ein Hindernis gestossen.\n")
              loop.break
            case 2 =>
              controller.moves.map(key => print(key._1 + "\t" + key._2 + "\n\n"))
              tui.draw(controller.getGrid())
              print("Du hast gewonnen!\n")
              loop.break
            case 3 =>
              controller.moves.map(key => print(key._1 + "\t" + key._2 + "\n\n"))
              tui.draw(controller.getGrid())
              print("Du hast verloren! Du hast keine Moves mehr.\n")
              loop.break
          }


          print("____________________________\n")
        }
    }
    }

    print("Weiterspielen?\n")
    var eingabe = readLine("Eingabe: \n")

    eingabe match {

      case "j" => main(args = new Array[String](3))
      case "n" => return
    }
  }
  def main(args: Array[String]): Unit = {

    val grid = Grid(4, 4)
    val player = Player(3, 3)
    val target = Target(0, 0)
    val obstacles:List[Obstacle] = List(Obstacle(1,1), Obstacle(2,1),Obstacle(2,0),Obstacle(3,2),Obstacle(1,3))
    val allowedMoves = Map("Up" -> 5, "Down" -> 5, "Left" -> 5, "Right" -> 5)
    val tui = new Tui()
    var controller = new Controller(grid, player, obstacles, target, allowedMoves)

    gameLoop(controller, tui)


  }

}

