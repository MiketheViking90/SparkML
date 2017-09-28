package sparkml

import org.apache.spark.sql.SparkSession

object Spark {

  def getSpark: SparkSession = {
    val spark = SparkSession.builder()
      .appName("Local SparkSession")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")
    spark
  }

}
