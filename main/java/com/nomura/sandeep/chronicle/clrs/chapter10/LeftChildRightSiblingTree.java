package com.nomura.sandeep.chronicle.clrs.chapter10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Very interesting concept ...When there are unknown number of children, then this structure is very handy..
 * Structure has :
 * <p>
 * x.p ==> Parent of x
 * x.left ==> left child of x
 * x.right ==> sibling of x
 * <p>
 * insert ( data, left , {sibling1,sibling2....} )
 */
public class LeftChildRightSiblingTree {
    private Node<String> root = null;

    public static void main(String[] args) {
        LeftChildRightSiblingTree familyTree = new LeftChildRightSiblingTree();
        familyTree.insert("JVVCS", null, null, null);
        familyTree.insert("JVSM", familyTree.find("JVVCS", 0), null, new String[]{"JVSK", "JVNM", "JVSS"});
        familyTree.insert("JSR", familyTree.find("JVSM", 1), null, null);
        familyTree.insert("JSV", familyTree.find("JVSK", 1), null, new String[]{"JSB"});
        familyTree.insert("JSR1", familyTree.find("JSR", 2), null, new String[]{"JSR2"});
        familyTree.insert("JBS1", familyTree.find("JSB", 2), null, new String[]{"JBS2"});
        familyTree.insert("ME", familyTree.find("JVSS", 1), null, null);
        familyTree.insert("ISHA", familyTree.find("ME", 2), null, null);

        familyTree.print();
    }

    private static boolean check(String data, int level, Node<String> tmp) {
        if (tmp.data == data && tmp.nodeLevel == level) {
            return true;
        }
        return false;
    }

    private static void print(Node<String> tmp) {
        System.out.printf("D : %s ..Level : %d \n", tmp.data, tmp.nodeLevel);
    }

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

    private void insert(String data, Node<String> parent, String left, String... siblings) {
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
