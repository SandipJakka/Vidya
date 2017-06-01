package com.nomura.sandeep.chronicle.clrs.chapter7;

/**
 * Created by sandeep on 12/25/2016.
 */
public class HoarePartitionQuickSort {

    private static int counter = 0;

    public static void main(String[] args) {
        HoarePartitionQuickSort q = new HoarePartitionQuickSort();
        int[] A = new int[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        int[] A1 = new int[]{13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21};
        int[] A2 = new int[]{5, 4, 3, 2, 1};
        int[] A3 = new int[]{4, 4, 4, 4, 4};
        int[] A5 = new int[]{1, 2, 3, 4, 5};


        q.quickSort(A, 0, A.length );
//        q.partition(A1, 0, A1.length);
        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }
        System.out.println("");
    }


    public void quickSort(int[] A, int low, int hi) {
        if (low < hi) {
            int p = partition(A, low, hi);
            quickSort(A, low, p);
            quickSort(A, p + 1, hi);
        }
    }

    public int partition(int[] A, int low, int hi) {
        int p = A[low];
        int i = low - 1;
        int j = hi;

        while (true) {
            do {
                j = j - 1;
            } while (!(A[j] <= p));

            do {
                i = i + 1;
            } while (!(A[i] >= p));

            if (i < j) {
                swap(A, i, j);
            } else {
                return j;
            }
        }
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}
