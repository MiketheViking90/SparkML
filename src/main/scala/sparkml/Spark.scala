package sparkml

import org.apache.spark.sql.SparkSession

object Spark {

  def getSpark: SparkSession = {
    SparkSession.builder()
      .appName("Local SparkSession")
      .master("local[*]")
      .getOrCreate()
  }

}
