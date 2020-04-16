package com.yg.leecode._3


/**
 * Y.G
 *
 * 2020-01-16 14:24
 **/
object _20200116_1 {
  def main(args: Array[String]): Unit = {
    println(lengthOfLongestSubstring("abcdpwwkew"))
  }

  def lengthOfLongestSubstring(s: String): Int = {
    val cache = scala.collection.mutable.Buffer[Char]()
    var maxLen = 0
    var startIndex = 0
    var index = 0
    s.toCharArray.foreach(v => {
      if(cache.contains(v)){
        val tmp = cache.lastIndexOf(v)
        if(tmp >= startIndex) {
          maxLen = chooseBigger(index - startIndex, maxLen)
          startIndex = tmp + 1
        }
      }
      cache.append(v)
      index += 1
    })
    chooseBigger(index-startIndex, maxLen)
  }

  def chooseBigger(var1:Int, var2:Int):Int ={
    if(var1 > var2){
      var1
    }else{
      var2
    }
  }
}
