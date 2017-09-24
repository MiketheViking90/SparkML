package scalaexercises.stdlib.tuples

object TupleExample extends App {

  def useTuples() {
    val tuple1 = (1.0, "dog", 52)
    val tuple2 = ("apple", "dog")

    val v1 = tuple2._1
    val v2 = tuple2._2

    println(v1 + ", " + v2)

    val (age, name, weight) = tuple1
    println(age + ", " + name + ", " + weight)

    val swapped = ("apple", "dog").swap
    println(swapped)
  }

  useTuples()
}
