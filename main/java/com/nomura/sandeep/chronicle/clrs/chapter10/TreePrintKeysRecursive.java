package com.nomura.sandeep.chronicle.clrs.chapter10;

/**
 * 10.4.4
 * Binary tree ..print all keys recursively.
 */
public class TreePrintKeysRecursive {

    private Node<Integer> root = null;

    public static void main(String[] args) {
        TreePrintKeysRecursive t = new TreePrintKeysRecursive();
        int[] arr = new int[]{31, 23, 45, 10, 9, 59};
        for (int i : arr) {
            t.insert(i);
        }
        t.printRecursive(t.root);

    }

    public void insert(Integer data) {
        Node<Integer> node = new Node<>(data, null, null);
        if (root == null) {
            root = node;
        } else {
            Node<Integer> tmp = root;
            Node<Integer> prev = root;
            while (tmp != null) {
                prev = tmp;
                if (data < tmp.data) {
                    tmp = tmp.left;
                } else {
                    tmp = tmp.right;
                }
            }
            if (data < prev.data) {
                prev.left = node;
            } else {
                prev.right = node;
            }
        }
    }

    public void printRecursive(Node node) {
        if (node == null) {
            return;
        } else {
            System.out.println(node.data);
            System.out.println("==>");
            printRecursive(node.left);
            printRecursive(node.right);
        }
    }

    private static final class Node<T> {
        private final T data;
        private Node<T> left;
        private Node<T> right;

        private Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}