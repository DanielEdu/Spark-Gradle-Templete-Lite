# _Spark-Gradle-Template-Lite_
A barebones project with scala, apache spark built using gradle. Spark-shell provides `spark` and `sc` variables pre-initialised.

## Prerequisites
- [Java 1.8](https://java.com/en/download/)
- [Gradle](https://gradle.org/)
- [Scala 2.11.12](https://www.scala-lang.org/)

## Build and Demo process

### Build
`./gradlew clean build`
### Run
`./gradlew run`
### All Together
`./gradlew clean run`


## What the demo does?
Take a look at *src->main->scala->template->spark* directory

We have two Items here. 

The trait `InitSpark` which is extended by any class that wants to run spark code. This trait has all the code for initialization. I have also supressed the logging to only error levels for less noise.

The file `Main.scala` has the executable class `Main`. 
In this class, I do 4 things

- Print spark version.
- Find sum from 1 to 100 (inclusive).
- Read a csv file into a structured `DataSet`. 
- Find average age of persons from the csv.

**InitSpark.scala**
```scala
trait InitSpark {
  val spark: SparkSession = SparkSession.builder().appName("Spark example").master("local[*]")
                            .config("spark.some.config.option", "some-value").getOrCreate()
  val sc = spark.sparkContext
  val sqlContext = spark.sqlContext
  def reader = spark.read.option("header",true).option("inferSchema", true).option("mode", "DROPMALFORMED")
  def readerWithoutHeader = spark.read.option("header",true).option("inferSchema", true).option("mode", "DROPMALFORMED")
  private def init = {
    sc.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)
    LogManager.getRootLogger.setLevel(Level.ERROR)
  }
  init
  def close = {
    spark.close()
  }
}
```

**Main.scala**
```scala
final case class Person(firstName: String, lastName: String, country: String, age: Int)

object Main extends InitSpark {

  def main(args: Array[String]): Unit = {
    import spark.implicits._

    val sparkVersion = spark.version
    val scalaVersion = util.Properties.versionNumberString

    println("SPARK VERSION = " + sparkVersion)
    println("SCALA VERSION = " + scalaVersion)

    val listRdd = spark.range(1, 101)
    println("Sum 1 to 100 using binary : "+listRdd.reduce(_ + _))

    println("Reading from csv file: people-example.csv")
    val persons = reader.csv("people-example.csv").as[Person]
    persons.show(2,false)

    val averageAge = persons.agg(avg("age"))
                     .first.get(0).asInstanceOf[Double]
    println(f"Average Age: $averageAge%.2f")

    close
  }
}
```

## Using this Repo
Just import it into your favorite IDE as a gradle project. Tested with IntelliJ to work. Or use your favorite editor and build from command line with gradle.

## Libraries Included
- Spark - 2.4.5
- Scala - 2.11.12

## Useful Links
- [Spark Docs - Root Page](http://spark.apache.org/docs/latest/)
- [Spark Programming Guide](http://spark.apache.org/docs/latest/programming-guide.html)
- [Spark Latest API docs](http://spark.apache.org/docs/latest/api/)
- [Scala API Docs](https://docs.scala-lang.org/getting-started/index.html)
 
