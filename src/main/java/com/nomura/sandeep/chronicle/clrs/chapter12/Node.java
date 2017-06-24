package com.nomura.sandeep.chronicle.clrs.chapter12;

/**
 * Created by sandeep.jakka on 6/21/17.
 */
public class Node<T> {
    final T data;
    Node<T> left;
    Node<T> right;

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public static <T> Node<T> newNode(T data) {
        return new Node<>(data, null, null);
    }

    @Override
    public String toString() {
        return "" + data;
    }


}
