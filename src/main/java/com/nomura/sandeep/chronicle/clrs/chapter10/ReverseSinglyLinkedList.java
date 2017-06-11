package com.nomura.sandeep.chronicle.clrs.chapter10;

/**
 *
 */
public class ReverseSinglyLinkedList {

    public static void print(Node head) {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data);
            if (tmp.next != null) {
                System.out.print("==>");
            }
            tmp = tmp.next;
        }
    }

    public static void main(String[] args) {
        ReverseSinglyLinkedList list = new ReverseSinglyLinkedList();
        int[] ints = new int[]{10, 100, 200, 7, 14, 28};
        Node<Integer> head = null;
        for (int i : ints) {
            head = list.insert(head, i);
        }

        print(head);
        head = reverse(head);
        System.out.println();
        System.out.println("===Reversed;===");
        print(head);
    }

    private static Node<Integer> reverse(Node<Integer> head) {
        boolean first = true;
        if (head == null || head.next == null) {
            return head;
        } else {
            Node nxt = head.next;
            Node tmp = null;
            while (nxt != null) {
                if (nxt.next != null) {
                    tmp = nxt.next;
                } else {
                    tmp = null;
                }
                nxt.next = head;
                if (first) {
                    head.next = null;
                    first = false;
                }
                head = nxt;
                nxt = tmp;
            }
        }
        return head;
    }

    public Node insert(Node head, int data) {
        Node<Integer> node = new Node<>();
        node.setData(data);
        if (head == null) {
            head = node;
            node.setNext(null);
        } else {
            node.next = head;
            head = node;
        }
        return head;
    }

    static class Node<T> {
        T data;
        Node<T> next;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "C: " + data;
        }
    }


}
