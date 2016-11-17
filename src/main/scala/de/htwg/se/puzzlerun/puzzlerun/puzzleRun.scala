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
        tui.draw()
        var eingabeLength = tui.input()
        eingabeLength -= 1 // TODO: Fix bug that it sometimes doesn't decrement in time
        print("\n" + "Eingabelänge: " + eingabeLength + "\n")

          controller.state match {
            case 0 =>
              if(eingabeLength == 0) {
                controller.moves.map(key => print(key._1 + "\t" + key._2 + "\n"))
                tui.defeat("Du bist nicht am Ziel angekommen.")
                loop.break
              }
            case 1 =>
              controller.moves.map(key => print(key._1 + "\t" + key._2 + "\n\n"))
              tui.defeat("Du bist auf ein Hindernis gestossen oder bist vom Spielfeld gekommen.")
              loop.break
            case 2 =>
              controller.moves.map(key => print(key._1 + "\t" + key._2 + "\n\n"))
              tui.victory()
              loop.break
            case 3 =>
              controller.moves.map(key => print(key._1 + "\t" + key._2 + "\n\n"))
              tui.defeat("Du hast keine Moves mehr.")
              loop.break
          }
        }
    }

    print("Weiterspielen?\n")
    var eingabe = readLine("Eingabe: \n")

    eingabe match {

      case "j" => main(args = new Array[String](3))
      case "n" => return
      case default => print("Input not recognized. Please put in 'j' or 'n")
    }
  }
  def main(args: Array[String]): Unit = {

    val grid = Grid(4, 4)
    val player = Player(3, 3)
    val target = Target(0, 0)
    val obstacles:List[Obstacle] = List(Obstacle(1,1), Obstacle(2,1),Obstacle(2,0),Obstacle(3,2),Obstacle(1,3))
    val allowedMoves = Map("Up" -> 5, "Down" -> 5, "Left" -> 5, "Right" -> 5)
    var controller = new Controller(grid, player, obstacles, target, allowedMoves)
    val tui = new Tui(controller)
    gameLoop(controller, tui)


  }

}

