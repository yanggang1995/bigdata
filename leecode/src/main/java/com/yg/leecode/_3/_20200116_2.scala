package com.yg.leecode._3

/**
 * Y.G
 *
 * 2020-01-16 16:12
 **/
object _20200116_2 {

  def lengthOfLongestSubstring(s: String): Int = {
    var cache = scala.collection.mutable.Buffer[Char]()
    var maxLen = 0
    s.toCharArray.foreach(v => {
      if(cache.contains(v)){
        maxLen = chooseBigger(cache.size, maxLen)
        cache = cache.drop(cache.indexOf(v)+1)
      }
      cache.append(v)
    })
    chooseBigger(cache.size, maxLen)
  }

  def chooseBigger(var1:Int, var2:Int):Int ={
    if(var1 > var2){
      var1
    }else{
      var2
    }
  }
}
