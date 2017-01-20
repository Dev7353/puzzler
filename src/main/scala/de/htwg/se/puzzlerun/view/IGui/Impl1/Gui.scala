/**
 * Created by kmg on 16.12.16.
 */
package de.htwg.se.puzzlerun.view.IGui.Impl1

import java.awt.{ Graphics, Image }
import java.awt.image.BufferedImage
import java.io.File
import javafx.scene.control.CheckBox
import javax.imageio.ImageIO
import javax.swing.ImageIcon

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.IGui.IGui

import scala.swing.{ BorderPanel, BoxPanel, _ }
import scala.swing.event.{ ButtonClicked, Key, KeyPressed }
import BorderPanel.Position._

class Gui(var c: IController) extends MainFrame with Observer with IGui {
  c.add(this)
  def update = draw()

  var grid_height = 600
  var grid_width = 600
  var helper = false
  def getCurrentDirectory = new java.io.File(".").getCanonicalPath
  var player_img = getCurrentDirectory + "/src/img/player.png"
  var obstacle_img = getCurrentDirectory + "/src/img/obstacle.png"
  var grass_img = getCurrentDirectory + "/src/img/grass.png"
  var door_img = getCurrentDirectory + "/src/img/door.png"

  var cb: Component = new RadioButton("Helper")

  var current_level = 0
  var fields = Array.ofDim[BoxPanel](c.grid.height, c.grid.length)

  var up = "Up " + c.moves.get("Up").get
  var down = "Down " + c.moves.get("Down").get
  var right = "Right " + c.moves.get("Right").get
  var left = "Left " + c.moves.get("Left").get

  var lbl_level = new Label("LEVEL " + current_level) {
    font = new Font("Verdana", 1, 36)
  }

  var btn_up = new Button("" + up)
  var btn_down = new Button("" + down)
  var btn_right = new Button("" + right)
  var btn_left = new Button("" + left)

  fill()

  title = "Puzzlrun"

  menuBar = new MenuBar {
    contents += new Menu("Puzzlerun") {

      contents += new MenuItem(Action("about") { Dialog.showMessage(null, "Puzzlerun 1.0", title = "about") })
      contents += new Separator
      contents += new MenuItem(Action("quit") { sys.exit() })

    }
  }

  def titleBox = new GridPanel(2, 1) {
    contents += new Label("Puzzlerun") {
      font = new Font("Verdana", 2, 40)
    }
    contents += lbl_level
  }

  var grid = new GridPanel(c.grid.length, c.grid.height) {

    border = Swing.BeveledBorder(Swing.Raised)
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
      border = Swing.BeveledBorder(Swing.Raised)
      background = java.awt.Color.BLACK
    }

    def cb_area = new BoxPanel(Orientation.Horizontal) {
      contents += cb
    }
    add(directions, Center)
    add(cb_area, South)
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
  listenTo(cb)

  listenTo(grid.keys)

  reactions += {
    case ButtonClicked(b) =>
      if (b.text.contains("Up")) {
        c.up()
        btn_up.text = "Up " + c.moves.get("Up").get
        if (c.moves.get("Up").get == 0)
          btn_up.enabled = false
      } else if (b.text.contains("Down")) {
        c.down()
        btn_down.text = "Down " + c.moves.get("Down").get
        if (c.moves.get("Down").get == 0)
          btn_down.enabled = false
      } else if (b.text.contains("Right")) {
        c.right()
        btn_right.text = "Right " + c.moves.get("Right").get
        if (c.moves.get("Right").get == 0)
          btn_right.enabled = false
      } else if (b.text.contains("Left")) {
        c.left()
        btn_left.text = "Left " + c.moves.get("Left").get
        if (c.moves.get("Left").get == 0)
          btn_left.enabled = false
      } else if (b.text.equals("Helper"))

        if (c.state.equals("Target reached")) {
          Dialog.showMessage(contents.head, "Congrats Pal :)", title = "")
        } else if (c.state.equals("Obstacle reached")) {
          Dialog.showMessage(contents.head, "Well, that was bad. Good Luck next time", title = "LOSER!")
          sys.exit()
        } else if (c.state.equals("Moves depleted")) {
          Dialog.showMessage(contents.head, "No more moves left!", title = "LOSER!")
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

      if (c.grid.getCell(row, col).toString.equals("p")) {
        fields(row)(col).contents.clear()
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon(getImage(player_img))
        }
      }
      if (c.grid.getCell(row, col).toString.equals("-")) {
        fields(row)(col).contents.clear()
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon(grass_img)
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
            icon = new ImageIcon(getImage(player_img))

          }
        } else if (c.grid.getCell(row, col).toString.equals("X")) {
          contents += new Label("") {
            icon = new ImageIcon(getImage(door_img))
          }
        } else if (c.grid.getCell(row, col).toString.equals("-")) {
          contents += new Label("") {
            icon = new ImageIcon(getImage(grass_img))
          }
        } else if (c.grid.getCell(row, col).toString.equals("o")) {
          contents += new Label("") {

            icon = new ImageIcon(getImage(obstacle_img))

          }
        }
      }
    }
  }

  def getImage(file: String): Image = {
    var ic: ImageIcon = new ImageIcon(file)
    var newImage: Image = ic.getImage.getScaledInstance(grid_height / c.grid.height, grid_width / c.grid.length, Image.SCALE_SMOOTH)
    newImage

  }
}