/**
  * Created by kmg on 16.12.16.
  */
package de.htwg.se.puzzlerun.view.IGui.Impl1

import scala.util.control.Breaks
import javax.swing.ImageIcon

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.IGui.IGui

import scala.swing.{BoxPanel, _}
import scala.swing.event.Key
import scala.swing.event.KeyPressed

class Gui(var c: IController) extends MainFrame with Observer with IGui {

  var fields = Array.ofDim[BoxPanel](c.grid.height, c.grid.length)
  val tf = new TextField(columns = 10)

  var up = new Label("Up \n"+c.moves.get("Up").get)
  var down = new Label("Down "+c.moves.get("Down").get)
  var right = new Label("Right "+c.moves.get("Right").get)
  var left = new Label("Left "+c.moves.get("Left").get)

  for{
    row <- 0 until c.grid.length
    col <- 0 until c.grid.height

  }{
    fields(row)(col) = new BoxPanel(Orientation.Horizontal){
      if (c.grid.getCell(row, col).toString.equals("p")) {
        contents += new Label(""){
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/player.png")
        }
      }
      else if(c.grid.getCell(row, col).toString.equals("X")){
        contents += new Label(""){
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/grass.png")
        }
      }
      else if(c.grid.getCell(row, col).toString.equals("-")){
        contents += new Label(""){
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/grass.png")
        }
      }
      else if(c.grid.getCell(row, col).toString.equals("o")){
        contents += new Label(""){
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

  def directions = new FlowPanel{
    contents += new Label("Directions")
    contents += tf
  }

  def bla = new GridPanel(1, 4){

    contents += up
    contents += down
    contents += right
    contents += left
  }
  import BorderPanel.Position._
  contents = new BorderPanel{
    add(titleBox, North)
    add(grid, Center)
    add(directions, East)
    add(bla, West)
  }

  listenTo(tf.keys)
  for{
    row <- 0 until c.grid.length
    col <- 0 until c.grid.height
  }{
    listenTo(fields(row)(col))
  }

  listenTo(up)
  listenTo(down)
  listenTo(right)
  listenTo(left)




  reactions += {
    case KeyPressed(_,Key.Enter,_,_) => {
      var eingabe = tf.text
      var eingabeLength = eingabe.size
      print(eingabeLength + "\n")
      for{
        i <- 0 until eingabeLength
      }{
        if(eingabe.charAt(i).equals('w')){
          c.up()
        }
        else if(eingabe.charAt(i).equals('s')){
          c.down()
        }
        else if(eingabe.charAt(i).equals('d')){
          c.right()
        }
        else if(eingabe.charAt(i).equals('a')){
          c.left()
        }

      }
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

      if(c.grid.getCell(row, col).toString.equals("p")){
        fields(row)(col).contents += new Label(""){
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/player.png")
        }
      }
      else if(c.grid.getCell(row, col).toString.equals("o")){
        fields(row)(col).contents += new Label(""){
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/obstacle.jpeg")
        }
      }
      else if(c.grid.getCell(row, col).toString.equals("X")){
        fields(row)(col).contents += new Label(""){
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/grass.png")
        }
      }
      else if(c.grid.getCell(row, col).toString.equals("-")){
        fields(row)(col).contents += new Label(""){
          icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/grass.png")
        }
      }


      fields(row)(col).repaint
      fields(row)(col).revalidate()
    }

    up.text = "Up "+c.moves.get("Up").get
    down.text = "Down "+c.moves.get("Down").get
    right.text = "Right "+c.moves.get("Right").get
    left.text = "Left "+c.moves.get("Left").get

    this.repaint()
  }
}