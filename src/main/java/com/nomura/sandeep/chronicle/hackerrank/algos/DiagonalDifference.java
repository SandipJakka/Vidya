package com.nomura.sandeep.chronicle.hackerrank.algos;

import java.util.Scanner;

/**
 *
 */
public class DiagonalDifference {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int no = scanner.nextInt();
        long pri = 0;
        long sec = 0;
        int n = no - 1;
        for (int i = 0; i < no; i++) {
            for (int j = 0; j < no; j++) {
                int ele = scanner.nextInt();
                if (isPrimary(i, j, n)) {
                    pri += ele;
                }
                if (isSecondary(i, j, n)) {
                    sec += ele;
                }
            }
        }
        System.out.println(Math.abs(pri - sec));
    }

    private static boolean isSecondary(int i, int j, int n) {
        return ((i + j) == n);
    }

    private static boolean isPrimary(int i, int j, int n) {
        return (i == j);
    }
}
