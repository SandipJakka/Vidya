package com.nomura.sandeep.chronicle.clrs.chapter6;

import java.util.Arrays;

/**
 * Created by sandeep on 12/15/2016.
 */
public class ExtractMaxKey {

    public static void main(String[] args) {
        ExtractMaxKey e = new ExtractMaxKey();
        int[] A = new int[]{15, 13, 9, 5, 12, 8, 7, 4, 0, 6, 2, 1};
        System.out.println("==> " + e.xtractMax(A));


    }

    public int xtractMax(int[] A) {
        MaxHeapify m = new MaxHeapify();
        BuildMaxHeap b = new BuildMaxHeap();
        int max = A[0];
        A = Arrays.copyOfRange(A, 1, A.length);
//        m.maxHepifyIterative(A, 0, A.length-1);
        b.buildMaxHeap(A);
        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }

        return max;
    }
}
