package de.htwg.se.puzzlerun

import de.htwg.se.puzzlerun.model.Student

object Hello {
  def main(args: Array[String]): Unit = {
    val student = Student("Your Name")
    println("Hello, " + student.name)
  }
}
