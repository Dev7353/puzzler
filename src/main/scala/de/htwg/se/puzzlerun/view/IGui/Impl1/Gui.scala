/**
  * Created by kmg on 16.12.16.
  */
package de.htwg.se.puzzlerun.view.IGui.Impl1

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.IGui.IGui

import scala.swing._

class Gui(var c: IController) extends MainFrame with Observer with IGui{

  title = "Puzzlrun"
  preferredSize = new Dimension(640, 480)
  resizable = false
  def gridPanel = new GridPanel(c.grid.length+1, c.grid.height+1){

    for{
      i <- 0 until c.grid.grid.length
      j <- 0 until c.grid.grid.length
    }{

      contents += new CellPanel(c, i, j)
    }

  }


  contents = new BorderPanel{
    add(gridPanel, BorderPanel.Position.Center)
  }
  visible = true
  def update = draw()




  def draw(): Unit ={

  }
}