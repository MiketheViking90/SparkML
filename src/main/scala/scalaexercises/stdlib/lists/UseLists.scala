package scalaexercises.stdlib.lists

object UseLists {

  def listEquality(): Unit = {
    val list1 = List(1, 2, 3)
    val list2 = List(1, 2, 3)

    print(list1.eq(list2)) //test reference
    print(list1 == list2) // test equality of content
  }

  def nilEquality(): Unit = {
    val list1: List[String] = Nil
    val list2: List[Int] = Nil

    print(list1 == list2)
    print(list1.eq(list2))

  }

  def headTail(): Unit = {
    val list1 = List(1, 2, 3)
    val head = list1.head
    val someHead = list1.headOption
    val tail = list1.tail

    println(head)
    println(someHead)
    println(tail)

    list1(0)
    list1(2)
  }

  def filterLists(): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7)
    val filtered = list.filterNot(v => v == 5)

    println(filtered)
  }

  def listUtilities(): Unit = {
    val list1 = List(1, 3, 5, 7, 9)

    println(list1.length)
    println(list1.reverse)

    val list2 = list1.map(v => v * 2)
    println(list2)

    val evenFilter = (v: Int) => (v % 2 != 0)
    println(list2.filter(evenFilter))
  }

  def listReduce(): Unit = {
    val a = List(1, 3, 5, 7)
    val sum = a.reduceLeft(_ + _)
    val prod = a.reduceLeft(_ * _)
    val diff = a.reduceLeft(_ - _)

    println(sum, prod, diff)
  }

  def listConcat(): Unit = {
    val head = List(1, 3)
    val tail = List(5, 7)

    println(head:::tail)
    println(head:::Nil)
  }

  def main(args: Array[String]): Unit = {
    listConcat()
  }

}
