/**
  * Created by BOWMANRS1 on 2/17/2016.
  */

object GAWaypointCreator extends App{

  override def main(args: Array[String]): Unit = {
    println("Creating graphs...")

    val g: Graph = new Graph(50)
    //println(g.checkLineOfSight(0,0,5,5))
    //println(g.checkLineOfSight(5,5,0,0))
    println("----")
    //g.printLineOfSight(23,42,23,37)
    println("----")

    g.printAdjMatrix()
  }
}