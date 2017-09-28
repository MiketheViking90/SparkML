package sparkml

object TitanicML {

  def doML: Unit = {
    val spark = Spark.getSpark
    println("Get Spark")
  }

  def main(args: Array[String]): Unit = {
    doML
  }

}
