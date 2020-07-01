package com.yg.flink.frame.util


import java.lang.reflect.InvocationTargetException
import scala.reflect.ClassTag

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/5/8 14:06
 **/
object FunctionUtil {
  def loadObjWithClass(className:String):Any = {
    val clazz: Class[_] = Class.forName(className)
    clazz.newInstance()
  }
}
