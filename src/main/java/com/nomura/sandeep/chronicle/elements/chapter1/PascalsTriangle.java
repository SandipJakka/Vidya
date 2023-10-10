package com.nomura.sandeep.chronicle.elements.chapter1;

/**
 * Created by sandeep.jakka on 12/14/17.
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        PascalsTriangle p = new PascalsTriangle();
        int[][] t = p.printPascalsTriangel(6);
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t.length; j++) {
                System.out.print(t[i][j] + "   ");
            }
            System.out.println("");
        }
        System.out.println("=================");
        p.printNthRow(6);
    }

    public int[][] printPascalsTriangel(int noOfRows) {
        int[][] pas = new int[noOfRows][noOfRows];
        for (int r = 0; r < noOfRows; r++) {
            for (int el = 0; el < (r + 1); el++) {
                pas[r][el] = (el == 0 || el == (r + 1)) ? 1 : (pas[r - 1][el - 1] + pas[r - 1][el]);
            }
        }
        return pas;
    }

    public void printNthRow(int n) {
        int x = 1;
        for (int c = 0; c <= n; c++) {
            System.out.print(x + " ");
            x = x * (n - c) / (c + 1);
        }
    }
}
