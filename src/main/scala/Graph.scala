/**
  * Created by BOWMANRS1 on 2/17/2016.
  */
class Graph {

  var nodes: List[Node] = Nil

  class Node(x: Int, y: Int, var ID: Int){
    //constructor with xy position
    var connectedNodes: List[Graph#Node] = Nil
    var pos = (x,y)

    def connectTo(node: Graph#Node): Unit ={
      if (connectedNodes.find(node.equals).isEmpty){
        connectedNodes = node :: connectedNodes //append the node to the list of connected nodes
        if (node.connectedNodes.find(this.equals).isEmpty){
          node.connectedNodes = this :: node.connectedNodes //also append this to the other list of connected nodes
        }
      }
    }

  }

  def printAdjMatrix(): Unit = {
    for (n <- nodes){
      print("\nNode "+ n.ID + " is connected to\n")
      n.connectedNodes.foreach((mynode)=>print(mynode.ID + " "))
    }
  }

  def newNode(x: Int, y: Int): Node = {
    var topID: Int = 0
    for (n <- nodes){
      if (n.ID >= topID)
        topID = n.ID
    }

    val res = new Node(x, y, topID+1)
    nodes = res :: nodes
    res
  }

}
