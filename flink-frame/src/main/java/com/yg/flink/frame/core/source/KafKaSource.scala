package com.yg.flink.frame.core.source
import java.net.URL
import java.util.Properties

import com.yg.flink.frame.conf.ApiHelper
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/5/8 15:50
 **/
class KafKaSource extends ISource [String]{
  /**
   * 获取flink支持的Source
   *
   * @return source
   */
  override def flinkSouce(): SourceFunction[String] = {
    val prop = new Properties()
    prop.setProperty("bootstrap.servers", ApiHelper.loadConf("opt.source.bootstrap.servers"))
    prop.setProperty("group.id", ApiHelper.loadConf("opt.source.group.id"))
    new FlinkKafkaConsumer[String]( ApiHelper.loadConf("opt.source.topic"), new SimpleStringSchema(), prop)
  }
}
