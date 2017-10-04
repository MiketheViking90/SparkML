package scalaexercises.stdlib.patternmatching

object PatternMatchingUses {

  def matchIntToString(x: Int): String = x match {
    case 1 => "One"
    case 2 => "Two"
    case _ => "None"
  }

  def match1(): Unit = {
    val m1 = matchIntToString(1)
    val m2 = matchIntToString(2)
    val m3 = matchIntToString(3)

    println(m1, m2, m3)
  }

  def matchColor(): Unit = {
    val color = "red"

    val colorVal = color match {
      case "blue" =>
        println("Color is blue"); 1
      case "red" =>
        println("Color is red"); 2
      case _ =>
        println("No color"); 3
    }
  }

  def matchColorToRbg(color: String): (Int, Int, Int) = {
    color match {
      case "red" => (255, 0, 0)
      case "green" => (0, 255, 0)
      case "blue" => (0, 0, 255)
      case _ => (0, 0, 0)
    }
  }

  def matchOnLine(i: Int, j: Int) = i match {
    case `j` =>
      println("is match") ; true
    case _ =>
      println("no match"); false
  }

  def matchList(): Unit = {
    val secondElement = List(1, 2, 3) match {
      case x :: xs ⇒ xs.head
      case _ ⇒ 0
    }

    println(secondElement)
  }

  def main(args: Array[String]): Unit = {
    matchList()
  }

}
