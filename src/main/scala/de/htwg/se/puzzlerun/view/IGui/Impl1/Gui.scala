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

  var fields = Array.ofDim[BoxPanel](c.grid.height, c.grid.length)
  val tf = new TextField(columns = 10)

  var up = new Label("Up \n" + c.moves.get("Up").get)
  var down = new Label("Down " + c.moves.get("Down").get)
  var right = new Label("Right " + c.moves.get("Right").get)
  var left = new Label("Left " + c.moves.get("Left").get)

  var btn_up = new Button("Up")
  var btn_down = new Button("Down")
  var btn_right = new Button("Right")
  var btn_left = new Button("Left")

  for {
    row <- 0 until c.grid.length
    col <- 0 until c.grid.height

  } {
    fields(row)(col) = new BoxPanel(Orientation.Horizontal) {
      if (c.grid.getCell(row, col).toString.equals("p")) {
        contents += new Label("") {
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/player.png")
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("X")) {
        contents += new Label("") {
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/door.png")
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("-")) {
        contents += new Label("") {
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/grass.png")
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("o")) {
        contents += new Label("") {
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/obstacle.jpeg")
        }
      }
    }
  }

  c.add(this)

  def update = draw()

  title = "Puzzlrun"

  def titleBox = new BoxPanel(Orientation.Horizontal) {

    contents += new Label("LEVEL 0")
    //serialisierung
  }

  def grid = new GridPanel(c.grid.length, c.grid.height) {

    for {

      row <- 0 until c.grid.length
      col <- 0 until c.grid.height
    } {
      contents += fields(row)(col)
    }
  }


  def control = new BorderPanel{

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

  def bla = new GridPanel(1, 4) {

    contents += up
    contents += down
    contents += right
    contents += left

  }



  contents = new BorderPanel {
    add(titleBox, North)
    add(grid, Center)
    add(control, East)
    add(bla, West)
  }

  listenTo(tf)
  for {
    row <- 0 until c.grid.length
    col <- 0 until c.grid.height
  } {
    listenTo(fields(row)(col))
  }

  listenTo(up)
  listenTo(down)
  listenTo(right)
  listenTo(left)

  listenTo(btn_down)
  listenTo(btn_up)
  listenTo(btn_left)
  listenTo(btn_right)


  reactions += {
    case ButtonClicked(b) =>
      if(b.text.equals("Up")){
        c.up()
      }
      else if(b.text.equals("Down")){
        c.down()
      }
      else if(b.text.equals("Right")){
        c.right()
      }
      else if(b.text.equals("Left")){
        c.left()
      }

      if(c.state.equals("Target reached")){
        Dialog.showMessage(contents.head, "Congrats Pal :)", title="You pressed me")
      }
      else if(c.state.equals("Obstacle reached")){
        Dialog.showMessage(contents.head, "Well, that was bad.", title="You pressed me")
      }
      else if(c.state.equals("Moves depleted")){
        Dialog.showMessage(contents.head, "No more moves left!", title="You pressed me")
      }

  }
  resizable = true
  visible = true

  def draw(): Unit = {
    for {

      row <- 0 until c.grid.length
      col <- 0 until c.grid.height
    } {
      fields(row)(col).contents.clear()

      if (c.grid.getCell(row, col).toString.equals("p")) {
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/player.png")
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("o")) {
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/obstacle.jpeg")
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("X")) {
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/door.png")
        }
      }
      else if (c.grid.getCell(row, col).toString.equals("-")) {
        fields(row)(col).contents += new Label("") {
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/grass.png")
        }
      }


      fields(row)(col).repaint
      fields(row)(col).revalidate()
    }

    up.text = "Up " + c.moves.get("Up").get
    down.text = "Down " + c.moves.get("Down").get
    right.text = "Right " + c.moves.get("Right").get
    left.text = "Left " + c.moves.get("Left").get

    this.repaint()
  }
}