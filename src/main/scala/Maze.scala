import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by BOWMANRS1 on 3/4/2016.
  */
class Maze(var fname: String) {
  var blocks = ListBuffer[ListBuffer[Boolean]]()
  for (line <- Source.fromFile(fname).getLines()){
    var row = ListBuffer[Boolean]()
    line.foreach(c => row += (c.equals('#')))
    blocks += row}
}
