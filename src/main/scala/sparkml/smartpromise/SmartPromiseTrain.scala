package sparkml.smartpromise

import sparkml.SparkUtils

object SmartPromiseTrain {

  val spark = SparkUtils.getSpark
  val dataFilePath = "data/smartpromise/data.txt"

  def main(args: Array[String]): Unit = {
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .option("delimiter", "\t")
      .csv(dataFilePath)
      .na.fill(0, Array("WEIGHT", "WIDTH", "HEIGHT", "LENGTH"))

    df.printSchema()
    df.show

  }
}
