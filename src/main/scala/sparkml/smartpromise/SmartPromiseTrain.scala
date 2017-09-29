package sparkml.smartpromise

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType
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

    val weightCol = df("WEIGHT")
    val dfBucketized = df.withColumn("ZIP3_O", (df("zip_o")/100).cast(to = IntegerType))
      .withColumn("ZIP3_D", (df("zip_d")/100).cast(to = IntegerType))
      .withColumn("WEIGHT_BAND",
        when(weightCol <= 0, "NO_WEIGHT")
          .otherwise(when(weightCol.between(0, 19.99), "SORTABLE")
            .otherwise(when(weightCol.between(19.99, 150), "NON-SORTABLE")
              .otherwise(when(weightCol.geq(150)
                .or(df("WIDTH").geq(108))
                .or(df("LENGTH").geq(108))
                .or(df("HEIGHT").geq(108)), "HEAVY_BULKY")
                .otherwise("NO_WEIGHT")))))

    dfBucketized.printSchema()
    dfBucketized.show
  }
}
