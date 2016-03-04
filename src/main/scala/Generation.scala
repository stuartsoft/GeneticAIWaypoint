import scala.collection.mutable.ListBuffer

/**
  * Created by BOWMANRS1 on 3/4/2016.
  */
class Generation(var genNum: Int, val predecessors: Option[List[Graph]], val alpha: Int, val beta: Float) {
  var entities = List[Graph]()
  val numEntitiesPerGen = 100
  val finalGenNum = 100

  def onSetup() = {}//inital setup and preparation

  def eval() ={}

  def selection(): List[Graph] = {
    var selected = new ListBuffer[Graph]()
    selected.toList
  }
  //build a list of the selected graphs from the previous generation that should be used to breed to create this generation

  def crossover() = {}

  def mutate() = {}

  def onComplete() = {}//finalize any other details for the generation. Recursively call the next generation to begin breeding again
}
