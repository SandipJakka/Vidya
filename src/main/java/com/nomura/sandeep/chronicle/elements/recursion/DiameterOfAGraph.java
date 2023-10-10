package com.nomura.sandeep.chronicle.elements.recursion;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.max;
import static java.lang.String.format;

/**
 * Diameter is the longest distance between the nodes...
 * 16.11 and Page no : 292
 */
public class DiameterOfAGraph {

    static class TreeNode {
        final String name;
        final List<TreeNode> nodesList;
        final double distanceToParent;

        TreeNode(String name, List<TreeNode> nodesList, double distance) {
            this.name = name;
            this.nodesList = nodesList;
            this.distanceToParent = distance;
        }

        @Override
        public String toString() {
            return format("(%s,%s,%f)", name, nodesList, distanceToParent);
        }
    }

    static class Result {
        final double height;
        final double diameter;

        Result(double height, double diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    public void solution(TreeNode root) {
        Result result = helper(root);
        System.out.println("Diameter of the tree is : " + result.diameter);
    }

    private Result helper(TreeNode node) {
        if (node == null || isLeafNode(node)) {   //base case.
            return new Result(0.0, 0.0);
        }
        double diameter = Double.MIN_VALUE;
        double[] heights = new double[]{0.0, 0.0}; //save top 2 heights

        for (TreeNode treeNode : node.nodesList) {
            Result out = helper(treeNode);

            if (out.height + treeNode.distanceToParent > heights[0]) {
                heights[1] = heights[0];
                heights[0] = out.height + treeNode.distanceToParent;
            } else if (out.height + treeNode.distanceToParent > heights[1]) {
                heights[1] = out.height + treeNode.distanceToParent;
            }
            diameter = max(diameter, out.diameter);
        }

        return new Result(heights[0], max(diameter, heights[0] + heights[1]));
    }

    private boolean isLeafNode(TreeNode node) {
        return (node.nodesList == null || node.nodesList.size() == 0);
    }

    public static void main(String[] args) {
        //first side of the tree
        TreeNode nodeE = new TreeNode("E", newArrayList(), 6.0);
        TreeNode nodeF = new TreeNode("F", newArrayList(), 3.0);

        TreeNode nodeD = new TreeNode("D", newArrayList(nodeE), 4.0);
        TreeNode nodeC = new TreeNode("C", newArrayList(nodeD, nodeF), 7.0);

        //second side of the tree
        TreeNode nodeA = new TreeNode("A", newArrayList(), 14.0);

        //third side of the tree...
        TreeNode nodeH = new TreeNode("H", newArrayList(), 2.0);
        TreeNode nodeJ = new TreeNode("J", newArrayList(), 6.0);
        TreeNode nodeL = new TreeNode("L", newArrayList(), 4.0);
        TreeNode nodeM = new TreeNode("M", newArrayList(), 1.0);
        TreeNode nodeO = new TreeNode("O", newArrayList(), 2.0);
        TreeNode nodeP = new TreeNode("P", newArrayList(), 3.0);


        TreeNode nodeN = new TreeNode("N", newArrayList(nodeM, nodeP, nodeO), 2.0);
        TreeNode nodeK = new TreeNode("K", newArrayList(nodeL, nodeN), 4.0);
        TreeNode nodeI = new TreeNode("I", newArrayList(nodeJ, nodeK), 1.0);
        TreeNode nodeG = new TreeNode("G", newArrayList(nodeH, nodeI), 3.0);


        TreeNode root = new TreeNode("B", newArrayList(nodeC, nodeA, nodeG), 0.0);

        DiameterOfAGraph diameterOfAGraph = new DiameterOfAGraph();
        diameterOfAGraph.solution(root);

    }

}