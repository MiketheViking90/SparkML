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
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")

    val intersect = mySet1 & mySet2
    println(intersect)

    val union = mySet1 | mySet2
    println(union)

    println(union subsetOf mySet1)

    println(mySet1 diff mySet2)

    println(intersect.equals(Set("Michigan", "Wisconsin")))
  }


  def main(args: Array[String]): Unit = {
    setOperations()
  }

}
