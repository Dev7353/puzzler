/**
 * Created by kmg on 23.12.16.
 */

package de.htwg.se.puzzlerun.view.IGui.Impl1

import de.htwg.se.puzzlerun.controller.IController

import scala.swing._
import   scala.swing.Swing.LineBorder

class CellPanel(c: IController, x: Int, y: Int) extends FlowPanel {
  var cell = c.grid.getCell(x, y)
  var label = new Label("" + cell)
}

