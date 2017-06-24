package com.nomura.sandeep.chronicle.clrs.chapter12;


public class MorrisTreeTraversal {

    Node<Integer> root = null;

    public static void main(String[] args) {
        MorrisTreeTraversal t = new MorrisTreeTraversal();
        int[] arr = new int[]{31, 23, 45, 10, 20, 9, 59, 35};
        for (int i : arr) {
            t.insert(i);
        }
        t.inOrderTraversalWithoutStack(t.root);
        System.out.println("");
        t.printInOrderRecursive(t.root);

        System.out.println("------------------------");

        t.preOrderTraversalWithoutStack(t.root);
        System.out.println("");
        t.printPreOrderRecursive(t.root);
    }


    public void inOrderTraversalWithoutStack(Node current) {
        Node<Integer> predecessor = null;

        while (current != null) {
            // Case when the left side of the tree is all fully processed and there is nothing on the
            // left side of the tree...
            if (current.left == null) {
                visit(current);
                current = current.right;
            } else {
                // finding the in order predecessor for the current node
                predecessor = current.left;
                // check against current since we have added a thread
                while (predecessor.right != current && predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                /// create the thread
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // delete the thread after we are done processing the left side of the tree.
                    // we don't need the thread anymore and can safely be deleted.
                    // visit the current node while doing so
                    predecessor.right = null;
                    visit(current);
                    current = current.right;
                }
            }
        }
    }

    private static void visit(Node current) {
        System.out.print(current.data);
        System.out.print(",");
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

    public void preOrderTraversalWithoutStack(Node current) {
        Node<Integer> predecessor = null;

        while (current != null) {
            // Case when the left side of the tree is all fully processed and there is nothing on the
            // left side of the tree...
            if (current.left == null) {
                visit(current);
                current = current.right;
            } else {
                // finding the in order predecessor for the current node
                predecessor = current.left;
                // check against current since we have added a thread
                while (predecessor.right != current && predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                /// create the thread
                if (predecessor.right == null) {
                    predecessor.right = current;
                    visit(current);
                    current = current.left;
                } else {
                    // delete the thread after we are done processing the left side of the tree.
                    // we don't need the thread anymore and can safely be deleted.
                    // visit the current node while doing so
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
    }

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
