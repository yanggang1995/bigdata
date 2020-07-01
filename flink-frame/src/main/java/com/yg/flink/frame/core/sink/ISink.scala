package com.yg.flink.frame.core.sink

import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.streaming.api.scala.DataStream

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/5/8 14:46
 **/
trait ISink[U, T] {

  def transform(data : U) : T

  def flinkSink: SinkFunction[T]
}
