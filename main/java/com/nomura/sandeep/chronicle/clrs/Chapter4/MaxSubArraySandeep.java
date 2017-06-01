package com.nomura.sandeep.chronicle.clrs.Chapter4;

/**
 * Created by sandeep on 12/12/2016.
 */
public class MaxSubArraySandeep {

    public static void main(String[] args) {
        int[] A = new int[]{10, 11, 7, 10, 6};
        int[] A1 = new int[]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
        MaxSubArraySandeep m = new MaxSubArraySandeep();

        System.out.println(" ==> " + m.solution(A1));
    }

/*
    public int solution(int[] A) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        boolean minReset = false;
        int maxProfit = 0;
        for (int i = 0; i < A.length; i++) {
            if (min < A[i]) {
                min = A[i];
//                maxProfit = 0;
            }
            if (max > A[i]) {
                max = A[i];
//                maxProfit = 0;
            }
            maxProfit = Math.max(maxProfit, (max - min));
        }

        return maxProfit;

    }
*/

    /**
     * Below solution is O(n) in run-time and O(n) is space.
     */

    public int solution(int[] A) {
        int maxProfit = 0;
        if (A.length == 0 || A.length > Integer.MAX_VALUE) {
            return maxProfit;
        }
        int[] diffs = new int[A.length - 1];
        for (int i = 0; i < A.length - 1; i++) {
            diffs[i] = A[i + 1] - A[i];
        }

        int maxProfitTillNow = 0;

        for (int i = 0; i < diffs.length; i++) {
            maxProfitTillNow = Integer.max(0, maxProfitTillNow + diffs[i]);
            maxProfit = Integer.max(maxProfit, maxProfitTillNow);
        }

        return maxProfit < 0 ? 0 : maxProfit;
    }

}
