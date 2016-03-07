import scala.collection.mutable.ListBuffer
import java.io._
/**
  * Created by BOWMANRS1 on 2/17/2016.
  */
class Graph(initialNodes: Int) {

  var nodes = Map[Int, Node]()  //an empty hash map for nodes. Maps the node ID to node object for easy lookup
  var maze = new Maze("src/main/resources/maze1.txt")
  val wid = maze.blocks.size
  val height = maze.blocks(0).size
  def maxID = {
    var topID: Int = 0
    nodes.foreach{ case (key, value) =>
      if (value.ID >= topID)
        topID = value.ID
    }
    topID
  }

  while(nodes.size < initialNodes) {
    val n = newRandomNode()
    if (maze.blocks(n.pos._1.toInt)(n.pos._2.toInt))
      removeNode(n.ID)//remove the node if it spawned inside a block
  }
  connectVisibleNodes()

  class Node(x: Float, y: Float, var ID: Int){
    //constructor with xy position
    var connectedNodes = new ListBuffer[Graph#Node]()
    var pos = (x,y)

    def connectTo(node: Graph#Node): Unit ={
      if (!connectedNodes.contains(node)){
        connectedNodes += node //append the node to the list of connected nodes
        if (node.connectedNodes.find(this.equals).isEmpty){
          node.connectedNodes += this //also append this to the other list of connected nodes
        }
      }
    }
  }

  def printAdjMatrix(): Unit = {
    nodes.foreach{ case (key, value) =>
      print("\nNode " + value.ID + value.pos + " is connected to\n")
      value.connectedNodes.foreach((mynode) => print(mynode.ID + " "))
    }
  }

  def newNode(x: Float, y: Float): Node = {

    val res = new Node(x, y, maxID+1)
    nodes += (maxID+1 -> res)//add new node to the Map
    res
  }

  def newRandomNode(): Node = {
    val r = scala.util.Random;
    newNode(r.nextFloat()*wid, r.nextFloat()*height)
  }

  def removeNode(id: Int) = {
    nodes = nodes.filterKeys(_!=id)//Filter out the node from the Map
    //remove any connections to this node
    nodes.foreach{
      case (key, value) =>
        value.connectedNodes = value.connectedNodes
          .filter(tempnode => tempnode.ID != id)
    }
  }

  def connectVisibleNodes(): Unit ={
    nodes.foreach{
      case (key, value) => {
        nodes.foreach{
          case (key2, value2) => {
            if (!value.equals(value2)){
              val v1 = (value.pos._1.toInt, value.pos._2.toInt)
              val v2 = (value2.pos._1.toInt, value2.pos._2.toInt)
              if (checkLineOfSight(v1._1, v1._2, v2._1, v2._2)){
                value.connectTo(value2)
              }
            }
          }
        }
      }
    }
  }

  def printLineOfSight(x1:Float, y1:Float, x2:Float, y2:Float) = {
    val len = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1))
    val dir = ((x2-x1)/len, (y2-y1)/len)

    var x: Double = x1
    var y: Double = y1

    if (x1 < x2 && y1 < y2){
      while(x <= x2 && y <= y2){
        println(maze.blocks(x.toInt)(y.toInt))
        y += dir._2
        x += dir._1
      }
    }
    else if (x1 <= x2 && y1 >= y2){
      while(x <= x2 && y >= y2){
        println(maze.blocks(x.toInt)(y.toInt))
        y += dir._2
        x += dir._1
      }
    }
    else if (x1 >= x2 && y1 <= y2){
      while(x >= x2 && y <= y2){
        println(maze.blocks(x.toInt)(y.toInt))
        y += dir._2
        x += dir._1
      }
    }
    else{
      while(x >= x2 && y >= y2){
        println(maze.blocks(x.toInt)(y.toInt))
        y += dir._2
        x += dir._1
      }
    }
  }

  def checkLineOfSight(x1:Float, y1:Float, x2:Float, y2:Float):Boolean = {
    val len = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1))
    val dir = ((x2-x1)/len, (y2-y1)/len)

    var x: Double = x1
    var y: Double = y1
    if (maze.blocks(x.toInt)(y.toInt)==true) return false
    if (maze.blocks(x2.toInt)(y2.toInt)==true) return false

    if (x1 <= x2 && y1 <= y2){
      while(x <= x2 && y <= y2){
        if (maze.blocks(x.toInt)(y.toInt)==true) return false
        y += dir._2
        x += dir._1
      }
    }
    else if (x1 <= x2 && y1 >= y2){
      while(x <= x2 && y >= y2){
        if (maze.blocks(x.toInt)(y.toInt)==true) return false
        y += dir._2
        x += dir._1
      }
    }
    else if (x1 >= x2 && y1 <= y2){
      while(x >= x2 && y <= y2){
        if (maze.blocks(x.toInt)(y.toInt)==true) return false
        y += dir._2
        x += dir._1
      }
    }
    else {
      while(x >= x2 && y >= y2){
        if (maze.blocks(x.toInt)(y.toInt)==true) return false
        y += dir._2
        x += dir._1
      }
    }

    if (maze.blocks(x2.toInt)(y2.toInt)==true) return false

    return true
  }

  def writeToFile(fname:String): Unit ={
    //output the graph to a file for use in unity visualizer
    val f = new PrintWriter(new File(fname))
    for(i<- 0 to maxID){
      if (nodes.contains(i)){
        val value: Node = nodes.get(i).get//force unwrap optional
        f.write("Node: " + value.ID + " " + value.pos._1 + " " + value.pos._2 + "\n")
        val connIDs = value.connectedNodes.map(n=>n.ID).toList;
        connIDs.foreach{id => f.write(id.toString + " ")}
        f.write("\n")
      }
    }
    f.close()
  }

}
