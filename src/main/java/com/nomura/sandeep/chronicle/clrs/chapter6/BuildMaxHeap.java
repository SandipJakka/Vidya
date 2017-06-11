package com.nomura.sandeep.chronicle.clrs.chapter6;

/**
 * Created by sandeep on 12/14/2016.
 */
public class BuildMaxHeap {


    public static void main(String[] args) {
        int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        BuildMaxHeap m = new BuildMaxHeap();
        m.buildMaxHeap(A);

        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }

    }

    // Indices (n/2)+1 ...n are all leaves.. so we run maxHeapify on 0...(n/2)
    //
    public void buildMaxHeap(int[] A) {
        MaxHeapify m = new MaxHeapify();
        int n = A.length;
        for (int i = (n / 2); i >= 0; i--) {
            m.maxHepifyIterative(A, i, A.length);
        }
    }

}
