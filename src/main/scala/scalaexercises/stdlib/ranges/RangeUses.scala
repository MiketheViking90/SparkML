package scalaexercises.stdlib.ranges

object RangeUses {

  def makeRanges(): Unit = {
    val nums1 = Range(0, 10)
    val nums2 = nums1(1)
    val nums3 = nums1.last

    val nums4 = 0 until 10
    println(nums1 == nums4)
  }

  def main(args: Array[String]): Unit = {
    makeRanges()

    println(3f)

    val f = 93e-9
    val g = 93E-9

    println(f, g)
  }

}
