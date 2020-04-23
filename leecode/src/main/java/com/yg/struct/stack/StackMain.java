package com.yg.struct.stack;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/21 9:41
 **/
public class StackMain {
    public static void main(String[] args) {
        LinkedStack<Integer> intStack = new LinkedStack<>();
        intStack.push(1)
                .push(2)
                .push(3);

        System.out.println(intStack.pop());
        intStack.push(4);
        System.out.println(intStack.pop());
        System.out.println(intStack.pop());
        System.out.println(intStack.pop());
        System.out.println(intStack.pop());
    }
}
