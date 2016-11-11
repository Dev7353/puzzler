/**
  * Created by kmg on 11.11.16.
  */

package de.htwg.se.puzzlerun.util

trait Observer {
  def update
}
class Observable {
  var subscribers:Vector[Observer] = Vector()
  def add(s:Observer) = subscribers=subscribers:+s
  def remove(s:Observer) = subscribers=subscribers.filterNot(o=>o==s)
  def notifyObservers = subscribers.foreach(o=>o.update)
}