/**
 * Created by kmg on 27.10.16.
 */
package de.htwg.se.puzzlerun.puzzlerun

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.controller.Impl1.Controller
import de.htwg.se.puzzlerun.view.ITui.ITui
import de.htwg.se.puzzlerun.view.ITui.Impl1.Tui
import de.htwg.se.puzzlerun.view.IGui.Impl1.Gui

import com.google.inject.Guice

import scala.util.control._
import scala.io.StdIn._

object puzzleRun {

  def gameLoop(controller: IController, tui: ITui): Unit = {
    tui.draw()
    print("\n")
    while (true) {
      tui.input()
    }

  }
  def main(args: Array[String]): Unit = {

    var controller: IController = new Controller("level00.config")
    val tui: ITui = Tui(controller)
    val gui = new Gui(controller)
    gameLoop(controller, tui)

  }

}

