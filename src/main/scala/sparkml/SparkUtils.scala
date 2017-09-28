package sparkml

import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkUtils {

  def getSpark: SparkSession = {
    val spark = SparkSession.builder()
      .appName("Local SparkSession")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")
    spark
  }

  def printNulls(df: DataFrame): Unit = {
    for (colName <- df.columns) {
      val col = df(colName)
      val cnt = df.select(colName).filter(col.isNull || col.isNaN || col === "").count

      println(col, cnt)
    }
  }

}
