package com.yg.leecode._04;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/7/6 10:51
 **/
public class Solution {
    public static void main(String[] args) {
        IntStream.range(0, 10).parallel().map(k -> {
            System.out.println(Thread.currentThread().getName() + " -> "+ (k + 1));
            return  k+1;
        }).flatMap(k -> IntStream.range(0, k)).forEach(k -> {
            System.out.println(Thread.currentThread().getName() + " -> "+ k);
        });
    }
}
