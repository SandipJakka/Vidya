package com.nomura.sandeep.chronicle.elements.graphs;

public class FindCelebrity {

    public static void main(String[] args) {
        boolean[][] A = new boolean[][]
                {
                        {true, true, true, false},
                        {false, true, true, false},
                        {false, false, true, false},
                        {true, false, true, true}
                };

        boolean[][] A1 = new boolean[][]
                {
                        {true, false, false, false},
                        {true, true, true, false},
                        {true, false, true, true},
                        {true, false, true, true}
                };

        boolean[][] A2 = new boolean[][]
                {
                        {true, true, false, false},
                        {true, true, false, false},
                        {false, true, true, true},
                        {false, true, true, true}
                };
        boolean[][] A3 = new boolean[][]
                {
                        {true, false, false, true},
                        {true, true, false, true},
                        {false, true, true, true},
                        {false, false, false, true}
                };
        FindCelebrity f = new FindCelebrity();
        System.out.println(f.findCelebrity(A));
        System.out.println("================");
        System.out.println(f.findCelebrity(A1));
        System.out.println("================");
        System.out.println(f.findCelebrity(A2));
        System.out.println("================");
        System.out.println(f.findCelebrity(A3));

    }

    public int findCelebrity(boolean[][] A) {
        int row = 0, col = 1;
        int cel = 0;
        while (col < A.length) {
            System.out.println("row=" + row + " , col = " + col);
            if (A[row][col]) {
                System.out.println("True");
                cel = col;
                row++;
                col++;
            } else {
                col++;
            }
        }
        return cel;
    }

}
