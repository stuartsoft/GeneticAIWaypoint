/**
  * Created by BOWMANRS1 on 2/17/2016.
  */

object GAWaypointCreator extends App{

  override def main(args: Array[String]): Unit = {
    println("Creating graphs...")

    val g: Graph = new Graph(50)
    g.printAdjMatrix()
    g.writeToFile("output.txt")
  }
}