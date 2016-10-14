package de.htwg.se.puzzlerun.view

class Tui{
  
  def draw_field(f: Array[Array[Int]]){ 
    // overrride cell class tostring method so that you can print each cell on tui
    
   for(i <- 0 until f.size){
     for(j <- 0 until f.length){
      print(f(i)(j) + "\t") 
     }
     println()
     println()
   }   
    
  }

}

object main{
  
  def main(args: Array[String]):Unit={
    var tui = new Tui()
    tui.draw_field(Array.ofDim[Int](3,3))
    
  }
}
