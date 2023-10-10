package com.nomura.sandeep.chronicle.elements.arrays;


import java.util.Arrays;
import java.util.Random;

public class LongIncreasingSubSequence {

    public static int[] longestIncreasingSubSequence(int[] A) {
        int[] idx = new int[]{0, 0};
        if (A == null || A.length == 0) {
            return idx;
        }

        int startIdx = 0, endIdx = 0, prev = A[0];
        int max = 0;
        int maxStartIdx = 0, maxEndIdx = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] < prev) {
                endIdx = i - 1;
                if ((endIdx - startIdx) + 1 > max) {
                    max = endIdx - startIdx + 1;
                    maxStartIdx = startIdx;
                    maxEndIdx = endIdx;
                }
                startIdx = i;
            }
            prev = A[i];
        }
        if (endIdx == 0) {
            maxEndIdx = A.length - 1;
            maxStartIdx = 0;
        }
        idx[0] = maxStartIdx;
        idx[1] = maxEndIdx;

        System.out.printf(" Array : {%s}, start = %d, end = %d (count = %d ) %n", Arrays.toString(A), maxStartIdx, maxEndIdx, max);

        return idx;
    }

    static int[] generateArray(int number, int start, int end) {
        Random r = new Random();
        return r.ints(number, start, end).toArray();
    }

    public static void main(String[] args) {
        longestIncreasingSubSequence(new int[]{2, 11, 3, 5, 13, 719, 17, 23});
        longestIncreasingSubSequence(new int[]{2, 11, 3, 5, 13, 7, 19, 17, 23});
        longestIncreasingSubSequence(new int[]{2});
        longestIncreasingSubSequence(new int[]{2, 1});
        longestIncreasingSubSequence(generateArray(10, 1, 99));
        longestIncreasingSubSequence(generateArray(15, -6, 25));
    }

}
