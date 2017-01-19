/**
  * Created by kmg on 19.01.17.
  */

package de.htwg.se.puzzlerun.controller.Impl1

class Context(sg: ParseStrategy, c: Controller){

  val strategy : ParseStrategy = sg
  val controller: Controller = c

  def execute(file: String): Unit ={
    strategy.parse(file, controller)
  }
}