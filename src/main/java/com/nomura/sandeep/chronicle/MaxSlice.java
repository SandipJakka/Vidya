package com.nomura.sandeep.chronicle;

public class MaxSlice {

    public static void main(String[] args) {
        int[] A = new int[]{5, -7, 3, 5, -2, 4, -1};
        MaxSlice m = new MaxSlice();

        System.out.println("====>" + m.solution(A));
    }

    public int solution(int[] A) {
        int maxSlice = 0;
        int maxTillNow = 0;
        if (A.length <= 0 || A.length >= Integer.MAX_VALUE) {
            return -1; //Incorrect argument.
        }
        int startIndex = 0, endIndex = -1;
        int maxStartIndx = 0, maxEndIndx = -1;
        for (int i = 0; i < A.length; i++) {
            //maxTillNow = Integer.max(0, maxTillNow + A[i]);
            if ((maxTillNow + A[i]) > 0) {
                startIndex = i;
                maxTillNow = maxTillNow + A[i];
            }

            if (maxTillNow > maxSlice) {
                endIndex = i;
                maxStartIndx = Integer.max(startIndex, maxStartIndx);
                maxEndIndx = Integer.max(endIndex, maxEndIndx);
                // startIndex = i;
                maxSlice = maxTillNow;
            }
            // maxSlice = Integer.max(maxSlice, maxTillNow);
        }
        System.out.println(" StartIndex = " + maxStartIndx + ", EndIndex = " + maxEndIndx);
        return maxSlice;
    }
}


