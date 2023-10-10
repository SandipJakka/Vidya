package com.nomura.sandeep.chronicle.elements.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static java.util.Comparator.comparingInt;

/**
 * 16.11 ...exercise
 * <p>
 * `
 */
public class SendMessage {


    static class TreeNode {
        String name;
        List<TreeNode> nodesList;
        int height;

        TreeNode(String name, List<TreeNode> nodesList, int distance) {
            this.name = name;
            this.nodesList = nodesList;
            this.height = distance;
        }

        @Override
        public String toString() {
            return format("(%s,%s,%d)", name, nodesList, height);
        }
    }

    static class Result {
        final TreeNode node;
        final int height;

        Result(TreeNode node, int height) {
            this.height = height;
            this.node = node;
        }

        public int height() {
            return height;
        }
    }


    public static void main(String[] args) {
        //first side of the tree
        TreeNode nodeE = new TreeNode("E", newArrayList(), 0);
        TreeNode nodeF = new TreeNode("F", newArrayList(), 0);

        TreeNode nodeD = new TreeNode("D", newArrayList(nodeE), 0);
        TreeNode nodeC = new TreeNode("C", newArrayList(nodeD, nodeF), 0);

        //second side of the tree
        TreeNode nodeA = new TreeNode("A", newArrayList(), 0);

        //third side of the tree...
        TreeNode nodeH = new TreeNode("H", newArrayList(), 0);
        TreeNode nodeJ = new TreeNode("J", newArrayList(), 0);
        TreeNode nodeL = new TreeNode("L", newArrayList(), 0);
        TreeNode nodeM = new TreeNode("M", newArrayList(), 0);
        TreeNode nodeO = new TreeNode("O", newArrayList(), 0);
        TreeNode nodeP = new TreeNode("P", newArrayList(), 0);


        TreeNode nodeN = new TreeNode("N", newArrayList(nodeM, nodeP, nodeO), 0);
        TreeNode nodeK = new TreeNode("K", newArrayList(nodeL, nodeN), 0);
        TreeNode nodeI = new TreeNode("I", newArrayList(nodeJ, nodeK), 0);
        TreeNode nodeG = new TreeNode("G", newArrayList(nodeH, nodeI), 0);


        TreeNode root = new TreeNode("B", newArrayList(nodeC, nodeA, nodeG), 0);

        SendMessage send = new SendMessage();
        send.solution(root);

    }

    public void solution(TreeNode root) {
        try {
            Result result = reOrgTree(root);
            System.out.println(result.node);
        } catch (Exception xp) {
            xp.printStackTrace();
        }
    }

    private Result reOrgTree(TreeNode root) {
        if (root == null) {
            return new Result(null, 0);
        } else if (isLeafNode(root)) {
            return new Result(root, 1);
        }
        Set<Result> sorted = new TreeSet<>(comparingInt(Result::height).reversed());
        int height = -1;
        for (TreeNode node : root.nodesList) {
            Result out = reOrgTree(node);
            sorted.add(out);
            height = Math.max(height, out.height);
        }

        List<TreeNode> res = new ArrayList<>();
        for (Result result : sorted) {
            res.add(result.node);
        }
        root.nodesList = res;
        return new Result(root, height + 1);

    }


    private boolean isLeafNode(TreeNode node) {
        return (node.nodesList == null || node.nodesList.size() == 0);
    }

}