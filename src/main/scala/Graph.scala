/**
  * Created by BOWMANRS1 on 2/17/2016.
  */
class Graph {
  class Node{
    var connectedNodes: List[Node] = Nil

    def connectTo(node: Node): Unit ={
      if (connectedNodes.find(node.equals).isEmpty){
        connectedNodes = node :: connectedNodes //append the node to the list of connected nodes
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
