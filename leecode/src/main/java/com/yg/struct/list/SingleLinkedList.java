package com.yg.struct.list;

/**
 * 单链表
 *
 * @author Y.G
 * @date 2020/3/31 15:41
 **/
public class SingleLinkedList {
    static class Node {
        private int data;
        private Node next;
        private int size = 1;

        public Node(int data) {
            this.data = data;
        }

        public Node add(Node node) {
            Node nextNode = select(size - 1);
            nextNode.next = node;
            size++;
            return this;
        }

        public Node delete(int i) {
            if(i==0){
                Node first = this.next;
                first.size = this.size -1;
                this.next = null;
                return first;
            }
            Node preNode = select(i - 1);
            preNode.next = preNode.next.next;
            size--;
            return this;
        }

        public Node insert(int i, Node node){
            if(i==0){
                node.next = this;
                node.size = this.size + 1;
                return node;
            }
            Node preNode = select(i -1);
            Node oldNext =preNode.next;
            preNode.next = node;
            node.next = oldNext;
            size++;
            return this;
        }

        public Node update(int i, Node node) {
            if(i == 0){
                node.next = this.next;
                node.size = this.size;
                this.next = null;
                return node;
            }
            Node preNode = select(i - 1);
            node.next = preNode.next.next;
            preNode.next = node;
            return this;
        }

        public Node select(int i) {
            if (i >= size || i<0) {
                throw new RuntimeException("OutOfBoundsException");
            }
            Node nodeNext = this;
            for (int var1 = 0; var1 < i; var1++) {
                nodeNext = nodeNext.next;
            }
            return nodeNext;
        }

        @Override
        public String toString() {
            Node nextNode = this.next;
            StringBuilder str = new StringBuilder(data + "");
            while (nextNode != null) {
                str.append(" -> ").append(nextNode.data);
                nextNode = nextNode.next;
            }
            return str.toString();
        }
    }

    public static void main(String[] args) {
        Node first = new Node(1);
        first.add(new Node(2));
        first.add(new Node(3));
        first.add(new Node(4));
        System.out.println(first);
        first = first.delete(0);
        System.out.println(first);
        System.out.println(first.select(1));
        System.out.println(first.update(1, new Node(10)));
        first = first.update(0, new Node(10));
        System.out.println(first);
        System.out.println(first.insert(1, new Node(100)));
        System.out.println(first.insert(0, new Node(100)));
    }
}
