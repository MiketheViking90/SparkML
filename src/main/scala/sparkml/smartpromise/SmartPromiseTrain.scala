package sparkml.smartpromise

import org.apache.spark.ml.{Pipeline, Predictor}
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer, VectorAssembler}
import org.apache.spark.ml.regression.{RandomForestRegressionModel, RandomForestRegressor}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType
import sparkml.SparkUtils

object SmartPromiseTrain {

  val spark = SparkUtils.getSpark
  val dataFilePath = "data/smartpromise/data.txt"

  def trainModel(): Unit = {
    val data = getData()
    val Array(train, test) = data.randomSplit(Array(0.7, 0.3))

    val rfModel = getModel("features", "label", train)
    val rfRangeModel = getModel("features", "range", train)

    val predictions = rfModel.transform(test)
    val pred = rfRangeModel.setPredictionCol("predictedRange").transform(predictions)
    val perf = pred.withColumn("ttDiff", pred("prediction") - pred("label"))
      .withColumn("rangeDiff", pred("predictedRange") - pred("range"))

    perf.show(50)

    val evaluator = new RegressionEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("rmse")
    val rmse = evaluator.evaluate(predictions)
    println(rmse)
  }

  private def getModel(features: String, label: String, train: DataFrame): RandomForestRegressionModel = {
    new RandomForestRegressor()
      .setLabelCol(label)
      .setFeaturesCol(features)
      .setMaxBins(100)
      .setNumTrees(50)
      .fit(train)
  }

  private def getData(): DataFrame = {
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .option("delimiter", "\t")
      .csv(dataFilePath)
      .na.fill(0, Array("WEIGHT", "WIDTH", "HEIGHT", "LENGTH"))
      .withColumnRenamed("BUSDELIVERYDAYSS2D", "label")

    val weightCol = df("WEIGHT")
    val dfBucketized = df.withColumn("Zip3_O", (df("zip_o") / 100).cast(to = IntegerType))
      .withColumn("Zip3_D", (df("zip_d") / 100).cast(to = IntegerType))
      .withColumn("WEIGHT_BAND",
        when(weightCol <= 0, "NO_WEIGHT")
          .otherwise(when(weightCol.between(0, 19.99), "SORTABLE")
            .otherwise(when(weightCol.between(19.99, 150), "NON-SORTABLE")
              .otherwise(when(weightCol.geq(150)
                .or(df("WIDTH").geq(108))
                .or(df("LENGTH").geq(108))
                .or(df("HEIGHT").geq(108)), "HEAVY_BULKY")
                .otherwise("NO_WEIGHT")))))
      .withColumn("Range", df("BUSLPROMISEDDELIVERYDAYSS2D") - df("BUSEPROMISEDDELIVERYDAYSS2D"))

    val mediaIndexer = new StringIndexer().setInputCol("MEDIA").setOutputCol("MediaIndex")
    val mediaEncoder = new OneHotEncoder().setInputCol("MediaIndex").setOutputCol("MediaVector")
    val speedCategoryIndexer = new StringIndexer().setInputCol("SPEEDCATEGORY").setOutputCol("SpeedCategoryIndex")
    val glIndexer = new StringIndexer().setInputCol("GL").setOutputCol("GlIndex")
    val orderDayOfWeekIndexer = new StringIndexer().setInputCol("ORDERDAYOFWEEK").setOutputCol("OrderDayOfWeekIndex")
    val shipWeekIndexer = new StringIndexer().setInputCol("SHIPWEEKOFYEAR").setOutputCol("ShipWeekIndex")
    val shipMonthIndexer = new StringIndexer().setInputCol("SHIPMONTH").setOutputCol("ShipMonthIndex")
    val weightBandIndexer = new StringIndexer().setInputCol("WEIGHT_BAND").setOutputCol("WeightBandIndex")
    val assembler = new VectorAssembler()
      .setInputCols(Array("MediaVector", "SpeedCategoryIndex", "GlIndex", "OrderDayOfWeekIndex", "ShipWeekIndex",
        "ShipMonthIndex", "WeightBandIndex"))
      .setOutputCol("features")

    val pipeline = new Pipeline().setStages(Array(mediaIndexer, mediaEncoder, speedCategoryIndexer, glIndexer,
      orderDayOfWeekIndexer, shipWeekIndexer, shipMonthIndexer, weightBandIndexer, assembler))

    pipeline.fit(dfBucketized).transform(dfBucketized).select("features", "label", "range")
  }

  def main(args: Array[String]): Unit = {
    trainModel
  }
}
