package com.nomura.sandeep.chronicle.hackerrank;

/**
 * Created by sandeep on 12/26/2016.
 */
public class LinkedListInsert {

    public static void main(String[] args) {

    }

    public static Node insert(Node head, int data) {
        Node n = new Node(data);
        if (head == null) {
            return n;
        } else {
            Node runner = head;
            while (runner.next != null) {
                runner = runner.next;
            }
            runner.next = n;
        }
        return head;
    }


}

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}
