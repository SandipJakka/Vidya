package com.nomura.sandeep.chronicle.hackerrank;

import java.util.Scanner;

/**
 * Created by sandeep on 7/7/2016.
 */
public class TreeHeight {

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        Node root = null;
        while (T-- > 0) {
            int data = sc.nextInt();
            root = insert(root, data);
        }
        int height = getHeight(root);
        System.out.println(height);
    }

    private static int getHeight(Node root) {
        /*
        if (root == null) {
            return -1;
        } else {
            int leftTreeHeight = getHeight(root.left);
            int rightTreeHeight = getHeight(root.right);
            if (leftTreeHeight > rightTreeHeight) {
                return leftTreeHeight + 1;
            } else {
                return rightTreeHeight + 1;
            }
        }*/

        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }

    }

    static class Node {
        Node left, right;
        int data;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

}
