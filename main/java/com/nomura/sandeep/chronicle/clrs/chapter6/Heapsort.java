package com.nomura.sandeep.chronicle.clrs.chapter6;

/**
 * Created by sandeep on 12/14/2016.
 */
public class Heapsort {

    public static void main(String[] args) {
        int[] A = new int[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        int[] A1 = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};

        Heapsort h = new Heapsort();
        h.heapSort(A1);

        for (int i = 0; i < A1.length; i++) {
            System.out.print(" " + A1[i]);
        }

    }

    /**
     * O(n) + n-1 * O ( log n ) =
     * n log (n)  ==> whole heapsort
     */
    public void heapSort(int[] A) {
        BuildMaxHeap b = new BuildMaxHeap();
        MaxHeapify m = new MaxHeapify();
        b.buildMaxHeap(A); /// O(n)
        int heapSize = A.length - 1;
        for (int i = A.length - 1; i > 0; i--) {
            int temp = A[i];
            int index = A.length - heapSize;
            A[i] = A[index];
            A[index] = temp;
            heapSize = heapSize - 1;
            m.maxHepifyIterative(A, i, heapSize); ///O ( log n ) called n-1 times
        }
    }
}
