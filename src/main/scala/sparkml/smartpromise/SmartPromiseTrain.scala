package sparkml.smartpromise

import sparkml.Spark

object SmartPromiseTrain {

  val spark = Spark.getSpark
  val dataFilePath = "data/smartpromise/data.txt"

  def main(args: Array[String]): Unit = {
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(dataFilePath)

    df.show

  }
}
