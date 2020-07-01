package com.yg.flink.frame.core.source

import org.apache.flink.streaming.api.functions.source.SourceFunction

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/5/8 14:41
 **/
trait ISource[T] {
  /**
   * 获取flink支持的Source
   *
   * @return source
   */
  def flinkSouce(): SourceFunction[T]
}
