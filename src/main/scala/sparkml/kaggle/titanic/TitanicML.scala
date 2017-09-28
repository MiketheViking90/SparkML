package sparkml.kaggle.titanic

import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer, VectorAssembler}
import org.apache.spark.sql.DataFrame
import sparkml.SparkUtils

object TitanicML {

  val spark = SparkUtils.getSpark
  val trainDataFilePath = "data/titanic/train.csv"
  val testDataFilePath = "data/titanic/test.csv"

  def doML(): Unit = {
    val training = getData(trainDataFilePath)
    val model = new RandomForestClassifier().setMaxBins(150).fit(training)

    val test = getData(testDataFilePath)
    val predictions = model.transform(test)

    val evaluator = new BinaryClassificationEvaluator()
    val accuracy = evaluator.evaluate(predictions)
    println("Test Error = " + (1.0 - accuracy))
  }

  private def getData(filePath: String) : DataFrame = {
    val df = spark.read
        .option("header", true)
        .option("inferSchema", true)
        .csv(trainDataFilePath)
        .na.fill(-1, Array("Age"))
        .na.fill("SHIP", Array("Cabin"))
        .na.fill("Z", Array("Embarked"))
        .withColumnRenamed("Survived", "label")

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

    val pipeline = new Pipeline().setStages(Array(sexIndexer, sexEncoder, cabinIndexer, embarkedIndexer, assembler))
    pipeline.fit(df).transform(df).select("features", "label")
  }

  def main(args: Array[String]): Unit = {
    doML
  }

}
