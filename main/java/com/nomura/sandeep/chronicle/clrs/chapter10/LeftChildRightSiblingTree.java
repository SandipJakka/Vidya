package com.nomura.sandeep.chronicle.clrs.chapter10;

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
    private int counter = -1;

    public static void main(String[] args) {
        LeftChildRightSiblingTree familyTree = new LeftChildRightSiblingTree();
        familyTree.insert("JVVCS", null, null, null);
        familyTree.insert("JVSM", familyTree.find("JVVCS", 0), null, new String[]{"JVSK", "JVNM", "JVSS"});
        familyTree.insert("JSR", familyTree.find("JVSM", 1), null, null);
        familyTree.insert("JSV", familyTree.find("JVSK", 1), null, new String[]{"JSB"});

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
        if (root != null) {
            Node<String> tmp = root;
            print(tmp);
            do {
                while (tmp.right != null) {
                    tmp = tmp.right;
                    print(tmp);
                }
                if (tmp.parent != null && tmp.parent.left != null) {
                    tmp = tmp.parent.left;
                }
                if (tmp.left != null) {
                    tmp = tmp.left;
                    print(tmp);
                }
                if (tmp.parent != null && tmp.parent.right != null) {
                    tmp = tmp.parent.right;
                }
            } while (tmp.left != null || tmp.right != null);

        }
    }

    private void insert(String data, Node<String> parent, String left, String... siblings) {
        Node<String> node = new Node<>(data, ++counter);
        if (root == null) {
            root = node;
        } else {
            node.parent = parent;
            parent.left = node;
            Node<String> tmp = node;
            if (siblings != null) {
                for (String sibling : siblings) {
                    Node<String> sib = new Node<>(sibling, counter);
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
/*
        do {
            while (tmp.right != null) {
                if (check(data, level, tmp)) {
                    return tmp;
                }
                tmp = tmp.right;
            }
            if (tmp.left != null) {
                tmp = tmp.left;
            }
        } while (tmp.left != null || tmp.right != null);
*/

        do {
            while (tmp.right != null) {
                tmp = tmp.right;
                if (check(data, level, tmp)) {
                    return tmp;
                }
            }
            if (tmp.parent != null && tmp.parent.left != null) {
                tmp = tmp.parent.left;
            }
            if (tmp.left != null) {
                tmp = tmp.left;
                if (check(data, level, tmp)) {
                    return tmp;
                }
            }
        } while (tmp.left != null || tmp.right != null);

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
