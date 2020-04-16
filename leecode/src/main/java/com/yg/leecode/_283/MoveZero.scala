package com.yg.leecode._283

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/3/23 10:39
 **/
object MoveZero {
  def main(args: Array[String]): Unit = {
    val arr = Array(1, 5,0,  9 ,0 ,2, 0, 10)
    moveZeroes(arr)
    println(arr.toBuffer)
  }

  def moveZeroes(nums: Array[Int]): Unit = {
    var zeroIndex:Int = 0
    for(index <- 0 until nums.length){
      if(nums(index) !=0){
          nums(zeroIndex) = nums(index)
        if(index!=zeroIndex) {
          nums(index) =0
        }
        zeroIndex += 1
      }
    }
  }
}
