/**
 * Created by kmg on 23.12.16.
 */

package de.htwg.se.puzzlerun.view.IGui.Impl1

import de.htwg.se.puzzlerun.controller.IController

import scala.swing._

class CellPanel(c: IController, x: Int, y: Int) extends FlowPanel {
  var cell = c.grid.grid(x)(y)
  var label = new Label(cell.toString)
  def boxPanel = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(10, 10)
    contents += label
    border = Swing.BeveledBorder(Swing.Raised)
  }
}

