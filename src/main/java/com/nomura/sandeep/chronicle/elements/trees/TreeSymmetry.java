package com.nomura.sandeep.chronicle.elements.trees;


class Node {
    int key;
    Node left, right;

    Node(int item) {
        key = item;
        left = right = null;
    }
}

public class TreeSymmetry {


    Node root;

    // returns true if trees with roots as root1 and root2 are mirror
    boolean isMirror(Node left, Node right) {
        // if both trees are empty, then they are mirror image
        if (left == null && right == null) {
            return true;
        }


        if ((left == null && right != null) || (left != null && right == null)) {
            return false;
        }

        if (left.key == right.key) {
            return isMirror(left.left, right.right) &&
                    isMirror(left.right, right.left);
        }
        return false;

    }

    // returns true if the tree is symmetric i.e
    // mirror image of itself
    boolean isSymmetric(Node node) {
        // check if tree is mirror of itself
        return isMirror(root, root);
    }


    // Driver program
    public static void main(String args[]) {
        TreeSymmetry tree = new TreeSymmetry();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(2);
        tree.root.left.left = new Node(3);
        tree.root.left.right = new Node(4);
        tree.root.right.left = new Node(4);
        tree.root.right.right = new Node(3);
        boolean output = tree.isSymmetric(tree.root);
        if (output == true)
            System.out.println("Symmetrical");
        else
            System.out.println("Non-Symmetrical");
    }
}