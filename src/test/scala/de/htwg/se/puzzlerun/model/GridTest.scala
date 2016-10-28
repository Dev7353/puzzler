package de.htwg.se.puzzlerun.model

import org.scalatest._

/**
  * Created by stefl on 25.10.2016.
  */
class GridTest extends FlatSpec with Matchers {

  "a new grid" should "create a array" in {
    val grid = new Grid(3, 3)

    grid.grid.length === 3
    grid.grid(0).length === 3
  }

  "a grid" should "return a cell" in {
    val grid = new Grid(3, 3)

    grid.getCell(0, 0).toString should be ("-")
  }
}
