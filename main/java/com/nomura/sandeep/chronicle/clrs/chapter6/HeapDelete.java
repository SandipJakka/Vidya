package com.nomura.sandeep.chronicle.clrs.chapter6;

/**
 * Created by sandeep on 12/15/2016.
 */
public class HeapDelete {

    public static void main(String[] args) {
        int[] A = new int[]{16, 15, 10, 14, 7, 9, 3, 2, 8, 1};
        HeapDelete d = new HeapDelete();
        d.heapDelete(A, 1);

        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }

    }


    public void heapDelete(int[] A, int index) {
        if (index >= A.length) {
            throw new IllegalArgumentException("");
        }
        A[index] = A[A.length - 1];
        MaxHeapify m = new MaxHeapify();
        m.maxHepifyIterative(A, index, A.length - 1);
    }
}
