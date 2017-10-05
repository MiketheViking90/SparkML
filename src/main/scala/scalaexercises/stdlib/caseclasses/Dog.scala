package scalaexercises.stdlib.caseclasses

case class Dog(name: String, breed: String)

object DogTest {

  def dogTest(): Unit = {
    val d1 = Dog("Ein", "Corgi")
    val d2 = Dog("Joe", "Spaniel")

    println(d1)
  }

  def caseClassMutation(): Unit = {
    val d1 = Dog("Ein", "Corgi")
    val d2 = d1.copy(name = "Zwei")

    println(d1, d2)
  }

  def main(args: Array[String]): Unit = {
    caseClassMutation()
  }

}