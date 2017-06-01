package com.nomura.sandeep.chronicle.codility;

import java.util.BitSet;

/**
 * Created by sandeep on 11/25/2016.
 */
public class PermCheck {
    public static void main(String[] args) {
        int[] A = new int[]{4, 2, 3, 1};
        int[] A1 = new int[]{4, 2, 1};
        int[] A2 = new int[]{1, 1};
        int[] A3 = new int[]{1000_000_000};

        PermCheck p = new PermCheck();

        System.out.println("==> " + p.solution(A));
        System.out.println("==> " + p.solution(A1));
        System.out.println("==> " + p.solution(A2));
        System.out.println("==> " + p.solution(A3));

    }

    /*
        public int solution(int[] A) {
            Set<Integer> set = new HashSet<>();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < A.length; i++) {
                set.add(A[i]);
                max = Math.max(max, A[i]);
            }

            if (set.size() != A.length) {
                return 0;
            }

            for (int i = 1; i < max; i++) {
                if (!set.contains(i)) {
                    return 0;
                }
            }
            return 1;
        }
    */
    public int solution(int[] A) {
        BitSet set = new BitSet();
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 100_000) {
                return 0;
            }
            set.set(A[i]);
        }
        return set.previousClearBit(A.length) <= 0 ? 1 : 0;
    }
}
