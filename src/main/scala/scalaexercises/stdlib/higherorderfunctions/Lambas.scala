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

  }

  firstLambdas

}
