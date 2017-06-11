package com.nomura.sandeep.chronicle.clrs.chapter2;

/**
 * Created by sandeep on 12/10/2016.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] A = new int[]{31, 41, 59, 26, 41, 58};
        MergeSort i = new MergeSort();
        i.mergeSort(A, 0, A.length - 1);
        for (int j = 0; j < A.length; j++) {
            System.out.print(" " + A[j]);
        }
    }

    private void merge(int[] A, int s, int m, int e) {

        int[] L = new int[m - s + 1 + 1];
        int[] R = new int[e - m + 1];

        for (int i = 0, x = s; i < L.length - 1; i++, x++) {
            L[i] = A[x];
        }

        for (int i = 0, x = m + 1; i < R.length - 1; i++, x++) {
            R[i] = A[x];
        }

        L[L.length - 1] = Integer.MAX_VALUE;
        R[R.length - 1] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        for (int k = s; k <= e; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i += 1;
            } else {
                A[k] = R[j];
                j += 1;
            }
        }

    }

    public void merge1(int[] A, int s, int m, int e) {
        int n1 = m - s + 1;
        int n2 = e - m;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0, x = s; i < L.length; i++, x++) {
            L[i] = A[x];
        }

        for (int i = 0, x = m + 1; i < R.length; i++, x++) {
            R[i] = A[x];
        }
      /*  int i = 0, j = 0, k = s;
        for (; k < e; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i += 1;
            } else {
                A[k] = R[j];
                j += 1;
            }
        }

        while (i < L.length) {
            A[k] = L[i];
            i++;
            k++;
        }

        while (j < R.length) {
            A[k] = R[j];
            j++;
            k++;
        }*/

        for (int i = 0, j = 0, k = s; k <= e; k++) {
            if (i == n1) {
                A[k] = R[j++];
            } else if (j == n2) {
                A[k] = L[i++];
            } else if (L[i] <= R[j]) {
                A[k] = L[i++];
            } else {
                A[k] = R[j++];
            }
        }


    }


    /*public static void merge(int[] arr, int p, int q, int r) {
        final int n1 = q - p + 1;
        final int n2 = r - q;
        final int[] left = new int[n1 + 1];
        final int[] right = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            left[i] = arr[p + i];
        }

        for (int i = 0; i < n2; i++) {
            right[i] = arr[q + i + 1];
        }

        left[n1] = Integer.MAX_VALUE;
        right[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int k = p; k <= r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i++];
            } else {
                arr[k] = right[j++];
            }
        }
    }*/

    public void mergeSort(int[] A, int s, int e) {
        if (s < e) {
            int m = (s + e) / 2;
            mergeSort(A, s, m);
            mergeSort(A, m + 1, e);
            merge1(A, s, m, e);
        }
    }

}
