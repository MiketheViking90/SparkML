package sparkml.kaggle.titanic

import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer, VectorAssembler}
import org.apache.spark.sql.{DataFrame, SparkSession}
import sparkml.SparkUtils

object TitanicML {

  val spark: SparkSession = SparkUtils.getSpark
  val trainDataFilePath = "data/titanic/train.csv"
  val testDataFilePath = "data/titanic/test.csv"

  def doML(): Unit = {
    val model = getModel(trainDataFilePath)

    val test = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(testDataFilePath)
      .na.fill(-1, Array("Age"))
      .na.fill("SHIP", Array("Cabin"))
      .na.fill("Z", Array("Embarked"))
    test.show

    val predictions = model.transform(test)
    predictions.show
//    val predictions = model.transform(test)
//    predictions.show
//
//    val output = predictions.select("PassengerId", "prediction")
//    output.write.csv("data/titanic/output")
//    output.show()

//    val evaluator = new BinaryClassificationEvaluator()
//    val accuracy = evaluator.evaluate(predictions)
//    println("Test Error = " + (1.0 - accuracy))
  }

  private def getModel(filePath: String) : PipelineModel = {
    val df = spark.read
        .option("header", true)
        .option("inferSchema", true)
        .csv(filePath)
        .na.fill(-1, Array("Age"))
        .na.fill("SHIP", Array("Cabin"))
        .na.fill("Z", Array("Embarked"))

    val sexIndexer = new StringIndexer()
      .setInputCol("Sex")
      .setOutputCol("SexIndex")
    val sexEncoder = new OneHotEncoder()
      .setInputCol("SexIndex")
      .setOutputCol("sexVector")

    val cabinIndexer = new StringIndexer()
      .setInputCol("Cabin")
      .setOutputCol("CabinIndex")

    val embarkedIndexer = new StringIndexer()
      .setInputCol("Embarked")
      .setOutputCol("EmbarkedIndex")

    val assembler = new VectorAssembler()
      .setInputCols(Array("Pclass", "sexVector", "Age", "SibSp", "Parch", "Fare", "CabinIndex", "EmbarkedIndex"))
        .setOutputCol("features")
    val model = new RandomForestClassifier().setLabelCol("Survived").setMaxBins(150)


    val pipeline = new Pipeline().setStages(Array(sexIndexer, sexEncoder, cabinIndexer, embarkedIndexer, assembler, model))
    pipeline.fit(df)
  }

  def main(args: Array[String]): Unit = {
    doML()
  }

}
