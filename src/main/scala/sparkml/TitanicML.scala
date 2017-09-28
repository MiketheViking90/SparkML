package sparkml

object TitanicML {

  val spark = Spark.getSpark
  val trainDataFilePath = "data/titanic/train.csv"

  def doML: Unit = {
    val df = spark.read
        .option("header", true)
        .option("inferSchema", true)
        .csv(trainDataFilePath)

    df.show()
    df.schema
  }

  def main(args: Array[String]): Unit = {
    doML
  }

}
