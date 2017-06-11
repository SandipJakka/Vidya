package com.nomura.sandeep.chronicle.codility;

import java.util.BitSet;

/**
 * Created by sandeep on 11/25/2016.
 */
public class FrogRiverOne {
    public static void main(String[] args) {
        int[] A = new int[]{1, 3, 1, 4, 2, 3, 5, 4};
        int[] A1 = new int[]{1, 3, 1, 3, 2, 1, 3};
        FrogRiverOne f = new FrogRiverOne();
        System.out.println("==> " + f.solution(5, A));
        System.out.println("==> " + f.solution(3, A1));
    }

    public int solution(int X, int[] A) {
        BitSet set = new BitSet();
        boolean found = false;
        int index = -1;
        for (int i = 0; i < A.length && !found; i++) {
            if (A[i] > 100_000) {
                return -1;
            }
            set.set(A[i]);
            if (A[i] == X) {
                found = true;
                index = i;
            }
        }

        if (found && set.previousClearBit(X) <= 0) {
            return index;
        } else {
            return -1;
        }
    }

}
