package com.yg.spark.data

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/6/22 14:38
 **/
object AppMain {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .appName("SparkSqlDemo")
      .config("spark.some.config.option", "some-value")

      .getOrCreate()
    val ckDf: DataFrame = spark.read.format("jdbc")
      .option("driver", "ru.yandex.clickhouse.ClickHouseDriver")
      .option("url", "jdbc:clickhouse://192.168.90.77:8123/starcor")
      .option("dbtable","(select id,name,day from yg_test.t_test where day>=20200619) as yg_test")
      .load()

    import spark.implicits._
    val ckDf2: DataFrame = ckDf.map(v => (v.getString(0), v.getString(1), v.getAs("day").toString.toLong)).toDF("ids","names","day")
    ckDf2.show()

    ckDf2.write.mode(SaveMode.Append).format("jdbc")
      .option("driver", "ru.yandex.clickhouse.ClickHouseDriver")
      .option("url", "jdbc:clickhouse://192.168.90.77:8123/starcor")
      .option("dbtable","yg_test.t_test2")
      .save()
  }

//  case class Test2(ids:String, names:String, day:Long)
}
