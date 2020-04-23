package com.yg.struct.list;

/**
 * 双向单链表
 *
 * @author Y.G
 * @date 2020/3/31 15:41
 **/
public class DoublyLinkedList {
    static class Node {
        private int data;
        private Node pre;
        private Node next;
        private int size = 1;

        public Node(int data) {
            this.data = data;
        }

        public Node add(Node node) {
            Node nextNode = select(size - 1);
            nextNode.next = node;
            node.pre = nextNode;
            size++;
            return this;
        }

        public Node delete(int i) {
            if(i==0){
                Node first = this.next;
                first.size = this.size -1;
                this.next = null;
                first.pre = null;
                return first;
            }
            Node preNode = select(i - 1);
            preNode.next = preNode.next.next;
            preNode.next.pre = preNode;
            size--;
            return this;
        }

        public Node insert(int i, Node node){
            if(i==0){
                node.next = this;
                node.size = this.size + 1;
                this.pre = node;
                return node;
            }
            Node preNode = select(i -1);
            Node oldNext =preNode.next;
            preNode.next = node;
            node.pre = preNode;
            node.next = oldNext;
            oldNext.pre = node;
            size++;
            return this;
        }

        public Node update(int i, Node node) {
            if(i == 0){
                node.next = this.next;
                node.size = this.size;
                this.next.pre = node;
                this.next = null;
                return node;
            }
            Node preNode = select(i - 1);
            node.next = preNode.next.next;
            preNode.next.next.pre = node;
            preNode.next = node;
            node.pre = preNode;
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
            Node lastNode = this.next;
            while (nextNode != null) {
                str.append(" -> ").append(nextNode.data);
                lastNode = nextNode;
                nextNode = nextNode.next;
            }

            str.append(" || " + lastNode.data);
            nextNode = lastNode.pre;
            while (nextNode != null) {
                str.append(" -> ").append(nextNode.data);
                nextNode = nextNode.pre;
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
