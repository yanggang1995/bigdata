package com.yg.flink.frame.conf

import java.io.{BufferedReader, InputStreamReader}

import com.google.gson.{JsonArray, JsonElement, JsonObject, JsonParser}

import scala.collection.mutable
import scala.collection.JavaConverters._

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/5/8 10:26
 **/
private class ApiHelper {
  private var frameJson:JsonObject = _
  private val flatJsonMap:mutable.HashMap[String, String] = mutable.HashMap()

  def init():Unit = {
    frameJson = JsonParser.parseReader(new InputStreamReader(this.getClass.getResourceAsStream("/frame.json"), "UTF-8")).asInstanceOf[JsonObject]
    flatten(frameJson, "")
    println("初始化成功")
  }

  private def flatten(jsonObject: JsonObject, parentKey:String) : Unit = {
    var nowKey = parentKey
    if(!"".equals(nowKey)){
      nowKey = nowKey.concat(".")
    }
    jsonObject.entrySet().forEach(entry => {
      val key:String = entry.getKey
      val value: JsonElement = entry.getValue
      if(value.isJsonObject){
        flatten(value.getAsJsonObject, nowKey.concat(key))
      }else if(value.isJsonArray){
        val array: JsonArray = value.getAsJsonArray
        for(var1 <- 0 until array.size()){
          flatten(array.get(var1).getAsJsonObject, String.format("%s%s.%s", nowKey, key, String.valueOf(var1)))
        }
      }else {
        flatJsonMap += (nowKey.concat(key) -> entry.getValue.getAsString)
      }
    })
  }
}

object ApiHelper{
  private val apiHelper = new ApiHelper
  apiHelper.init()
  def loadConf(flatKey:String) : String = apiHelper.flatJsonMap.getOrElse(flatKey, "")
  def loadJson() : JsonObject = apiHelper.frameJson
}