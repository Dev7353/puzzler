package de.htwg.se.puzzlerun.view.ITui.Impl1

import de.htwg.se.puzzlerun.controller._
import de.htwg.se.puzzlerun.util._
import de.htwg.se.puzzlerun.view.ITui.ITui

import scala.io.StdIn._
import scala.util.control.Breaks
import org.apache.log4j._

case class Tui(var c: IController) extends Observer with ITui {
  var current_level = 0
  c.add(this)
  def update = draw()
  val logger = Logger.getLogger(getClass().getName())


  logger.info("--- Puzzlerun ---")
  logger.info("The awesome labyrinth game!")

  def draw(): Unit = {
    var size = this.c.grid.grid.length
    var format = "%-" + size * 4 + "s%-" + size * 2 + "s%-" + size * 2 + "s%-" + size * 2 + "s%" + size * 2 + "s\n"

    logger.info(format, "Gamefield", "Up", "Down", "Left", "Right")
    println(format, this.c.moves.get("Up").get, this.c.moves.get("Down").get, this.c.moves.get("Left").get, this.c.moves.get("Right").get)
    val field = this.c.grid.grid
    for (i <- field.indices) {
      for (j <- field(0).indices) {
        print(field(i)(j) + "\t")
      }

      print("\n")
    }
    if (c.state.equals("Target reached")) {
      logger.info("Congrats Pal :)")

    } else if (c.state.equals("Obstacle reached")) {
      logger.info("Well, that was bad. Good Luck next time.")
    } else if (c.state.equals("Moves depleted")) {
      logger.info("No more moves left!")
    }
    Thread.sleep(200)
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
          case 'q' => sys.exit()
          case _ =>
            logger.info("Falsche Eingabe. Wird ignoriert.\n")
            eingabeLength -= 1
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

