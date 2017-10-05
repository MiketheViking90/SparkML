package scalaexercises.stdlib.caseclasses

object CaseClassUses {

  abstract class Term
  case class Var(name: String) extends Term
  case class Fun(arg: String, body: Term) extends Term
  case class App(f: Term, v: Term) extends Term

  def caseClassEqToString(): Unit = {
    val val1 = Var("jim")
    val val2 = Var("jim")
    val val3 = Var("joe")

    println(val1, val2, " => " + val1 == val2)
    println(val1, val3, " => " + val1 == val3)
    println(val2, val3, " => " + val2 == val3)
  }

  def termTest() {
    def printTerm(term: Term) {
      term match {
        case Var(n) =>
          print(n)
        case Fun(x, b) =>
          print("^" + x + ".")
          printTerm(b)
        case App(f, v) =>
          print("(")
          printTerm(f)
          print(" ")
          printTerm(v)
          print(")")
      }
    }

    def isIdentityFun(term: Term): Boolean = term match {
      case Fun(x, Var(y)) if x == y => true
      case _ => false
    }

    val id = Fun("x", Var("x"))
    val t = Fun("x", Fun("y", App(Var("x"), Var("y"))))
    printTerm(t)
    println
    println(isIdentityFun(id))
    println(isIdentityFun(t))
  }

  def main(args: Array[String]): Unit = {
    caseClassEqToString()
  }
}
