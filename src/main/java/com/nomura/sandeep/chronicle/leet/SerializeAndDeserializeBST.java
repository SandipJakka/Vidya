package com.nomura.sandeep.chronicle.leet;


/**
 * Created by sandeep.jakka on 4/27/19.
 */
public class SerializeAndDeserializeBST {
    private static class TreeNode {
        public final int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        printInorder(root, stringBuilder);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("-");
        printPreorder(root, stringBuilder);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data != null && data.trim().length() > 0) {
            String[] in = (data.split("-")[0]).split(",");

            String[] pre = data.split("-")[1].split(",");
            int preIndex = 0;
            TreeNode root = buildTree(in, pre, 0, in.length - 1, Integer.valueOf(0));
            return root;
        }
        return null;

    }

    //static int preIndex = 0;

    TreeNode buildTree(String[] in, String[] pre, int inStrt, int inEnd, Integer preIndex) {
        if (inStrt > inEnd)
            return null;

        /* Pick current node from Preorder traversal using preIndex
           and increment preIndex */
        TreeNode tNode = new TreeNode(Integer.valueOf(pre[preIndex]));

        preIndex = preIndex + 1;

        System.out.println(preIndex);

        /* If this node has no children then return */
        if (inStrt == inEnd)
            return tNode;

        /* Else find the index of this node in Inorder traversal */
        int inIndex = search(in, inStrt, inEnd, Integer.toString(tNode.val));

        /* Using index in Inorder traversal, construct left and
           right subtress */
        tNode.left = buildTree(in, pre, inStrt, inIndex - 1, preIndex);
        tNode.right = buildTree(in, pre, inIndex + 1, inEnd, preIndex);

        return tNode;
    }


    /* Function to find index of value in arr[start...end]
     The function assumes that value is present in in[] */
    int search(String[] arr, int strt, int end, String value) {
        int i;
        for (i = strt; i <= end; i++) {
            if (arr[i].equals(value))
                return i;
        }
        return i;
    }

    void printInorder(TreeNode node, StringBuilder stringBuilder) {
        if (node == null)
            return; 
  
        /* first recur on left child */
        printInorder(node.left, stringBuilder);
  
        /* then print the data of TreeNode */
        stringBuilder.append(node.val).append(",");
  
        /* now recur on right child */
        printInorder(node.right, stringBuilder);
    }

    /* Given a binary tree, print its TreeNodes in preorder*/
    void printPreorder(TreeNode node, StringBuilder stringBuilder) {
        if (node == null)
            return; 
  
        /* first print data of TreeNode */
        stringBuilder.append(node.val).append(",");
  
        /* then recur on left sutree */
        printPreorder(node.left, stringBuilder);
  
        /* now recur on right subtree */
        printPreorder(node.right, stringBuilder);
    }

    void printInorder(TreeNode node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.val + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    public static void main(String[] args) {
        TreeNode nod100 = new TreeNode(100);
        TreeNode nod80 = new TreeNode(80);
        TreeNode nod50 = new TreeNode(50);
        TreeNode nod89 = new TreeNode(89);
        TreeNode nod125 = new TreeNode(125);
        TreeNode nod110 = new TreeNode(110);
        TreeNode nod200 = new TreeNode(200);

        nod100.left = nod80;
        nod100.right = nod125;

        nod80.left = nod50;
        nod80.right = nod89;

        nod125.left = nod110;
        nod125.right = nod200;

        SerializeAndDeserializeBST s = new SerializeAndDeserializeBST();

        String serialize = s.serialize(nod100);
        System.out.println(serialize);

        s.printInorder(s.deserialize(serialize));

    }

}