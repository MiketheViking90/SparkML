package scalaexercises.stdlib.partiallyappliedfunctions

object partialApply {

  def sum(a: Int, b: Int, c: Int): Int = {
    a + b + c
  }

  def multiply(a: Int, b: Int): Int = {
    a * b
  }

  def basicUse(): Unit = {
    val sum3 = sum _
    val v1 = sum(1, 2, 5)
    val v2 = sum(3, 7, 2)

    val sumC = sum(1, 10, _: Int)
    val v3 = sumC(12)
  }

  def curry(): Unit = {
    val multC = (multiply _).curried

    val prod = multC(7)(2)

    val multC4 = multC(4)
    val prod1 = multC4(2)
    val prod2 = multC4(4)

    println(prod, prod1, prod2)
  }

  def filterList(f: Int => Boolean) (nums: List[Int]) = nums.filter(f)

  def useFilter(): Unit = {
    val nums = Range(0, 12).toList

    val evens = filterList(v => (v%2) == 0)(nums)
    val odds = filterList(v => v%2 == 1)(nums)

    println(evens)
    println(odds)
  }

  def main(args: Array[String]): Unit = {
    useFilter()
  }

}
