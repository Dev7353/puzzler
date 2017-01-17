/**
  * Created by kmg on 16.12.16.
  */
package de.htwg.se.puzzlerun.view.IGui.Impl1

import scala.util.control.Breaks
import javax.swing.ImageIcon

import de.htwg.se.puzzlerun.controller.IController
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.IGui.IGui

import scala.swing._
import scala.swing.event.Key
import scala.swing.event.KeyPressed

class Gui(var c: IController) extends MainFrame with Observer with IGui {

  c.add(this)
  def update = draw()

  title = "Puzzlrun"

  //resizable = false

  contents = new BoxPanel(Orientation.Vertical) {

    contents += new BoxPanel(Orientation.Horizontal) {

      contents += new Label("LEVEL 0")
      //serialisierung
    }

    contents += new GridPanel(c.grid.length, c.grid.height) {

      for {

        row <- 0 until c.grid.length
        col <- 0 until c.grid.height
      } {
        contents += new BoxPanel(Orientation.Horizontal) {

          contents += new BoxPanel(Orientation.Horizontal) {


            if (c.grid.getCell(row, col).toString.equals("p")) {

              contents += new Label {

                icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/player.png")


              }
            }
            else if (c.grid.getCell(row, col).toString.equals("o")) {
              contents += new Label {

                icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/obstacle.jpeg")

              }
            }
            else {
              contents += new Label {

                icon = new ImageIcon("/Users/kmg/projects/puzzlerun/src/main/scala/de/htwg/se/puzzlerun/view/IGui/Impl1/grass.png")

              }
            }
          }
        }

      }
    }

    val tf = new TextField(columns = 20)
    var loop = new Breaks()



    contents += new BoxPanel(Orientation.Horizontal){

      contents += new Label("Directions")
      contents += tf
      listenTo(tf.keys)


      reactions += {
        case KeyPressed(_,Key.Enter,_,_) => {
          var eingabe = tf.text
          var eingabeLength = eingabe.size

          loop.breakable {
            for (e <- eingabe) {
              e match {
                case 'w' =>
                  c.up()
                  eingabeLength -= 1
                case 'a' =>
                 c.left()
                  eingabeLength -= 1
                case 's' =>
                  c.down()
                  eingabeLength -= 1
                case 'd' =>
                  c.right()
                  eingabeLength -= 1
                case _ =>
                  print("Falsche Eingabe. Wird ignoriert.\n")
                  eingabeLength -= 1
              }

              c.state match {
                case "Not reached" =>
                  if (c.checkEingabeLength(eingabeLength)) {
                    loop.break
                  }
                case "Obstacle reached" =>
                  loop.break
                case "Target reached" =>
                  loop.break
                case "Moves depleted" =>
                  loop.break
              }
            }
          }

          tf.text = ""
        }
      }
    }

  }

  resizable = false
  visible = true

  def draw(): Unit = {


  }
}