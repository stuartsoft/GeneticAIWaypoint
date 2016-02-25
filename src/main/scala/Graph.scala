/**
  * Created by BOWMANRS1 on 2/17/2016.
  */
class Graph {
  class Node(x: Int, y: Int){//constructor with xy position
    def this() = this(0,0)//blank constructor
    var connectedNodes: List[Graph#Node] = Nil
    var pos = (x,y)

    def connectTo(node: Graph#Node): Unit ={
      if (connectedNodes.find(node.equals).isEmpty){
        connectedNodes = node :: connectedNodes //append the node to the list of connected nodes
        //TODO also add this node to the other node's list of connections
      }
    }
  }

  var nodes: List[Node] = Nil
  def newNode: Node = {
    val res = new Node
    nodes = res :: nodes
    res
  }

}
