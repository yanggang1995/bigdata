package com.yg.leecode._3

/**
 * Y.G
 *
 * 2020-01-16 14:24
 **/
object _20200116_3 {
  def main(args: Array[String]): Unit = {
    println(lengthOfLongestSubstring("abcdpwwkew"))
  }

  def lengthOfLongestSubstring(s: String): Int = {
    val cache = scala.collection.mutable.Map[Char, Int]()
    val lenSet = scala.collection.mutable.Set[Int](0)
    var startIndex = 0
    var index = 0
    s.foreach(v => {
       val sValue = cache.get(v)
      if(sValue != None){
        val vIndex = sValue.get
        if(vIndex>=startIndex) {
          lenSet.add(index - startIndex)
          startIndex = vIndex + 1
        }
        cache(v) = index
      }else {
        cache += (v -> index)
      }
      index += 1
    })
    lenSet.add(index-startIndex)
    lenSet.max
  }
}
