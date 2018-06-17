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
        int i = 0, j = 1;
        int cel = 0;
        while (j < A.length) {
            System.out.println("i=" + i + " , j = " + j);
            if (A[i][j]) {
                cel = j;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return cel;
    }

}
