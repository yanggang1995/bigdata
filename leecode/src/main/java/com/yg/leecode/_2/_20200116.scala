package com.yg.leecode._2

/**
 * Y.G
 *
 * 2020-01-16 13:56
 **/
object _20200116 {
  def main(args: Array[String]): Unit = {
    val t1 = new ListNode(1)
    t1.next = new ListNode(2)
    t1.next.next = new ListNode(3)
    val t2 = new ListNode(9)
    t2.next = new ListNode(9)
    t2.next.next = new ListNode(9)
    val rs = addTwoNumbers(t1, t2)
    print(rs)
  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    var finish_1 = l1
    var finish_2 = l2
    var result:ListNode = null
    var tmp = result
    var last = 0
    while(finish_1 != null || finish_2!=null){
      val total = finish_1._x + finish_2._x + last
      last = total / 10
      if(tmp == null) {
        tmp = new ListNode(total % 10)
        result = tmp
      }else{
        tmp.next = new ListNode(total % 10)
        tmp = tmp.next
      }
      finish_1 = finish_1.next
      finish_2 = finish_2.next
      if(finish_1 == null && finish_2 == null){
        if(last>0){
          tmp.next = new ListNode(last)
        }
      }else if(finish_1 == null){
        finish_1 = new ListNode(last)
        last = 0
      }else if(finish_2 == null){
        finish_2 = new ListNode(last)
        last = 0
      }
    }
    result
  }

  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
    override def toString():String = {
      var str = x.toString
      var tmp = next
      while (tmp!=null){
        str += s" -> ${tmp.x}"
        tmp = tmp.next
      }
      str
    }
  }

}
