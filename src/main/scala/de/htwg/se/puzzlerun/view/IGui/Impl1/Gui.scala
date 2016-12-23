/**
  * Created by kmg on 16.12.16.
  */
package de.htwg.se.puzzlerun.view.IGui.Impl1
import javax.swing.{ImageIcon, JLabel}

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.IGui.IGui

import scala.swing._

class Gui(var c: IController) extends MainFrame with Observer with IGui{

  title = "Puzzlrun"
  preferredSize = new Dimension(640, 480)
  resizable = false
  contents = new GridPanel(c.grid.length+1, c.grid.height+1){

    val player = new Label("", new ImageIcon(getClass.getResource("player.png")), Alignment.Right)

    contents += player

    for(i <- 1 to 16){
      contents += new Label{
        icon = new ImageIcon(getClass.getResource("gras.jpg"))
      }

    }



  }

  visible = true
  def update = draw()




  def draw(): Unit ={

  }
}