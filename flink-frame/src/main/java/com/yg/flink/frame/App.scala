package com.yg.flink.frame

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time


/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/30 23:18
 **/
object App {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("192.168.90.96", 9999)

    val counts = text
      .flatMap(w => w.split("\\s"))
      .map(w => WordWithCount(w, 1))
      .keyBy("word")
      .timeWindow(Time.seconds(5))
      .sum("count")

    counts.print()

    env.execute("Socket Window WordCount")
  }

  case class WordWithCount(word: String, count: Long)

}
