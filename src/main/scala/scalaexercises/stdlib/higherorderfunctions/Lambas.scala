package scalaexercises.stdlib.higherorderfunctions

object Lambas extends App {

  def firstLambdas {
    def lambda1 = (x:Int) => x + 1
    def lambda2 = (x:Int) => x + 1
    val lambda3 = (x:Int) => x + 1

    val lambda4 = new Function[Int, Int] {
      override def apply(v1: Int): Int = v1 - 1
    }

    println(lambda3(3))
    println(lambda4(3))

    val multiplierLambda = (i: Int) => i*10
  }

  def closures {
    var multiplicand = 12;

    val closure = (i: Int) => i * multiplicand

    println(closure(13))
  }

  def higherOrderFunctions {
    def summation(x: Int, y: Int => Int): Int = y(x)
    def plus3 = (i: Int) => i+3

    println(summation(12, plus3))

  }

  def hofReturnFunctions {
    def add(x: Int): Function[Int, Int] = {
      new Function[Int, Int]() {
        override def apply(y: Int): Int = x + y
      }
    }
    println(add(5))

    def addFunction = add(5)
    println(addFunction.isInstanceOf[Function[_,_]])
  }

  def functionComposition {
    def makeUpper = (strings: List[String]) =>
      strings map { _.toUpperCase() }

    val strings = List("abc", "xyz", "123")
    println(makeUpper(strings))

    def transformStrings(xs: List[String], sideEffect: String => String) = xs map sideEffect
    val myNameIs = (name: String) => s"My name is $name"
    println(transformStrings(List("Joe", "Mark"), myNameIs))
  }

  functionComposition

}
