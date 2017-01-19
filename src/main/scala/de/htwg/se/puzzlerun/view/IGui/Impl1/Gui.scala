/**
  * Created by kmg on 16.12.16.
  */
package de.htwg.se.puzzlerun.view.IGui.Impl1

import scala.util.control.Breaks
import javax.swing.ImageIcon

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.IGui.IGui

import scala.swing.{BorderPanel, BoxPanel, _}
import scala.swing.event.{ButtonClicked, Key, KeyPressed}

import BorderPanel.Position._

class Gui(var c: IController) extends MainFrame with Observer with IGui {
  def getCurrentDirectory = new java.io.File(".").getCanonicalPath
  var player_img = getCurrentDirectory + "/src/main/scala/de/htwg/se/puzzlerun/view/IGUI/Impl1/player.png"
  var obstacle_img = getCurrentDirectory + "/src/main/scala/de/htwg/se/puzzlerun/view/IGUI/Impl1/obstacle.jpeg"
  var grass_img = getCurrentDirectory + "/src/main/scala/de/htwg/se/puzzlerun/view/IGUI/Impl1/grass.png"
  var door_img = getCurrentDirectory + "/src/main/scala/de/htwg/se/puzzlerun/view/IGUI/Impl1/door.png"

  var current_level = 0
  var fields = Array.ofDim[BoxPanel](c.grid.height, c.grid.length)

  var up = "Up " + c.moves.get("Up").get
  var down = "Down " + c.moves.get("Down").get
  var right = "Right " + c.moves.get("Right").get
  var left = "Left " + c.moves.get("Left").get

  var lbl_level = new Label("LEVEL " + current_level)

  var btn_up = new Button("" + up)
  var btn_down = new Button("" + down)
  var btn_right = new Button("" + right)
  var btn_left = new Button("" + left)

  fill()

  c.add(this)

  def update = draw()

  title = "Puzzlrun"

  def titleBox = new BoxPanel(Orientation.Horizontal) {

    contents += lbl_level
    //serialisierung
  }

  var grid = new GridPanel(c.grid.length, c.grid.height) {

    for {

      row <- 0 until c.grid.length
      col <- 0 until c.grid.height
    } {
      contents += fields(row)(col)
    }
  }


  def control = new BorderPanel {

    def directions = new GridPanel(3, 3) {
      contents += new Label(" ")
      contents += btn_up
      contents += new Label(" ")
      contents += btn_left
      contents += new Label(" ")
      contents += btn_right
      contents += new Label(" ")
      contents += btn_down
      contents += new Label(" ")
    }

    add(directions, Center)
  }


  contents = new BorderPanel {
    add(titleBox, North)
    add(grid, Center)
    add(control, East)
  }

  listenTo(btn_down)
  listenTo(btn_up)
  listenTo(btn_left)
  listenTo(btn_right)


  reactions += {
    case ButtonClicked(b) =>
      if (b.text.contains("Up")) {
        c.up()
        btn_up.text = "Up " + c.moves.get("Up").get
        if (c.moves.get("Up").get == 0)
          btn_up.enabled = false
      }
      else if (b.text.contains("Down")) {
        c.down()
        btn_down.text = "Down " + c.moves.get("Down").get
        if (c.moves.get("Down").get == 0)
          btn_down.enabled = false
      }
      else if (b.text.contains("Right")) {
        c.right()
        btn_right.text = "Right " + c.moves.get("Right").get
        if (c.moves.get("Right").get == 0)
          btn_right.enabled = false
      }
      else if (b.text.contains("Left")) {
        c.left()
        btn_left.text = "Left " + c.moves.get("Left").get
        if (c.moves.get("Left").get == 0)
          btn_left.enabled = false
      }

      if (c.state.equals("Target reached")) {
        Dialog.showMessage(contents.head, "Congrats Pal :)", title = "You pressed me")
      }
      else if (c.state.equals("Obstacle reached")) {
        Dialog.showMessage(contents.head, "Well, that was bad.", title = "You pressed me")
      }
      else if (c.state.equals("Moves depleted")) {
        Dialog.showMessage(contents.head, "No more moves left!", title = "You pressed me")
      }

  }
  resizable = true
  visible = true

  def draw(): Unit = {

    if (c.level > current_level) {

      current_level += 1
      grid.contents.clear()
      lbl_level.text = "LEVEL " + current_level
      fields = Array.ofDim[BoxPanel](c.grid.height, c.grid.length)
      fill()

      grid.columns = c.grid.height
      grid.rows = c.grid.length
      for {

        row <- 0 until c.grid.length
        col <- 0 until c.grid.height
      } {
        grid.contents += fields(row)(col)
      }

      btn_up.enabled = true
      btn_down.enabled = true
      btn_right.enabled = true
      btn_left.enabled = true

      btn_up.text = "Up " + c.moves.get("Up").get
      btn_down.text = "Down " + c.moves.get("Down").get
      btn_right.text = "Right " + c.moves.get("Right").get
      btn_left.text = "Left " + c.moves.get("Left").get

    }
    for {

      row <- 0 until c.grid.length
      col <- 0 until c.grid.height
    } {
      fields(row)(col).contents.clear()

      if (c.grid.getCell(row, col).toString.equals("p")) {
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon(player_img)
          preferredSize = new Dimension (20,20)
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("o")) {
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon(obstacle_img)
          preferredSize = new Dimension (20,20)
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("X")) {
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon(door_img)
          preferredSize = new Dimension (20,20)
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("-")) {
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon(grass_img)
          preferredSize = new Dimension (20,20)

        }
      }


      fields(row)(col).repaint
      fields(row)(col).revalidate()
    }

    this.repaint()
  }

  def fill(): Unit = {
    for {
      row <- 0 until c.grid.length
      col <- 0 until c.grid.height

    } {
      fields(row)(col) = new BoxPanel(Orientation.Horizontal) {
        if (c.grid.getCell(row, col).toString.equals("p")) {
          contents += new Label("") {
            icon = new ImageIcon(player_img)
          }
        }
        else if (c.grid.getCell(row, col).toString.equals("X")) {
          contents += new Label("") {
            icon = new ImageIcon(door_img)
          }
        }
        else if (c.grid.getCell(row, col).toString.equals("-")) {
          contents += new Label("") {
            icon = new ImageIcon(grass_img)
          }
        }
        else if (c.grid.getCell(row, col).toString.equals("o")) {
          contents += new Label("") {
            icon = new ImageIcon(obstacle_img)
          }
        }
      }
    }
  }
}