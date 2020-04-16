package com.yg.leecode._4

/**
 * Y.G
 *
 * 2020-01-16 17:20
 **/
object _20200116 {
  def main(args: Array[String]): Unit = {
    println(findMedianSortedArrays(Array(1,2,3,4,5,6,7),Array(8,9)))
  }

  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    val len1 = nums1.length
    val len2 = nums2.length
    if(nums1(len1 - 1) <= nums2(0)){
      if((len1 + len2)%2 == 0){
        val mLen = (len1 + len2) / 2
        if(mLen < len1){
          println((nums1(mLen) + nums1(mLen-1))*1.0 / 2)
        }else if(mLen == len1){
          println((nums1(mLen-1) + nums2(mLen - len1))*1.0 / 2)
        }else {
          println((nums2(mLen-len1-1) + nums2(mLen - len1))*1.0 / 2)
        }
      }else{
        val mLen = (len1 + len2) / 2
        if(mLen < len1){
          println(nums1(mLen))
        }else {
          println(nums2(mLen - len1))
        }
      }
    }else{

    }
//    (findMedian(nums1, true) + findMedian(nums2, false))*1.0/2
    1.0
  }

  def findMedian(num:Array[Int], isFirst:Boolean):Int = {
    val length = num.length
    if(length % 2 == 0){
      if(isFirst){
        num(length/2)
      }else{
        num(length/2 - 1)
      }
    }else{
      num(length/2)
    }
  }
}
