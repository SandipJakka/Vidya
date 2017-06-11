package com.nomura.sandeep.chronicle.clrs.chapter9;

import java.util.Arrays;

/**
 * Let X[1...n] and Y [1...n] be 2 sorteed arrays..
 * O(log n) solution to find median of all 2n elements....
 */
public class Median2Arrays {

    public static int median(int[] X, int[] Y) {
        if (X.length == 1 && Y.length == 1) {
            return Math.max(X[0], Y[0]);
        } else if (X.length == 2 && Y.length == 2) {
            return Math.max(Math.min(X[0], X[1]), Math.min(Y[0], Y[1]));
        } else {
            int xMed = median(X.length);
            if (Y[xMed] > X[xMed]) {
                if (X.length % 2 == 0) {
                    return median(Arrays.copyOfRange(X, xMed + 1, X.length), Arrays.copyOfRange(Y, 0, xMed + 1));
                } else {
                    return median(Arrays.copyOfRange(X, xMed, X.length), Arrays.copyOfRange(Y, 0, xMed + 1));
                }
            } else {
                if (X.length % 2 == 0) {
                    return median(Arrays.copyOfRange(X, 0, xMed + 1), Arrays.copyOfRange(Y, xMed + 1, Y.length));
                } else {
                    return median(Arrays.copyOfRange(X, 0, xMed + 1), Arrays.copyOfRange(Y, xMed, Y.length));
                }
            }
        }
    }


    private static int median(int lenght) {
        return (lenght % 2) == 0 ? (lenght - 1) / 2 : lenght / 2;
    }

    public static void main(String[] args) {
        int[] X = new int[]{21, 29, 70};
        int[] Y = new int[]{31, 40, 66};

        int[] X1 = new int[]{1, 12, 15, 26, 38};
        int[] Y1 = new int[]{2, 13, 17, 30, 45};


        int[] X2 = new int[]{1, 2, 3, 7};
        int[] Y2 = new int[]{4, 6, 8, 10};

        int[] X3 = new int[]{1, 12, 15, 26, 38, 71};
        int[] Y4 = new int[]{2, 13, 17, 30, 45, 99};

        System.out.println(median(X, Y));
        System.out.println(median(X1, Y1));
        System.out.println(median(X2, Y2));
        System.out.println(median(X3, Y4));

        System.out.println("========================");
        System.out.println(med(X, Y));
        System.out.println(med(X1, Y1));
        System.out.println(med(X2, Y2));
        System.out.println(med(X3, Y4));
    }

    public static int med(int[] A, int[] B) {
        return median_sorted_array(A, 0, A.length - 1, B, 0, B.length - 1);
    }

    private static int median_sorted_array(int[] A, int startA, int endA, int[] B, int startB, int endB) {
        /*if (A.length == 1 && B.length == 1) {
            return Math.min(A[0], B[0]);
        } else*/
        if ((endA - startA) == 1 && (endB - startB) == 1) {
            return Math.max(Math.max(A[0], B[0]), Math.min(A[1], B[1]));
        } else {
            int medianAIndex = medianA(A, startA, endA) +  startA;
            int medianBIndex = medianA(B, startB, endB) + + startB;
            int medianA = A[medianAIndex];
            int medianB = B[medianBIndex];
            if (medianA == medianB) {
                return medianA;
            } else if (medianA < medianB) {
                startA = medianAIndex;
                endB = medianBIndex;
            } else {
                endA = medianAIndex;
                startB = medianBIndex;
            }
            return median_sorted_array(A, startA, endA, B, startB, endB);
        }
    }

    private static int medianA(int[] A, int startA, int endA) {
        return median(endA - startA + 1);
    }

}
