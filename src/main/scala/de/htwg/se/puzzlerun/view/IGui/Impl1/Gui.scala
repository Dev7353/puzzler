/**
 * Created by kmg on 16.12.16.
 */
package de.htwg.se.puzzlerun.view.IGui.Impl1

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.IGui.IGui

import scala.swing._
import scala.swing.Swing.LineBorder

class Gui(var c: IController) extends MainFrame with Observer with IGui {

  title = "Puzzlrun"
  preferredSize = new Dimension(640, 480)
  resizable = false
  def gridPanel = new GridPanel(c.grid.length + 1, c.grid.height + 1) {
    for {
      i <- 0 until c.grid.grid.length
      j <- 0 until c.grid.grid.length
    } {

      contents += new BoxPanel(Orientation.Horizontal){
        border = LineBorder(java.awt.Color.BLACK, 2)
        contents += new Label{
          text = "" + c.grid.getCell(i, j)
          font = new Font("Verdana", 1, 36)

        }
      }

    }

  }

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
  }

  menuBar = new MenuBar {

      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
  }
  visible = true
  def update = draw()

  def draw(): Unit = {

  }
}