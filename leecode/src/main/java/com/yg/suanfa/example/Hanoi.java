package com.yg.suanfa.example;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 汉诺塔问题
 * 1、无论有多少个盘子需要移动，都可以视为两个
 * 2、最下面的视为一个，其余的视为一个
 * 3、最后两种情况：
 *    1）一个盘子
 *    2）两个盘子
 *
 * @author Y.G
 * @date 2020/3/29 10:23
 **/
public class Hanoi {
    private static final AtomicLong COUNTER = new AtomicLong(0);

    public static void main(String[] args) {
        hanoi(2, 'A', 'B', 'C');
        System.out.println("---  Total Number:"+ COUNTER.get() +"  ---");
    }

    private static void hanoi(int n, char from, char in, char to) {
        COUNTER.incrementAndGet();
        if (n == 1) {
            System.out.println("第" + n + "个盘子，从" + from + "移动到" + to);
        } else {
            // 上面的盘子移动到中间
            hanoi(n - 1, from, to, in);
            // 下面的盘子移动到目标位置
            System.out.println("第" + n + "个盘子，从" + from + "移动到" + to);
            // 上面的盘子移动到目标位置
            hanoi(n - 1, in, from, to);
        }
    }
}
