package scalaexercises.stdlib.objects

object Greeting {

  def english = "Hi"
  def espanol = "Hola"

  def testGreeting(): Unit = {
    println(Greeting.english)
    println(Greeting.espanol)
  }

  def testReference(): Unit = {
    val a = Greeting
    val b = a
    val c = Greeting

    println(a eq b)
    println(a eq c)
  }

  def main(args: Array[String]): Unit = {
    testReference()
  }
}
