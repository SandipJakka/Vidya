package com.nomura.sandeep.chronicle;

public class MaxProfits {

    public static void main(String[] args) {
        MaxProfits d = new MaxProfits();
        int[] A = new int[]{23171, 21011, 21123, 21366, 21013, 21367};
        System.out.println("===> + " + d.solution(A));
    }

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
