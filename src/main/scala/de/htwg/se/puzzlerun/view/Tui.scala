package de.htwg.se.puzzlerun.view

import de.htwg.se.puzzlerun.controller._
import de.htwg.se.puzzlerun.util._

import scala.io.StdIn._
import scala.util.control.Breaks

case class Tui(var c: Controller) extends Observer {
  c.add(this)

  def update = draw()

  def draw(): Unit = {
    var size = this.c.grid.grid.length
    var format = "%-"+size*4+"s%-"+size*2+"s%-"+size*2+"s%-"+size*2+"s%"+size*2+"s\n"

    printf(format, "Gamefield", "Up", "Down", "Left", "Right")
    val field = this.c.grid.grid
    for (i <- field.indices) {
      for (j <- field(0).indices) {
        print(field(i)(j) + "\t")
      }
      if(i == field.length / 2)
        print(this.c.moves.get("Up") + "|" + this.c.moves.get("Down")+"|" + this.c.moves.get("Left")+"|" + this.c.moves.get("Right"))
      print("\n")
    }
    Thread.sleep(1500)
  }

  def input(): Int = {
    var eingabe = readLine("Eingabe: \n").toCharArray
    var eingabeLength = eingabe.length
    var loop = new Breaks

   loop.breakable {
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

       this.c.state match {
         case 0 =>
           if (this.c.checkEingabeLength(eingabeLength)) {
             loop.break
           }
         case 1 =>
           loop.break
         case 2 =>
           loop.break
         case 3 =>
           loop.break
       }
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

