package com.nomura.sandeep.chronicle.elements.trees;

public class PostOrderTraversalWithStack {
    class Node {
        int key;
        Node left, right, parent;

        public Node(int key) {
            this.key = key;
            left = right = parent = null;
        }
    }


    Node root;

    /* A utility function to insert a new node with
       given val in BST */
    Node insert(Node node, int key) {
        /* If the tree is empty, return a new node */
        if (node == null)
            return new Node(key);

        /* Otherwise, recur down the tree */
        if (key < node.key) {
            node.left = insert(node.left, key);
            node.left.parent = node;
        } else if (key > node.key) {
            node.right = insert(node.right, key);
            node.right.parent = node;
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    public static void main(String[] args) {
        PostOrderTraversalWithStack tree = new PostOrderTraversalWithStack();
      /*  tree.root = tree.insert(tree.root, 24);
        tree.root = tree.insert(tree.root, 27);
        tree.root = tree.insert(tree.root, 29);
        tree.root = tree.insert(tree.root, 34);
        tree.root = tree.insert(tree.root, 14);
        tree.root = tree.insert(tree.root, 4);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 22);
        tree.root = tree.insert(tree.root, 13);
        tree.root = tree.insert(tree.root, 3);
        tree.root = tree.insert(tree.root, 2);
        tree.root = tree.insert(tree.root, 6);*/

        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 100);
        tree.root = tree.insert(tree.root, 80);
        tree.root = tree.insert(tree.root, 120);


        System.out.println("PostOrder traversal is ");
        tree.root = tree.postOrder(tree.root);
        System.out.println("");
        System.out.println("InOrder traversal is ");
        tree.root = tree.inOrder(tree.root);
    }

    private Node inOrder(Node root) {
        if (root == null) {
            return null;
        }
        Node temp = root;
        Node curr = root, prev = null;
        while (curr != null) {
            Node next = null;
            if (curr.parent == prev) {
                // we came here from the parent to keep going left if present, else process the node
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    System.out.print(" " + curr.key);
                    next = curr.right != null ? curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                System.out.print(" " + curr.key);
                // we came here from left child. So, process the right-subtree of present.
                if (curr.right != null) {
                    next = curr.right;
                } else {  // nothing on the right;
                    next = curr.parent;
                }
            } else {
                next = curr.parent;
            }
            prev = curr;
            curr = next;
        }
        return temp;
    }

    private Node postOrder(Node root) {
        if (root == null) {
            return null;
        }
        Node temp = root;
        Node curr = root, prev = null;
        while (curr != null) {
            Node next = null;
            if (curr.parent == prev) {
                // we came here from the parent to keep going left if present, else process the node
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    System.out.print(" " + curr.key);
                    next = curr.right != null ? curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                // we came here from left child. So, process the right-subtree of present.
                if (curr.right != null) {
                    next = curr.right;
                } else {  // nothing on the right;
                    next = curr.parent;
                }
            } else {
                System.out.print(" " + curr.key);
                next = curr.parent;
            }
            prev = curr;
            curr = next;
        }
        return temp;
    }
}