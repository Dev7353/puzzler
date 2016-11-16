package de.htwg.se.puzzlerun.view

import com.sun.glass.ui.Timer
import de.htwg.se.puzzlerun.controller._
import de.htwg.se.puzzlerun.util._

import scala.io.StdIn._

case class Tui(var c: Controller) extends Observer {
  c.add(this)

  def update = draw()

  def draw(): Unit = {
    print(c.grid.toString)
    print("____________________________\n")
  }

  def input(): Int = {

    var eingabe = readLine("Eingabe: \n").toCharArray
    var eingabeLength = eingabe.length
    for (c <- eingabe) {
      c match {
        case 'w' => this.c.up()
        case 'a' => this.c.left()
        case 's' => this.c.down()
        case 'd' => this.c.right()
        case _ => print("Falsche Eingabe.\n")
      }
    }
    eingabeLength
  }
  def defeat(reason: String): Unit = {
    print("Du hast verloren! " + reason + "\n")
  }
  def victory(): Unit = {
    print("Du hast gewonnen! ")
  }
}


