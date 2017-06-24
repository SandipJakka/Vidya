package com.nomura.sandeep.chronicle.clrs.chapter12;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

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

        System.out.println(" Min : " + t.min(t.root1));
        System.out.println(" Min : " + t.min(t.root3));
        System.out.println(" Min : " + t.min(t.root4));

        System.out.println(" Max : " + t.max(t.root1));
        System.out.println(" Max : " + t.max(t.root3));
        System.out.println(" Max : " + t.max(t.root4));

        System.out.println("");
        t.printInOrderRecursive(t.root1);

        //@formatter:off
        /*
                   31
              23         45
          10           35   59
        9    20

        */
        //@formatter:on

        System.out.println("Successor : " + t.successor(t.find(10, t.root1), t.root1));
        System.out.println("Successor : " + t.successor(t.find(20, t.root1), t.root1));
        System.out.println("Successor : " + t.successor(t.find(59, t.root1), t.root1));

        System.out.println("Successor : " + t.predecessor(t.find(10, t.root1), t.root1));
        System.out.println("Successor : " + t.predecessor(t.find(23, t.root1), t.root1));
        System.out.println("Successor : " + t.predecessor(t.find(35, t.root1), t.root1));

        List<Node<Integer>> collector = new ArrayList<>();
        print1(t.sumRootToLeaf(t.root1, 84, collector), collector);
        collector = new ArrayList<>();
        print1(t.sumRootToLeaf(t.root1, 111, collector), collector);
        collector = new ArrayList<>();
        print1(t.sumRootToLeaf(t.root1, 64, collector), collector);


        System.out.println("");
        System.out.println("Level order printing with queue : ");
        t.printLevelOrder(t.root1);
        System.out.println("");

        System.out.println("Largest BST :" + t.largest(t.root1));


        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        //this is just to create a binary tree.
