package com.nomura.sandeep.chronicle.clrs.chapter8;

/**
 *
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] B = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        int[] A = CountingSort.sort(B, 6);
        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }

    }

    public static int[] sort(int[] A, int k) {
        int[] B = new int[A.length];
        int[] C = new int[k];

        for (int i = 0; i < A.length; i++) {
            C[A[i]] = C[A[i]] + 1;
        }

        for (int i = 1; i < k; i++) {
            C[i] = C[i] + C[i - 1];
        }

        // For stability
        for (int i = A.length - 1; i >= 0; i--) {
            B[C[A[i]] - 1] = A[i];
            C[A[i]] = C[A[i]] - 1;
        }
        return B;
    }
}
