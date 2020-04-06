package template.spark.utils

import org.apache.log4j.{Level, LogManager, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrameReader, SQLContext, SparkSession}

trait InitSpark {
  val spark: SparkSession = SparkSession.builder()
                            .appName("Spark template")
                            .master("local[*]")
                            .config("option", "some-value")
                            .getOrCreate()

  val sc: SparkContext = spark.sparkContext
  val sqlContext: SQLContext = spark.sqlContext

  def reader: DataFrameReader = spark.read
               .option("header",value = true)
               .option("inferSchema", value = true)
               .option("mode", "DROPMALFORMED")


  def readerWithoutHeader: DataFrameReader = spark.read
                            .option("header",value = false)
                            .option("inferSchema", value = false)
                            .option("mode", "DROPMALFORMED")


  private def init(): Unit = {
    sc.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)
    LogManager.getRootLogger.setLevel(Level.ERROR)
  }


  init()
  def close(): Unit = {
    spark.close()
  }
}
