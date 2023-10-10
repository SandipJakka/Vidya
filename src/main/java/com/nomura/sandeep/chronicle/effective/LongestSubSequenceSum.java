package com.nomura.sandeep.chronicle.effective;

import java.util.Arrays;

/**
 *
 *
 */
public class LongestSubSequenceSum {

    private int maxSumBrute(int[] array, int start, int end, int max) {
        System.out.printf("start=%d, end= %d \n", start, end);

        int length = array.length - 1;
        if ((start == length) && (end >= length)) {
            return max;
        }
        int newMax = Math.max(max, add(array, start, end));

        if (end == length) {
            start = start + 1;
            end = start;
        }
        return maxSumBrute(array, start, end + 1, newMax);

    }

    private int add(int[] array, int start, int end) {
        int sum = 0;
        for (int index = start; index < end; index++) {
            sum += array[index];
        }
        return sum;
    }


    private int dp(int[] array) {
        int n = array.length;
        int[][] cache = new int[n][n];

        return usingDp(array, cache);
    }

    private int usingDp(int[] array, int[][] cache) {
        prepopulate(array, cache);
        print(cache);
        int max = Integer.MIN_VALUE;
        for (int[] aCache : cache) {
            for (int anACache : aCache) {
                max = Math.max(max, anACache);
            }
        }
        return max;
    }

    private void print(int[][] arr) {
        for (int[] a : arr) {
            for (int v : a) {
                System.out.printf(" %s,", v == Integer.MIN_VALUE ? "  " : v);
            }
            System.out.printf("\n");
        }
    }

    private void prepopulate(int[] array, int[][] cache) {
        for (int[] a : cache) {
            Arrays.fill(a, Integer.MIN_VALUE);
        }

        int len = array.length;

        for (int in = 0; in < len; in++) {
            cache[in][in] = array[in];
        }

        for (int row = 0; row < len; row++) {
            for (int col = row + 1; col < len; col++) {
                cache[row][col] = cache[row][col - 1] + array[col];
            }
        }
    }

    private int dpOptimized(int[] array) {
        int[] maxz = new int[array.length];
        Arrays.fill(maxz, Integer.MIN_VALUE);
        return usingDpOptimized(array, maxz);
    }

    private int usingDpOptimized(int[] array, int[] maxz) {
        prepopulateSingleArray(array, maxz);
        return 0;
    }

    private void prepopulateSingleArray(int[] array, int[] maxz) {


    }

    private int kadane(int[] arr) {

        int maxSumSoFor = arr[0], maxSumEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            // maxSum subbarray =
            maxSumEndingHere = Math.max(arr[i], maxSumEndingHere + arr[i]);


            if (maxSumSoFor < maxSumEndingHere) {
                maxSumSoFor = maxSumEndingHere;
            }

        }
        return maxSumSoFor;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        LongestSubSequenceSum l = new LongestSubSequenceSum();
        //System.out.printf("Sum of max subsequence = %d", l.maxSumBrute(arr, 0, 0, Integer.MIN_VALUE));
//        System.out.printf("Sum of max subsequence = %d", l.dp(arr));
        System.out.printf("Sum of max subsequence = %d \n ", l.kadane(arr));
        System.out.printf("Sum of max subsequence = %d \n", l.kadane(new int[]{-1, 2, 3, -4}));
    }

}
