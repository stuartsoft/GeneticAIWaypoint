import scala.collection.mutable.ListBuffer

/**
  * Created by BOWMANRS1 on 2/17/2016.
  */
class Graph(initialNodes: Int, val wid: Float, val height: Float) {

  var nodes = Map[Int, Node]()  //an empty hash map for nodes. Maps the node ID to node object for easy lookup

  for(i <- Range(1, initialNodes)) {
    newRandomNode()
  }

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
      print("\nNode "+ value.ID + value.pos + " is connected to\n")
      value.connectedNodes.foreach((mynode)=>print(mynode.ID + " "))
    }
  }

  def newNode(x: Float, y: Float): Node = {
    var topID: Int = 0
    nodes.foreach{ case (key, value) =>
      if (value.ID >= topID)
        topID = value.ID
    }

    val res = new Node(x, y, topID+1)
    nodes += (topID+1 -> res)//add new node to the Map
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

}
