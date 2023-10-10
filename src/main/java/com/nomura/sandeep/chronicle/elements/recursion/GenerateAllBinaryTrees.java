package com.nomura.sandeep.chronicle.elements.recursion;

import com.google.common.collect.Lists;

import java.util.List;

public class GenerateAllBinaryTrees {
    static class TreeNode {
        final int data;
        final TreeNode left;
        final TreeNode right;

        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            //StringBuilder stringBuilder = new StringBuilder();
            return String.format("%d -> ", data);
        }
    }


    public Iterable<TreeNode> binaryTreesWithNodes(int n) {
        if (n == 0) {
            TreeNode treeNode = null;
            return Lists.newArrayList(treeNode);
        }
        List<TreeNode> results = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            for (TreeNode left : binaryTreesWithNodes(i)) {
                for (TreeNode right : binaryTreesWithNodes(n - 1 - i)) {
                    results.add(new TreeNode(i, left, right));
                }
            }
        }
        return results;
    }


    public void binarySearchTreeFrom1To(int n) {
        Iterable<TreeNode> results = helper(1, n);
        results.forEach(tree -> {
            preorder(tree);
            System.out.println("");
        });
    }


    private Iterable<TreeNode> helper(int start, int end) {
        List<TreeNode> results = Lists.newArrayList();
        if (start > end) {
            TreeNode treeNode = null;
            results.add(treeNode);
        } else {
            for (int i = start; i <= end; i++) {
                for (TreeNode left : helper(start, i - 1)) {
                    for (TreeNode right : helper(i + 1, end)) {
                        results.add(new TreeNode(i, left, right));
                    }
                }
            }
        }
        return results;
    }

    static void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void main(String[] args) {
        GenerateAllBinaryTrees gen = new GenerateAllBinaryTrees();
        Iterable<TreeNode> res = gen.binaryTreesWithNodes(3);
        res.forEach(e -> {
            preorder(e);
            System.out.println("---------");
        });

        System.out.println("=================");
        System.out.println("=================");
        System.out.println("=================");
        gen.binarySearchTreeFrom1To(3);
    }

    static void inorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inorder(treeNode.left);
        System.out.print(treeNode.data + ", ");
        inorder(treeNode.right);
    }
}