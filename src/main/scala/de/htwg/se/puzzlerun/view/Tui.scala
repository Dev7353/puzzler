package de.htwg.se.puzzlerun.view

import de.htwg.se.puzzlerun.controller._
import de.htwg.se.puzzlerun.util._

case class Tui(var c: Controller) extends Observable{

  def update = draw()

  def draw(): Unit ={
    print(c.grid.toString)
  }



}


