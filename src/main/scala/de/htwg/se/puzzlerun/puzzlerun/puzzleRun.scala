/**
 * Created by kmg on 27.10.16.
 */
package de.htwg.se.puzzlerun.puzzlerun

import scala.io.StdIn
import de.htwg.se.puzzlerun.model._
import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.controller.Impl1.Controller
import de.htwg.se.puzzlerun.view.ITui.ITui
import de.htwg.se.puzzlerun.view.ITui.Impl1.Tui

import scala.util.control._
import scala.collection.mutable.Map
import scala.io.StdIn._

object puzzleRun {

  def gameLoop(controller: IController, tui: ITui): Unit = {
    tui.draw()
    print("\n")

    val loop = new Breaks
    loop.breakable {
      while (true) {
        var eingabeLength = tui.input()
        //print("\n" + "Eingabelänge: " + eingabeLength + "\n")

        controller.state match {
          case "Not reached" =>
            if (controller.checkEingabeLength(eingabeLength)) {
              tui.defeat("Du bist nicht am Ziel angekommen.")
              loop.break
            }
          case "Obstacle reached" =>
            tui.defeat("Du bist auf ein Hindernis gestossen oder bist vom Spielfeld gekommen.")
            loop.break
          case "Target reached" =>
            tui.victory()
            loop.break
          case "Moves depleted" =>
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

    var controller: IController = new Controller("level00.config")
    val tui: ITui = Tui(controller)
    gameLoop(controller, tui)

  }

}

