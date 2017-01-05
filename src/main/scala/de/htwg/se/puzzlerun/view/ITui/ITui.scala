package de.htwg.se.puzzlerun.view.ITui

/**
 * Created by kmg on 16.12.16.
 */
trait ITui {

  def draw()
  def input(): Int
  def victory()
  def defeat(message: String)
}
