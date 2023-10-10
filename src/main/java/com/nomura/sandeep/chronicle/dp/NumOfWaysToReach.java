package com.nomura.sandeep.chronicle.dp;

import java.util.Arrays;

public class NumOfWaysToReach {

    // O(2^n)
    private int numberOfWaysToReachBrute(int row, int col, int[][] arr) {
        //
        if (row == 0 && col == 0) {
            return 0;
        }
        // The first row and col can only be reached in 1 way .. this is terminating condition.
        if (row == 0 || col == 0) {
            return 1;
        }
        return numberOfWaysToReachBrute(row, col - 1, arr) +
                numberOfWaysToReachBrute(row - 1, col, arr);
    }

    public static void main(String[] args) {
        NumOfWaysToReach n = new NumOfWaysToReach();
        //3,4
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        // top down approach .. start from (2,3)
        System.out.printf("Number of ways from (0,0) to (2,3) = %d \n", n.numberOfWaysToReachBrute(2, 3, arr));
        System.out.printf("Number of ways from (0,0) to (2,3) = %d \n", n.dp(arr));
    }

    // O(n^2)
    private int dp(int[][] arr) {
        System.out.printf("# rows = %d, # of col =%d \n", arr.length, arr[0].length);
        int[][] cache = new int[arr.length][arr[0].length];

        for (int[] one : arr)
            Arrays.fill(one, 0);

        return usingDp(arr, cache);
    }

    private void print(int[][] arr) {
        for (int[] a : arr) {
            for (int v : a) {
                System.out.printf(" %s,", v == Integer.MIN_VALUE ? "  " : v);
            }
            System.out.printf("\n");
        }
    }

    private int usingDp(int[][] arr, int[][] cache) {
        prePopulate(cache);
        return cache[arr.length - 1][arr[0].length - 1];
    }

    private void prePopulate(int[][] cache) {
        cache[0][0] = 0;

        for (int row = 1; row < cache.length; row++) {
            cache[row][0] = 1;
        }

        for (int col = 1; col < cache[0].length; col++) {
            cache[0][col] = 1;
        }

        for (int row = 1; row < cache.length; row++) {
            for (int col = 1; col < cache[0].length; col++) {
                cache[row][col] = cache[row - 1][col] + cache[row][col - 1];
            }
        }
        print(cache);
    }
}