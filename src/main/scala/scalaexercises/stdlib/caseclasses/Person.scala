package scalaexercises.stdlib.caseclasses

case class Person(firstName: String, lastName: String, age: Int = 0, ssn: String = "")

object PersonTest {
  def defaultValues(): Unit = {
    val p1 = Person("Fred", "Jones", 23, "111-22-3333")
    val p2 = Person("Samantha", "Jones")
    val p3 = Person("Jones", "Fred", ssn = "111-22-3333")
    val p4 = p3.copy(age = 23)

    println(p1)
    println(p2)
    println(p3)
    println(p4)
  }

  def disassemble(): Unit = {
    val p1 = Person(lastName = "Fred", firstName = "Jones", ssn = "111-22-3333", age = 23)
    val parts = Person.unapply(p1).get

    println(parts._1)
    println(parts._2)
    println(parts._3)
    println(parts._4)

  }

  def main(args: Array[String]): Unit = {
    disassemble()
  }

}