package scalaexercises.stdlib.maps

object MapUses {

  var map = Map(
    "MI" → "Michigan",
    "OH" → "Ohio",
    "WI" → "Wisconsin",
    "IA" → "Iowa"
  )

  def mapUniqueKeys() {
    map += ("IL" -> "Illinois")
    println(map)
  }

  def mapValues() {
    var mapVals = map.values

    println(mapVals.tail)
  }

  def mapDefaults() {
    val map = Map(1 -> "a", 2 -> "b") withDefaultValue "n/a"
    val map2 = Map(1 -> "a", 2 -> "b")

    val v1 = map(4)
    println(map(23))

    println(map2.getOrElse(4, "none"))
  }

  def mapRemoves() {

  }

  def main(args: Array[String]): Unit = {
    mapDefaults()
  }
}
