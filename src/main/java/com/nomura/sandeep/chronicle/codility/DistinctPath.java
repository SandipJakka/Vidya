package com.nomura.sandeep.chronicle.codility;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * In this problem we consider binary trees. The figure below shows an example binary tree consisting
 * of seven nodes.
 * <p>
 * <p>
 * <p>
 * A binary tree is either an empty tree or a node (called the root) containing a single integer value and
 * linked to two further binary trees. We are interested in paths (sequences of linked adjacent nodes) that start
 * at the root and follow the tree edges (marked as arrows in the figure above).
 * For example, the sequence of nodes A, B, D is a valid path, but the sequence A, B, G is not.
 * <p>
 * Problem
 * <p>
 * We would like to find the maximum number of distinct values that appear on a
 * path starting at the root of the tree. For example, on the path consisting of nodes A, B, D, G
 * there are two distinct values (4 and 5). On the path A, C, E there are three distinct values (1, 4 and 6).
 * There is no path that contains four or more distinct values.
 * <p>
 * <p>
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(Tree T); }
 * <p>
 * that, given a binary tree T consisting of N nodes, returns
 * the maximum number of distinct values that appear on a path starting at the root of tree T.
 * For example, given the tree shown above, the function should return 3.
 */
public class DistinctPath {

    static class TreeNode {
        final int x;
        final TreeNode left;
        final TreeNode right;

        TreeNode(int x, TreeNode left, TreeNode right) {
            this.x = x;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("%d", x);
        }
    }

    static class Info {
        Set<Integer> pathElements = new HashSet<>();

        int size() {
            return pathElements.size();
        }

        @Override
        public String toString() {
            return pathElements.toString();
        }
    }


    public int solution(TreeNode T) {
        if (T == null) {
            return -1;
        }
        List<Info> distinctPaths = helper(T);
        distinctPaths.sort(Comparator.comparing(Info::size).reversed());
        return distinctPaths.get(0).size();
    }

    private List<Info> helper(TreeNode treeNode) {
        if (treeNode == null) {
            return Lists.newArrayList();
        }
        if (treeNode.left == null && treeNode.right == null) {
            Info info = new Info();
            info.pathElements.add(treeNode.x);
            return Lists.newArrayList(info);
        }
        List<Info> left = helper(treeNode.left);
        List<Info> right = helper(treeNode.right);

        List<Info> ret = Lists.newArrayList();
        left.forEach(el -> ret.add(el));
        right.forEach(el -> ret.add(el));
        ret.forEach(el -> el.pathElements.add(treeNode.x));
        return ret;
    }


    public static void main(String[] args) {

        TreeNode nodeG = new TreeNode(5, null, null);
        TreeNode nodeD = new TreeNode(4, nodeG, null);
        TreeNode nodeB = new TreeNode(5, nodeD, null);

        TreeNode nodeE = new TreeNode(1, null, null);
        TreeNode nodeF = new TreeNode(6, null, null);
        TreeNode nodeC = new TreeNode(6, nodeE, nodeF);

        TreeNode root = new TreeNode(4, nodeB, nodeC);


        DistinctPath d = new DistinctPath();
        System.out.println(d.solution(root));
    }
}