/*
        int inorder[] = {-7, -6, -5, -4, -3, -2, 1, 2, 3, 16, 6, 10, 11, 12, 14};
        int preorder[] = {3, -2, -3, -4, -5, -6, -7, 1, 2, 16, 10, 6, 12, 11, 14};
*/

        int inorder[] = {19, 15, 18, 18, 20, 25, 25, 20, 25, 35, 40, 50, 55, 60, 70};
        int preorder[] = {25, 18, 19, 15, 20, 18, 25, 50, 35, 20, 25, 40, 60, 55, 70};

        Node root = ctf.createTree(inorder, preorder);
        System.out.println("Largest : " + t.largest(root));
    }


    private static void print1(boolean result, List<Node<Integer>> collector) {
        System.out.println(" Sum to leaf : " + result);
        if (result) {
            System.out.println(collector);
        }
    }


    /**
     * Given a Binary Tree , determine the size of the largest BST it contains. Should return the size ( number of nodes )
     * in the largest BST in the binary tree.
     * <p>
     * <p>
     * Solution :
     * <p>
     * Start from the leaf node and keep going up the tree..
     * //@formatter:off
     * 1. Leaf nodes are all BST.
     * 2. Pass these 4 variables ( isBst, min, max , count )
     * 3. Check if each node is within the range ... else not an BST.
     * count --> max of left and right side + 1.
     * <p>
     * <p>
     * <p>
     * //@formatter:on
     * <p>
     * Testing formatting
     *
     * @param root
     * @return
     */

    /**
     * Given a binary tree, find size of largest binary search subtree in this
     * binary tree.
     * <p>
     * Traverse tree in post order fashion. Left and right nodes return 4 piece
     * of information to root which isBST, size of max BST, min and max in those
     * subtree.
     * If both left and right subtree are BST and this node data is greater than max
     * of left and less than min of right then it returns to above level left size +
     * right size + 1 and new min will be min of left side and new max will be max of
     * right side.
     * <p>
     * References:
     * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
     * https://leetcode.com/problems/largest-bst-subtree/
     * <p>
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LargestBSTInBinaryTree.java
     */

    private MinMax largest(Node<Integer> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            MinMax m = new MinMax();
            m.size = 1;
            m.min = node.data;
            m.max = node.data;
            return m;
        }

        MinMax left = largest(node.left);
        MinMax right = largest(node.right);
        boolean isBst = true;
        if (left != null) {
            isBst = isBst && left.isBST && node.data > left.max;
        }
        if (right != null) {
            isBst = isBst && right.isBST && right.min > node.data;
        }

        MinMax m = new MinMax();
        int leftSize = left != null ? left.size : 0;
        int rightSize = right != null ? right.size : 0;

        if (!isBst) {
            m.isBST = false;
            m.size = Math.max(leftSize, rightSize);
            m.min = 0;
            m.max = 0;
            return m;
        } else {
            m.isBST = true;
            m.size = 1 + (leftSize) + (rightSize);
            m.min = node.left != null ? left.min : node.data;
            m.max = node.right != null ? right.max : node.data;
            return m;
        }

    }

    private class MinMax {
        private int min;
        private int max;
        private boolean isBST;
        private int size;

        MinMax() {
            min = Integer.MIN_VALUE;
            max = Integer.MAX_VALUE;
            isBST = true;
            size = 1;
        }

        @Override
        public String toString() {
            return "Min= " + min + " , Max=" + max + " , isBST=" + isBST + ", size =" + size;
        }
    }

    /**
     * Print the tree in a level order .. from left to right.
     *
     * @param root
     */
    public void printLevelOrder(Node<Integer> root) {
        if (root == null) {
            return;
        }

        Queue<Node<Integer>> queue = new LinkedBlockingQueue<>();
        Node<Integer> current = null;
        queue.add(root);
        while (queue.size() > 0) {
            current = queue.remove();
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
            System.out.print(current);
            System.out.print(",");
        }
    }


    /**
     * Root to leaf in a binary tree.
     * <p>
     * Problem :
     * Let's say you are given a sum = S. Is there a path that exists from root to leaf whose data is the sum
     * of nodes = S.
     * And basically print the path if it exists.
     * <p>
     * Solution is :
     * <p>
     * Recursive function
     * <p>
     * Start at the root
     * As we move along the left and the right subtrees, keep reducing from the sum and by the time you are at the leaf and the remaining sum = leaf node data
     * then collect the data in a List and return true .
     */


    public boolean sumRootToLeaf(Node<Integer> node, int sum, List<Node<Integer>> collector) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null) {   //leaf data.
            if (sum == node.data) {
                collector.add(node);
                return true;
            } else {
                return false;
            }
        }

        if (sumRootToLeaf(node.left, (sum - node.data), collector)) {
            collector.add(node);
            return true;
        }
        if (sumRootToLeaf(node.right, (sum - node.data), collector)) {
            collector.add(node);
            return true;
        }
        return false;
    }


    /**
     * Interesting point is we are not comparing keys to determine MIN and MAX.
     * The sheer property of BST lets us achieve that which is cool.
     * O(h)
     *
     * @param node
     * @return
     */
    public Node<Integer> min(Node<Integer> node) {
        if (node == null) {
            return null;
        }
        Node<Integer> prev = null;
        while (node != null) {
            prev = node;
            node = node.left;
        }

        return prev;
    }

    public Node<Integer> max(Node<Integer> node) {
        if (node == null) {
            return null;
        }
        Node<Integer> prev = null;
        while (node != null) {
            prev = node;
            node = node.right;
        }

        return prev;
    }


    /**
     * @param node
     * @param root
     * @return
     */
    // @formatter : off

    /*  If the node has a right node , then
            the left most node in it's right sub tree is it's successor ( which is min in the right sub tree )
        else   ( when the right sub tree is not present )
            then , the nearest ancestor for which a given node would be in the "left" subtree  is the successor.


         Idea is :
            If the node ( we are looking ) is on the right of the parent, then we have already visited it ( by virtue of inorder traversal ) .
             in that case check its parent.
            If  the node is on the left of the parent , then we have not visited it yet.


       Algo: ( for the 2nd part ) :

        keep traversing to the node from root.  that path from root to node is basically all the ancestors of that node .
         Now, the successor is the last ( deepest ) ancestor for which this node is on the left subtree.

    */
    // @formatter :on
    public Node<Integer> successor(Node<Integer> node, Node<Integer> root) {
        if (node == null || root == null) {
            return null;
        }

        if (node.right != null) {
            return min(node.right);
        } else {
            Node<Integer> succ = null;
            while (root != null) {
                if (node.data < root.data) {
                    succ = root;   // last left ancestor.
                    root = root.left;
                } else if (node.data > root.data) {
                    root = root.right;
                } else {
                    // case where we have found the node and we know already know the successor.
                    // Hence break;
                    break;
                }
            }
            return succ;
        }

    }


    /**
     * Algo same as above
     *
     * @param node
     * @param root
     * @return
     */
    private Node<Integer> predecessor(Node<Integer> node, Node<Integer> root) {
        if (root == null || node == null) {
            return null;
        }
        if (node.left != null) {
            return max(node.left);
        } else {
            Node<Integer> pred = null;
            while (root != null) {
                if (node.data < root.data) {
                    root = root.left;
                } else if (node.data > root.data) {
                    pred = root;
                    root = root.right;
                } else {
                    break;
                }
            }
            return pred;
        }

    }

    private Node<Integer> find(int data, Node<Integer> root) {
        if (root == null) {
            return null;
        }
        while (root != null) {
            if (data == root.data) {
                return root;
            }
            if (data < root.data) {
                root = root.left;
            } else if (data > root.data) {
                root = root.right;
            }
        }
        return null;
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

}
