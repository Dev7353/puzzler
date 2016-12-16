/**
  * Created by kmg on 16.12.16.
  */
package de.htwg.se.puzzlerun.view.IGui.Impl1
import de.htwg.se.puzzlerun.controller._
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.IGui.IGui

import swing._

case class Gui(var c: IController) extends Observer with IGui{


  def update = draw()



  def draw(): Unit ={

  }
}