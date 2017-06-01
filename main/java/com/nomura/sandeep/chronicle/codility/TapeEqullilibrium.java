package com.nomura.sandeep.chronicle.codility;

/**
 * Created by sandeep on 11/24/2016.
 */
public class TapeEqullilibrium {

    public static void main(String[] args) {
        int A[] = new int[]{3, 1, 2, 4, 3};
        //     int A[] = new int[]{-1000, 1000};
        TapeEqullilibrium tp = new TapeEqullilibrium();
        System.out.println("===>" + tp.solution(A));
    }

    public int solution(int[] A) {
        int min = Integer.MAX_VALUE;
        int absTotal = 0;
        for (int i = 0; i < A.length; i++) {
            absTotal = absTotal + A[i];
        }
        int runningTotal = 0;
        for (int i = 0; i < A.length - 1; i++) {
            runningTotal = runningTotal + A[i];
            min = Math.min(min, Math.abs((runningTotal) - (absTotal - runningTotal)));
        }

        return min;
    }
}
