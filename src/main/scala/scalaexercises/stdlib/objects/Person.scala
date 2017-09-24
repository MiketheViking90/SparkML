package scalaexercises.stdlib.objects

class Person(name: String, private val superheroName: String)

object Person {
  def showMeSecret(person: Person) : String = {
    person.superheroName
  }

  def main(args: Array[String]): Unit = {
    val clark = new Person("Clark Kent", "Superman")
    val peter = new Person("Peter Parker", "Spiderman")

    println(Person.showMeSecret(clark))
    println(Person.showMeSecret(peter))
  }
}
