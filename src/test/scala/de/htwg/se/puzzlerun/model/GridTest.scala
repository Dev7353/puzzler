package de.htwg.se.puzzlerun.model

import de.htwg.se.puzzlerun.model.Impl1.{Cell, Grid}
import org.scalatest._

/**
 * Created by stefl on 25.10.2016.
 */
class GridTest extends WordSpec with Matchers {

  "A new grid" should {
    val grid = Grid(3, 3)

    "return a 2d array of cells" in {
      grid.createGrid()(0)(0) shouldBe a[Cell]
    }

    "and create an array" in {
      grid.length should equal(3)
      grid.height should equal(3)
    }
    "and should return a cell" in {
      grid.getCell(0, 0).toString should be("-")
    }
    "and should set a cell" in {
      val newCell = new Cell
      grid.setCell(0, 0, newCell)
      grid.grid(0)(0) shouldBe a[Cell]
    }

    "tostring should output length 1" in {

      val grid2 = Grid(1, 1)
      assert(grid2.toString.length() == 6)
    }

  }
}
