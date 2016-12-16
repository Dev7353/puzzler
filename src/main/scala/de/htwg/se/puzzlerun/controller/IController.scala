package de.htwg.se.puzzlerun.controller
import de.htwg.se.puzzlerun.model.Impl1.Grid
import de.htwg.se.puzzlerun.util.Observable

import scala.collection.mutable.Map

/**
  * Created by kmg on 16.12.16.
  */

trait IController extends Observable {

  var grid: Grid
  var moves: Map[String, Int]
  var state: String
  def wrap()
  def move(x: Int, y: Int)
  def up()
  def down()
  def right()
  def left()
  def checkEingabeLength(eingabeLength: Int): Boolean
}