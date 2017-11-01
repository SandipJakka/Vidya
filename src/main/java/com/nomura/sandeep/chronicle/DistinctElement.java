package com.nomura.sandeep.chronicle;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 *
 */
public class DistinctElement {

    public static void main(String[] args) {
        DistinctElement ds = new DistinctElement();
        int A[] = new int[]{2, 1, 1, 2, 3, 1, 8, 9, 0, 1, 2, 99999, 999999, -1, -2, -3, -8, -7};
        System.out.println("===>" + ds.solution(A));
    }

    public int solution(int[] A) {
        if (A.length == 0 || A.length > Integer.MAX_VALUE) {
            return 0;
        }
        BitSet positiveSet = new BitSet(1_000_000);
        BitSet negaSet = new BitSet(1_000_000);
        List<Integer> uni = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            int tmp = Math.abs(A[i]); //pos
            if (A[i] <= 0 && !negaSet.get(tmp)) {
                negaSet.set(tmp);
                uni.add(A[i]);
            } else if (A[i] > 0 && !positiveSet.get(tmp)) {
                positiveSet.set(tmp);
                uni.add(A[i]);
            }
        }
        return uni.size();
    }
}
