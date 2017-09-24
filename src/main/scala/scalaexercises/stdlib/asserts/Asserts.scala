package scalaexercises.stdlib.asserts

object Asserts extends App {

  def assertFailException(): Unit = {
    val left = 1
    val right = 2
    assert(left == right)
  }

  assertFailException()

}
