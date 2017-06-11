package com.nomura.sandeep.chronicle.clrs.chapter2;

/**
 * Inversion is : A[1...n] where i < j and A[i] > A[j]
 * <p>
 * Write a program to get the number of inversion in any permutation in O(n log n) time.
 */
public class Inversion {

    public static void main(String[] args) {
        int[] A = new int[]{31, 41, 59, 26, 58, 5};
        Inversion in = new Inversion();
        int n = in.inversions(A, 0, 5);
        System.out.println("==> " + n);
    }

    public int inversions(int[] A, int s, int e) {
        if (s < e) {
            int inv = 0;
            int m = (s + e) / 2;
            inv = inv + inversions(A, s, m);
            inv = inv + inversions(A, m + 1, e);
            inv = inv + doCheck(A, s, m, e);
            return inv;
        } else {
            return 0;
        }
    }

    private int doCheck(int[] A, int s, int m, int e) {
        int inversion = 0;
        int n1 = m - s + 1;
        int n2 = e - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = A[i + s];

        for (int i = 0; i < n2; i++) {
            R[i] = A[m + i + 1];
        }

        for (int i = 0, j = 0, k = s; k <= e; k++) {
            if (i == n1) {
                A[k] = R[j++];
            } else if (j == n2) {
                A[k] = L[i++];
            } else if (L[i] <= R[j]) {
                A[k] = L[i++];
            } else {
                A[k] = R[j++];
                inversion += n1 - i;
            }
        }
        return inversion;
    }

}