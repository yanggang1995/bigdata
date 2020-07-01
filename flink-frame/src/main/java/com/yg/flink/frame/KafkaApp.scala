package com.yg.flink.frame

import java.util.Properties

import com.google.gson.{JsonObject, JsonParser}
import com.yg.flink.frame.conf.ApiHelper
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.scala._
import org.apache.flink.core.fs.Path
import org.apache.flink.formats.parquet.avro.ParquetAvroWriters
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy
import org.apache.flink.streaming.api.functions.sink.filesystem.{OutputFileConfig, StreamingFileSink}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/5/7 16:05
 **/
object KafkaApp {

  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 每 1000ms 开始一次 checkpoint
    env.enableCheckpointing(10 * 60 * 1000L)
    val prop = new Properties()
    prop.setProperty("bootstrap.servers", ApiHelper.loadConf("opt.source.bootstrap.servers"))
    prop.setProperty("group.id", "yg_kafka_1")
    val kafkaStream: DataStream[String] = env.addSource(new FlinkKafkaConsumer[String](ApiHelper.loadConf("opt.source.topic"), new SimpleStringSchema(), prop))
    kafkaStream.print()

    val builder = StreamingFileSink
      .forBulkFormat(new Path(ApiHelper.loadConf("opt.sink.path")), ParquetAvroWriters.forReflectRecord(classOf[DbMovie]))
    builder.withRollingPolicy(OnCheckpointRollingPolicy.build())
      .withOutputFileConfig(OutputFileConfig.builder()
        .withPartPrefix("yg")
        .withPartSuffix(".parquet")
        .build())

    val parquetSink: StreamingFileSink[DbMovie] = builder.build()
    kafkaStream
      .map(v => {
        val jsonObject: JsonObject = JsonParser.parseString(v).getAsJsonObject
        DbMovie(jsonObject.get("cover").getAsString, jsonObject.get("title").getAsString)
      })
      .filter(k => k != null)
      .addSink(parquetSink)

    env.execute("kafka_yg")
  }

  case class DbMovie(cover:String, title:String)
}
