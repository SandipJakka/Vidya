package com.nomura.sandeep.chronicle.clrs.chapter12;


public class SundryTreeFunctions {

    Node<Integer> root1 = null;
    Node<Integer> root3 = null;

    Node<Integer> root2 = null;
    Node<Integer> root4 = null;

    public static void main(String[] args) {
        SundryTreeFunctions t = new SundryTreeFunctions();
        int[] arr = new int[]{31, 23, 45, 10, 20, 9, 59, 35};
        for (int i : arr) {
            t.root1 = t.insertBST(i, t.root1);
            t.root3 = t.insertBST(i, t.root3);
            t.root2 = t.insertBinaryTree(i, t.root2);
        }

        arr = new int[]{31, 23, 45, 20, 10, 9, 59, 35};
        for (int i : arr) {
            t.root4 = t.insertBST(i, t.root4);
        }
        System.out.println("----Is Binary Tree :" + t.isBST(t.root1));
        System.out.println("----Is Binary Tree :" + t.isBST(t.root2));

        System.out.println("---- Tree Height:" + t.heightOfABinaryTree(t.root1));
        System.out.println("---- Tree Height:" + t.findHeight(t.root1));

        System.out.println("---- Tree Height:" + t.heightOfABinaryTree(t.root2));
        System.out.println("----Tree Height :" + t.findHeight(t.root2));

        System.out.println("Is tree equal : " + t.isTreesEqual(t.root1, t.root3));
        System.out.println("Is tree equal : " + t.isTreesEqual(t.root1, t.root4));
    }


    /**
     * Equality check is checking the order of the tree , not only the values,
     * but the way these nodes are in the tree.
     *
     * @param node1
     * @param node2
     * @return
     */
    public boolean isTreesEqual(Node<Integer> node1, Node<Integer> node2) {
        System.out.println("Node 1 :" + node1 + " , Node2 :" + node2);
        if (node1 == null && node2 == null) {
            return true;
        }
        if ((node1 == null && node2 != null) || (node1 != null && node2 == null)) {
            return false;
        }
        if (node1.data != node2.data) {
            return false;
        }
        return isTreesEqual(node1.left, node2.left) && isTreesEqual(node1.right, node2.right);
    }

    /**
     * Height of the tree : 1 + Max ( height of left tree , height of right tree )
     * Height of the tree with only root = 0
     *
     * @param node
     * @return
     */
    public int heightOfABinaryTree(Node<Integer> node) {
        System.out.println("Height : " + node);
        if (node == null) {
            return 0;
        }
        if (node.right == null && node.left == null) { //leaf node
            return 0;
        }
        return 1 + Math.max(heightOfABinaryTree(node.left), heightOfABinaryTree(node.right));
    }

    private final int findHeight(Node<Integer> aNode) {
        if (aNode == null) {
            return -1;
        }

        int lefth = findHeight(aNode.left);
        int righth = findHeight(aNode.right);

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }

    /**
     * Satellite method to invoke the actual method.
     *
     * @param node
     * @return
     */
    public boolean isBST(Node<Integer> node) {
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Check if each node has values which are with in permissable ranges.
     * Root can be between MIN and MAX
     * Left child can be between MIN and node.data
     * Right child can be between node.data and MAX.
     *
     * @param node
     * @param minValue
     * @param maxValue
     * @return
     */
    private boolean isBST(Node<Integer> node, int minValue, int maxValue) {
//        System.out.println("Node :" + node + " Min : " + minValue + " Max : " + maxValue);
        if (node == null) {
            return true;
        }
        if (node.data > maxValue || node.data < minValue) {
            return false;
        }
        return isBST(node.left, minValue, node.data) && isBST(node.right, node.data, maxValue);
    }


    /**
     * Creates a BST
     *
     * @param data
     * @param root
     * @return
     */
    public Node<Integer> insertBST(Integer data, Node<Integer> root) {
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
        return root;
    }

    /**
     * Creates just a binary tree , not neccasarily a BST.
     *
     * @param data
     * @param root
     * @return
     */
    public Node<Integer> insertBinaryTree(Integer data, Node<Integer> root) {
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
            if (prev.left == null) {
                prev.left = node;
            } else {
                prev.right = node;
            }
        }
        return root;
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
