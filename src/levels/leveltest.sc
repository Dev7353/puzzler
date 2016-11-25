//import scala.io.Source
//import java.io.File
//
//def getListOfFiles(dir: String):List[File] = {
//  val d = new File(dir)
//  if (d.exists && d.isDirectory) {
//    d.listFiles.filter(_.isFile).toList
//  } else {
//    List[File]()
//  }
//}
//
//getListOfFiles("/Users/kmg/projects/puzzlerun/src/levels")
//
//
//
//val home = System.getProperty("user.home")
//
//var gridx = 0
//var gridy = 0
//val output = scala.collection.mutable.Map[String,String]()
//var i:Int = 0
//for(line <- Source.fromFile(home + "/projects/puzzlerun/src/levels/level00/").getLines(); i <- 10)
//  output.put("a", line)
//
//println(output)

import java.io.FileInputStream
import java.io.InputStream
import java.util.Properties
import de.htwg.se.puzzlerun.model._

var prop: Properties = new Properties()
val filename: String = "/Users/kmg/projects/puzzlerun/src/levels/level00.config"
val is: InputStream =  new FileInputStream(filename)

prop.load(is)

var grid_size = prop.getProperty("grid").split(",")
val grid = Grid(grid_size(0).toInt, grid_size(1).toInt)

var player_coord = prop.getProperty("player").split(",")
val player = Player(player_coord(0).toInt, player_coord(1).toInt)

var target_coord = prop.getProperty("target").split(",")
val target = Target(target_coord(0).toInt, target_coord(1).toInt)



//var obstacles_list = prop.getProperty("obstacles").split(" ")
//var ob: List[Obstacle] = List[Obstacle]()
//for(entry <- obstacles_list){
//  var buffer = entry.split(",")
//  ob:+Obstacle(buffer(0).toInt, buffer(1).toInt)
//}
//println(ob)







