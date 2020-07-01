//package com.yg.flink.frame.core
//
//import com.yg.flink.frame.core.sink.ISink
//import com.yg.flink.frame.core.source.ISource
//import com.yg.flink.frame.util.FunctionUtil
//import org.apache.flink.streaming.api.functions.source.SourceFunction
//import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
//import org.apache.flink.api.scala._
//import org.apache.flink.streaming.api.functions.sink.SinkFunction
//
///**
// * TODO
// *
// * @author Y.G
// * @date 2020/5/8 13:46
// **/
//class EngineCore[U, T](env: StreamExecutionEnvironment) {
//  private var iSource :  ISource[U] = _
//  private var iSink : ISink[U, T] = _
//
//  def execute(taskName:String):Unit = {
//    env.addSource(iSource.flinkSouce())
//        .keyBy(0)
//        .map(k => iSink.transform(k))
//        .addSink(iSink.flinkSink)
//    env.execute(taskName)
//  }
//}
//
//object EngineCore{
//  def builder[U, T](env: StreamExecutionEnvironment): Builder[U, T] = {
//    new Builder[U, T](env)
//  }
//
//  class Builder[U, T](env: StreamExecutionEnvironment){
//    private val engineCore = new EngineCore(env)
//
//    def addSource(sourceClass : String) : Builder[U, T] = {
//      engineCore.iSource = FunctionUtil.loadObjWithClass(sourceClass).asInstanceOf[ISource[U]]
//      this
//    }
//
//    def addSink(sinkClass : String) : Builder[U, T] = {
//      engineCore.iSink = FunctionUtil.loadObjWithClass(sinkClass).asInstanceOf[ISink[U, T]]
//      this
//    }
//
//    def build() : EngineCore[U, T] = engineCore
//  }
//}