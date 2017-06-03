package com.nomura.sandeep.chronicle.clrs.chapter10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Very interesting concept ...When there are unknown number of children, then this structure is very handy..
 * Structure has :
 * <p>
 * x.p ==> Parent of x
 * x.left ==> left child of x
 * x.right ==> sibling of x
 * <p>øø
 * insert ( data, left , {sibling1,sibling2....} )
 * print 
 */
public class LeftChildRightSiblingTree {
    private Node<String> root = null;

    public static void main(String[] args) {
        LeftChildRightSiblingTree familyTree = new LeftChildRightSiblingTree();
        familyTree.insert("JVVCS", null, null, null);
        familyTree.insert("JVSM", familyTree.find("JVVCS", 0), new String[]{"JVSK", "JVNM", "JVSS"});
        familyTree.insert("JSR", familyTree.find("JVSM", 1), null);
        familyTree.insert("JSV", familyTree.find("JVSK", 1), new String[]{"JSB"});
        familyTree.insert("JSR1", familyTree.find("JSR", 2), new String[]{"JSR2"});
        familyTree.insert("JBS1", familyTree.find("JSB", 2), new String[]{"JBS2"});
        familyTree.insert("ME", familyTree.find("JVSS", 1), null);
        familyTree.insert("ISHA", familyTree.find("ME", 2), null);

        familyTree.print();
    }

    /**
     * Checks if the data and level passed are same the node value
     *
     * @param data
     * @param level
     * @param tmp
     * @return true if there is a match else false
     */
    private static boolean check(String data, int level, Node<String> tmp) {
        if (tmp.data == data && tmp.nodeLevel == level) {
            return true;
        }
        return false;
    }

    private static void print(Node<String> tmp) {
        System.out.printf("D : %s ..Level : %d \n", tmp.data, tmp.nodeLevel);
    }

    /**
     * Prints the tree starting from the tree.
     * <p>
     * The main idea is : print all my children ( left first and right ).
     * While doing this. Keep tracking all  my grand children.
     * Iterate them over when
     * Print them.
     * When done ( queue is empty ) we are done.
     * </p>
     */
    private void print() {
        Node<String> tmp = root;
        print(root);
        Node<String> myChild = null;
        Queue<Node<String>> mySiblingsChildren = new LinkedBlockingQueue<>();
        while (tmp != null) {
            /** Print all my children first  */
            if (tmp.left != null) {
                tmp = tmp.left;
                if (tmp.left != null) {
                    myChild = tmp.left;
                }
                print(tmp);
            }
            while (tmp.right != null) {
                tmp = tmp.right;
                if (tmp.left != null) {
                    mySiblingsChildren.offer(tmp.left);
                }
                print(tmp);
            }
            if (myChild != null) {
                tmp = myChild;
                print(tmp);
                myChild = null;
            } else if (mySiblingsChildren.peek() != null) {
                tmp = mySiblingsChildren.remove();
                print(tmp);
                mySiblingsChildren.remove(tmp);
            } else {
                tmp = null;
            }
        }

    }

    /**
     * @param data     , the data the needs to be inserted.
     * @param parent   , the parent of the current data
     * @param siblings , any siblings that come on the right.
     */
    private void insert(String data, Node<String> parent, String... siblings) {
        int level = parent != null ? parent.nodeLevel + 1 : 0;
        Node<String> node = new Node<>(data, level);
        if (root == null) {
            root = node;
        } else {
            node.parent = parent;
            parent.left = node;
            Node<String> tmp = node;
            if (siblings != null) {
                for (String sibling : siblings) {
                    Node<String> sib = new Node<>(sibling, level);
                    sib.parent = parent;
                    tmp.right = sib;
                    tmp = sib;
                }
            }
        }
    }

    /**
     * Find the Node that matches the data and the level
     *
     * @param data
     * @param level
     * @return node if found , else null
     * Same logic as print, instead of printing, we would check and return the value.
     *
     * More efficient way would be to cache it when we iterate and check the cache  and then the whole tree.
     */
    private Node<String> find(String data, int level) {
        Node<String> node = null;
        if (check(data, level, root)) {
            return root;
        }
        Node<String> tmp = root;

        Node<String> myChild = null;
        Queue<Node<String>> mySiblingsChild = new LinkedList<>();
        while (tmp != null) {
            /** Print all my children first  */
            if (tmp.left != null) {
                tmp = tmp.left;
                if (tmp.left != null) {
                    myChild = tmp.left;
                }
                if (check(data, level, tmp)) {
                    return tmp;
                }
            }
            while (tmp.right != null) {
                tmp = tmp.right;
                if (tmp.left != null) {
                    mySiblingsChild.add(tmp.left);
                }
                if (check(data, level, tmp)) {
                    return tmp;
                }
            }
            if (myChild != null) {
                tmp = myChild;
                if (check(data, level, tmp)) {
                    return tmp;
                }
                myChild = null;
            } else if (mySiblingsChild != null && mySiblingsChild.size() > 0) {
                tmp = mySiblingsChild.remove();
                if (check(data, level, tmp)) {
                    return tmp;
                }
                //mySiblingsChild = null;
            } else {
                tmp = null;
            }

        }
        return node;
    }

    /**
     * Actual node of the tree.
     * @param <T>
     */
    private static class Node<T> {
        private final T data;
        private final int nodeLevel;
        private Node<T> parent = null;
        private Node<T> right = null;
        private Node<T> left = null;

        private Node(T data, int nodeLevel) {
            this.data = data;
            this.nodeLevel = nodeLevel;
        }

        @Override
        public String toString() {
            return "D:" + data + "======Level :" + nodeLevel;
        }
    }
}
