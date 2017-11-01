package com.nomura.sandeep.chronicle.chintan;

import java.util.Arrays;

/**
 * Given an array A , put all the zeros in the right ...
 */
public class ZerosOneSide {

    public static int[] moveZerosToRight(int[] A) {
        if (A == null || A.length == 0) {
            throw new IllegalArgumentException(" Nothing in A");
        }
        if (A.length == 1) {
            return A;
        }
        int i = 0, j = A.length - 1;
        while (i <= j) {
            while (j > 0 && A[j] == 0) {
                --j;
            }
            if (j >= 0 && A[i] == 0) {
                A[i] = A[j];
                A[j] = 0;
                --j;
            }
            ++i;

        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = new int[0];
//        A = moveZerosToRight(A);
        print(A);

        A = new int[]{1};
        A = moveZerosToRight(A);
        print(A);

        A = new int[]{2, 0};
        A = moveZerosToRight(A);
        print(A);


        A = new int[]{0, 0, 0, 0, 0};
        A = moveZerosToRight(A);
        print(A);

        A = new int[]{0, 7};
        A = moveZerosToRight(A);
        print(A);


        A = new int[]{0, 1, 2, 0, 6, 9};
        A = moveZerosToRight(A);
        print(A);

        A = new int[]{7, 1, 2, 8, 6, 9};
        A = moveZerosToRight(A);
        print(A);

    }

    public static void print(int[] A) {
        System.out.println(Arrays.toString(A));
    }
}
