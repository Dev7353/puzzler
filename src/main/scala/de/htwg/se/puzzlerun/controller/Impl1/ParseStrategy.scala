/**
 * Created by kmg on 19.01.17.
 */
package de.htwg.se.puzzlerun.controller.Impl1

import java.io.{ FileInputStream, InputStream }
import java.util.Properties

import de.htwg.se.puzzlerun.model.Impl1.{ Grid, Obstacle, Player, Target }
import net.liftweb.json.parse

import scala.collection.mutable
import scala.io.Source

import scala.collection.mutable.Map

trait ParseStrategy {

  def levelParse(file: String, c: Controller)
}

class TextParser() extends ParseStrategy {

  override def levelParse(file: String, c: Controller): Unit = {

    import java.nio.file.{ Paths, Files }

    var prop: Properties = new Properties()
    def getCurrentDirectory = new java.io.File(".").getCanonicalPath
    val filename: String = getCurrentDirectory + "/src/levels/" + file
    val is: InputStream = new FileInputStream(filename)

    prop.load(is)

    var grid_size = prop.getProperty("grid").split(",")
    c.grid = Grid(grid_size(0).toInt, grid_size(1).toInt)

    val playerCoord = prop.getProperty("player").split(",")
    c.player = Player(playerCoord(0).toInt, playerCoord(1).toInt)

    var target_coord = prop.getProperty("target").split(",")
    c.target = Target(target_coord(0).toInt, target_coord(1).toInt)

    var obstacles_list = prop.getProperty("obstacles").split(" ")
    for (entry <- obstacles_list) {
      var buffer = entry.split(",")
      c.obstacles += Obstacle(buffer(0).toInt, buffer(1).toInt)
    }
    var moves_list = prop.getProperty("moves").split(" ")
    c.moves = mutable.Map("Up" -> moves_list(0).toInt, "Down" -> moves_list(1).toInt, "Left" -> moves_list(2).toInt, "Right" -> moves_list(3).toInt)

  }
}

class JsonParser() extends ParseStrategy {
  implicit val formats = net.liftweb.json.DefaultFormats
  override def levelParse(file: String, c: Controller): Unit = {
    def getCurrentDirectory = new java.io.File(".").getCanonicalPath
    val filename: String = getCurrentDirectory + "/src/levels/" + file
    val json = parse(Source.fromFile(filename).mkString)
    c.grid = Grid(json.\("grid").children.head.extract[String].toInt, json.\("grid").children(1).extract[String].toInt)
    c.player = Player(json.\("player").children.head.extract[Int], json.\("player").children(1).extract[Int])
    c.target = Target(json.\("target").children.head.extract[Int], json.\("target").children(1).extract[Int])
    for (i <- 0 until json.\("obstacles").children.length)
      c.obstacles += Obstacle(json.\("obstacles").children(i)(0).extract[Int], json.\("obstacles").children(i)(1).extract[Int])
    c.moves = Map(
      "Up" -> json.\("moves").children(2).children(0).extract[Int],
      "Down" -> json.\("moves").children(1).children(0).extract[Int],
      "Left" -> json.\("moves").children(3).children(0).extract[Int],
      "Right" -> json.\("moves").children(0).children(0).extract[Int]
    )
  }
}
