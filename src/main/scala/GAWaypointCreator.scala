/**
  * Created by BOWMANRS1 on 2/17/2016.
  */

object GAWaypointCreator extends App{

  override def main(args: Array[String]): Unit = {
    print("Creating graphs...")

    val g: Graph = new Graph(50, 50, 50)
    //val n1: g.Node = g.newNode(0,0)
    //val n2: g.Node = g.newNode(1,1)
    //n1.connectTo(n2)
    //g.removeNode(1)
    g.printAdjMatrix()
  }
}