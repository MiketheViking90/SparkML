package sparkml

object TitanicML {

  val spark = Spark.getSpark
  val trainDataFilePath = "data/titanic/train.csv"

  def doML: Unit = {
    val df = spark.read.csv(trainDataFilePath)
    df.show()
  }

  def main(args: Array[String]): Unit = {
    doML
  }

}
./