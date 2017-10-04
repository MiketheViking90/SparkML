package scalaexercises.stdlib.sets

object SetUses {

  def setAdditionRemove(): Unit = {
    val states = Set("MI", "OH", "WI", "MN")
    println(states.size)

    val moreStates = states + "WA"

    val newStates = states -- Set("MI", "OH")
    val newStates1 = states - ("MI", "OH")

    val newStates2 = states - "NY"
    print(states.equals(newStates2))
  }

  def setOperations(): Unit = {

  }


  def main(args: Array[String]): Unit = {
    setOperations()
  }

}
