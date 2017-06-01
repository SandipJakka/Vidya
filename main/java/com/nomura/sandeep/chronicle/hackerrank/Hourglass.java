package com.nomura.sandeep.chronicle.hackerrank;

import java.util.Scanner;

/**
 * Objective
 * Today, we're building on our knowledge of Arrays by adding another dimension. Check out the Tutorial tab for learning materials and an instructional video!
 * <p>
 * Context
 * Given a  2D Array, :
 * <p>
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * We define an hourglass in  to be a subset of values with indices falling in this pattern in 's graphical representation:
 * <p>
 * a b c
 * d
 * e f g
 * There are  hourglasses in , and an hourglass sum is the sum of an hourglass' values.
 * <p>
 * Task
 * Calculate the hourglass sum for every hourglass in , then print the maximum hourglass sum.
 * <p>
 * Input Format
 * <p>
 * There are  lines of input, where each line contains  space-separated integers describing 2D Array ; every value in  will be in the inclusive range of  to .
 * <p>
 * Constraints
 */
public class Hourglass {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(maxHourGlass(arr));
    }

    public static int maxHourGlass(int[][] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                int sum = getHourglass(arr, i, j);
                if (sum != Integer.MIN_VALUE) {
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }

    private static int getHourglass(int[][] arr, int row, int col) {
        int sum = 0;
        boolean hasResult = false;
        if (withinRange(arr, row, col)) {
            hasResult = true;
            sum = arr[row][col] + arr[row][col + 1] + arr[row][col + 2];
            sum = sum + arr[row + 1][col + 1];
            sum += arr[row + 2][col] + arr[row + 2][col + 1] + arr[row + 2][col + 2];
        }
        return hasResult ? sum : Integer.MIN_VALUE;
    }

    private static boolean withinRange(int[][] arr, int row, int col) {
        return (row + 2 < arr[0].length && col + 2 < arr.length);
    }

}