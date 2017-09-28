package sparkml

import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer, VectorAssembler}

object TitanicML {

  val spark = Spark.getSpark
  val trainDataFilePath = "data/titanic/train.csv"

  def doML: Unit = {
    val df = spark.read
        .option("header", true)
        .option("inferSchema", true)
        .csv(trainDataFilePath)

    df.printSchema()
    for (colName <- df.columns) {
      val col = df(colName)
      val cnt = df.filter(col.isNaN || col.isNull || col === "").count()

      println(colName, cnt)
    }

//    val sexIndexer = new StringIndexer()
//      .setInputCol("Sex")
//      .setOutputCol("SexIndex")
//    val sexEncoder = new OneHotEncoder()
//      .setInputCol("SexIndex")
//      .setOutputCol("sexVector")
//
//    val cabinIndexer = new StringIndexer()
//      .setInputCol("Cabin")
//      .setOutputCol("CabinIndex")
//
//    val embarkedIndexer = new StringIndexer()
//      .setInputCol("Embarked")
//      .setOutputCol("EmbarkedIndex")
//
//    val assembler = new VectorAssembler()
//      .setInputCols(Array("Pclass", "sexVector", "Age", "SibSp", "Parch", "Fare", "CabinIndex", "EmbarkedIndex"))
//        .setOutputCol("features")
//
//    val pipeline = new Pipeline().setStages(Array(sexIndexer, sexEncoder, cabinIndexer, embarkedIndexer, assembler))
//    val output = pipeline.fit(df).transform(df)
//    output.show()
  }

  def main(args: Array[String]): Unit = {
    doML
  }

}
