package com.nomura.sandeep.chronicle.codility;

import java.util.Arrays;

/**
 * Created by sandeep on 12/4/2016.
 */
public class MaxProductOfThree {
    public static void main(String[] args) {
        int[] A = new int[]{-3, 1, 2, -2, 5, 6};
        int[] A1 = new int[]{-3, -1, -2, -2, -5, -6};
        int[] A2 = new int[]{0, 0, 0};
        int[] A3 = new int[]{5};

        MaxProductOfThree m = new MaxProductOfThree();

        System.out.println("==> " + m.solution(A));
        System.out.println("==> " + m.solution(A1));
        System.out.println("==> " + m.solution(A2));
        System.out.println("==> " + m.solution(A3));
    }


        public int solution(int[] A) {
            int max = 0;
            if (A.length == 2) {
                return A[0] * A[1];
            } else if (A.length == 1) {
                return A[0];
            } else {

                Arrays.parallelSort(A);
                int sz = A.length;
                int start = A[0] * A[1] * A[sz - 1];
                int end = A[sz - 1] * A[sz - 2] * A[sz - 3];
                max = start > end ? start : end;
            }
            return max;
        }


    private boolean withInRange(int index, int[] A) {
        return (index >= 0 && index + 1 < A.length && index + 2 < A.length);
    }

}
