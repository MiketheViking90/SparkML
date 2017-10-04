package scalaexercises.stdlib.formatting

object FormattingUses {

  def specifiers(): Unit = {
    val str = "String here"
    val formatted = "s%s".format(str)
    println(formatted)

    val c = 'z'
    val charSpecifier = "My favorite letter is %c".format(c)
    println(charSpecifier)

    val c1 = 'a'
    val c2 = '\141'
    val c3 = '\"'
    val c4 = '\\'

    println("%c".format(c1))
    println("%c".format(c2))
    println("%c".format(c3))
    println("%c".format(c4))

    val j = 200
    println("%d bottles of %s" format (j-20, "beer"))
  }

  def main(args: Array[String]): Unit = {
    specifiers()
  }

}
