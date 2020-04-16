package com.yg.suanfa.example;

/**
 * 斐波拉契数列
 *
 * @author Y.G
 * @date 2020/3/29 10:18
 **/
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(4));
    }

    private static int fibonacci(int n){
        if(n == 1 || n == 2){
            return 1;
        }else{
            return fibonacci(n-1) + fibonacci(n - 2);
        }
    }
}
