package com.nomura.sandeep.chronicle.clrs.chapter12;

import java.util.Stack;

/**
 * 10.4.4
 * Binary tree ..print all keys recursively.
 * Setting things up on a mac
 * Testing from intellij
 */
public class TreePrintKeysRecursive {

    private Node<Integer> root = null;

    public static void main(String[] args) {
        TreePrintKeysRecursive t = new TreePrintKeysRecursive();
        int[] arr = new int[]{31, 23, 45, 10, 20, 9, 59, 35};
        for (int i : arr) {
            t.insert(i);
        }
        System.out.println("In order :");

        t.printInOrderRecursive(t.root);
        System.out.println("");
        System.out.println("In Order Iter with Stack");
        t.printInOrderWithStack(t.root);
        System.out.println("");
        System.out.println("In Order Iter with Stack lil better");
        t.printInOrderWithStackAlilBetter(t.root);
        System.out.println("");
        System.out.println("-------------------------");

        System.out.println("");
        System.out.println("Pre Order :");
        t.printPreOrderRecursive(t.root);
        System.out.println("");
        System.out.println("Pre Order with Stack:");
        t.printPreOrderWithStack(t.root);
        System.out.println("");
        System.out.println("Pre Order with Stack One mOre:");
        t.printPreOrderWithStackOneMore(t.root);
        System.out.println("");
        System.out.println("----------------------");

        System.out.println("");
        System.out.println("Post Order :");
        t.printPostOrderRecursive(t.root);
        System.out.println("");
        System.out.println("Post Order with stack:");
        t.printPostOrderWithStack(t.root);

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

    public void printInOrderWithStack(Node node) {
        Stack<Node<Integer>> stack = new Stack<>();
        Node<Integer> tmp = null;
        if (node != null) {
            stack.push(node);
        }
        while (!stack.isEmpty()) {
            while (node.left != null) {
                node = node.left;
                stack.push(node);
            }
            tmp = stack.pop();
            System.out.print(tmp.data);
            System.out.print(",");
            if (tmp.right != null) {
                node = tmp.right;
                stack.push(node);
            }
        }
    }

    public void printInOrderWithStackAlilBetter(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node<Integer>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pop();
                System.out.print(node.data);
                System.out.print(",");
                node = node.right;
            }
        }
    }


//    public void


    public void printPreOrderRecursive(Node node) {
        if (node == null) {
            return;
        } else {
            System.out.print(node.data);
            System.out.print(",");
            printPreOrderRecursive(node.left);
            printPreOrderRecursive(node.right);
        }
    }

    public void printPreOrderWithStack(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node<Integer>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.data);
            System.out.print(",");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public void printPreOrderWithStackOneMore(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node<Integer>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                System.out.print(node.data);
                System.out.print(",");
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pop();
                node = node.right;
            }
        }
    }


    public void printInOrderRecursive(Node node) {
        if (node == null) {
            return;
        } else {
            printInOrderRecursive(node.left);
            System.out.print(node.data);
            System.out.print(",");

            printInOrderRecursive(node.right);
        }
    }

    public void printPostOrderRecursive(Node node) {
        if (node == null) {
            return;
        } else {
            printPostOrderRecursive(node.left);
            printPostOrderRecursive(node.right);
            System.out.print(node.data);
            System.out.print(",");
        }
    }

    public void printPostOrderWithStack(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node<Integer>> stack1 = new Stack<>();
        Stack<Node<Integer>> stack2 = new Stack<>();
        stack1.push(node);
        while (!stack1.isEmpty()) {
            node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }

        }
        while (!stack2.isEmpty()) {
            node = stack2.pop();
            System.out.print(node.data);
            System.out.print(",");
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

        @Override
        public String toString() {
            return "" + data;
        }
    }
}