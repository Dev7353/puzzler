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
    Thread.sleep(1500)
  }

  def input(): Int = {

    var eingabe = readLine("Eingabe: \n").toCharArray
    var eingabeLength = eingabe.length
    for (c <- eingabe) {
      c match {
        case 'w' =>
          this.c.up()
          eingabeLength -= 1
        case 'a' =>
          this.c.left()
          eingabeLength -= 1
        case 's' =>
          this.c.down()
          eingabeLength -= 1
        case 'd' =>
          this.c.right()
          eingabeLength -= 1
        case _ =>
          print("Falsche Eingabe. Wird ignoriert.\n")
          eingabeLength -= 1
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

