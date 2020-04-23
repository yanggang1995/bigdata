package com.yg.struct.queue;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/21 10:44
 **/
public class QueueMain {
    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.offer(1)
                .offer(2)
                .offer(3);

        System.out.println(linkedQueue.take());
        System.out.println(linkedQueue.take());
        linkedQueue.offer(4);
        System.out.println(linkedQueue.take());
        System.out.println(linkedQueue.take());
        System.out.println(linkedQueue.take());
    }
}
