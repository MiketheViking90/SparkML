package scalaexercises.stdlib.options

object OptionTest {

  def getOptionString(flag: Boolean): Option[String] = {
    if (flag) {
      Some("test")
    } else {
      None
    }
  }

  def testOption(): Unit = {
    val someValue: Option[String] = None
    println(someValue)

    val val1 = getOptionString(true)
    val val2 = getOptionString(false)

    println(val1)
    println(val2)
  }

  def testEmpty(): Unit = {
    val val1 = getOptionString(true)
    val val2 = getOptionString(false)

    println(val1.isEmpty)
    println(val2.isEmpty)
  }

  def testPatternMatching(): Unit = {
    val val1: Option[Double] = Some(1.0)
    val val2: Option[Double] = None


    val value = val2 match {
      case Some(v) => v
      case None => 0.0
    }

    println(value)
  }

  def testMap(): Unit = {
    val number: Option[Double] = Some(1.0)
    val noNumber: Option[Double] = None

    val numberProd = number.map(_ * 1.5)
    val noNumberProd = noNumber.map(_ * 1.5)

    println(numberProd)
    println(noNumberProd)
  }

  def testFold(): Unit = {
    val number: Option[Double] = Some(3.0)
    val noNumber: Option[Double] = None

    val numberFold = number.fold(1.0)(_ * 3)
    val noNumberFold = noNumber.fold(1.0)(_ * 1.5)

    println(numberFold)
    println(noNumberFold)
  }

  def main(args: Array[String]): Unit = {
    testMap()
  }

}