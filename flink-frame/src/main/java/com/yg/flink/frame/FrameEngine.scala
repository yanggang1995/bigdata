//package com.yg.flink.frame
//
//import java.util
//
//import com.google.gson.{Gson, JsonObject}
//import com.yg.flink.frame.conf.ApiHelper
//import com.yg.flink.frame.core.EngineCore
//import com.yg.flink.frame.util.FunctionUtil
//import org.apache.avro.generic.GenericRecord
//import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
//
//import scala.collection.mutable
//import scala.collection.JavaConverters._
//
///**
// * TODO
// *
// * @author Y.G
// * @date 2020/5/8 10:23
// **/
//object FrameEngine {
//
//
//  private def engine() = {
//    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
//    EngineCore.builder[String, GenericRecord](env)
//      .addSource(ApiHelper.loadConf("opt.source.class"))
//      .addSink(ApiHelper.loadConf("opt.sink.class"))
//      .build().execute("")
//  }
//
//  def main(args: Array[String]): Unit = {
//    engine()
//  }
//}
