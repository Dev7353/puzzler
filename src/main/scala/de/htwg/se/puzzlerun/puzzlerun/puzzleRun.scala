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

object puzzleRun {

  def gameLoop(controller: Controller, tui: Tui): Unit = {
    print("hoch: \t\t w\n" +
      "runter: \t s\n" +
      "links: \t\t a\n" +
      "rechts: \t d\n")
    print("\n")

    val loop = new Breaks
    loop.breakable {
      while (true) {
        tui.draw()
        var eingabeLength = tui.input()
        print("\n" + "Eingabelänge: " + eingabeLength + "\n")

        controller.state match {
          case 0 =>
            if (controller.checkEingabeLength(eingabeLength)) {
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

    var controller = new Controller("level00.config")
    val tui = new Tui(controller)
    gameLoop(controller, tui)

  }

}

