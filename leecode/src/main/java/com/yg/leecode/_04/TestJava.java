package com.yg.leecode._04;

/**
 * @author: Y.G
 * @description:
 * @create: 2020-01-28 15:30
 **/
public class TestJava {
    public void test1(){
        test();
        this.test();
    }

    private void test(){}

    public static void main(String[] args) {
        TestJava testJava = new TestJava();
        testJava.test();
        
    }
}
