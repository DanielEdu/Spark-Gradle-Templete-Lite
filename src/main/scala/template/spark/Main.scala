package template.spark

import org.apache.spark.sql.functions._
import template.spark.utils.InitSpark

final case class Person(firstName: String, lastName: String,
                        country: String, age: Int)

object Main extends InitSpark {

  def main(args: Array[String]): Unit = {
    import spark.implicits._

    val sparkVersion = spark.version
    val scalaVersion = util.Properties.versionNumberString
    val javaVersionn = java.lang.Runtime.version()


    println("SPARK VERSION = " + sparkVersion)
    println("SCALA VERSION = " + scalaVersion)
    println("JAVA  VERSION = " + javaVersionn)


    val listRdd = spark.range(1, 101)
    println("Sum 1 to 100 using binary : "+listRdd.reduce(_ + _))



    println("Reading from csv file: people-example.csv")
    val persons = reader.csv("people-example.csv").as[Person]
    persons.show(2,truncate = false)

    val averageAge = persons.agg(avg("age"))
                     .first.get(0).asInstanceOf[Double]
    println(f"Average Age: $averageAge%.2f")

    close()
  }
}
