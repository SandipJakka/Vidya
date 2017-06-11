package com.nomura.sandeep.chronicle;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by sandeep on 4/9/2016.
 */
public class ValuationFunctionMin {
    static BitSet bs = new BitSet(3);
    /*
        static void fill(int k, int n) {
            if (k == n) {
                System.out.println(bs);
                return;
            }
            bs.set(k, false);
            fill(k + 1, n);
            bs.set(k, true);
            fill(k + 1, n);
        }
    */
    static List<BitSet> seq = new ArrayList<>();

    public static void main(String[] args) {
        ValuationFunctionMin vm = new ValuationFunctionMin();
        int[] A = new int[]{1, 5, 2, -2};
//        System.out.println("===>" + vm.solution(A));
        vm.fill(0, 3);
        System.out.println(vm.seq);
    }

    static void  fill(int k, int n) {
        if (k == n) {
            BitSet s = (BitSet) bs.clone();
            seq.add(s);
            return;
        }
        bs.set(k, false);
        fill(k + 1, n);
        bs.set(k, true);
        fill(k + 1, n);
    }

    public int solution(int[] A) {
        if (A.length == 0) {
            return -1;
        }
        List<BitSet> sequences = getAllSequences(A.length);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sequences.size(); i++) {
            BitSet sequence = sequences.get(i);
            int sum = 0;
            for (int j = 0; j < A.length; j++) {
                if (sequence.get(j)) { //set ==1 , not set == -1
                    sum = sum + A[j] * 1;
                } else {
                    sum = sum + A[j] * -1;
                }
                min = Integer.min(min, Math.abs(sum));
            }
        }
        return min;
    }

    private List<BitSet> getAllSequences(int arrLen) {
        List<BitSet> sequences = new ArrayList<>();
        BitSet bs = new BitSet(arrLen);

        sequences.add(bs);//All off. set to -1
        for (int i = 0; i < (1 << arrLen); i++) {
            BitSet bitSet = new BitSet(arrLen);

            bitSet.set(0, i);
            sequences.add(bitSet);
        }
        return sequences;
    }


}
