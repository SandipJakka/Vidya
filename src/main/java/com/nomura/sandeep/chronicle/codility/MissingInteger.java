package com.nomura.sandeep.chronicle.codility;

/**
 * Created by sandeep on 11/24/2016.
 */
public class MissingInteger {

    public static void main(String[] args) {
        int[] A = new int[]{1, 3, 6, 4, 1, 2};
        int[] A1 = new int[]{6, -1000};
        int[] A2 = new int[]{1};
        int[] A3 = new int[]{2, Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] A4 = new int[100_000];
        for (int i = 2; i < 100_000; i++) {
            A4[i] = i;
        }
        MissingInteger m = new MissingInteger();
        System.out.println("===> " + m.solution(A));
        System.out.println("===> " + m.solution(A1));
        System.out.println("===> " + m.solution(A2));
        System.out.println("===> " + m.solution(A3));
        System.out.println("===> " + m.solution(A4));
    }

/*    public int solution(int[] A) {
        int minPositiveInt = 0;
        BitSet positiveSet = new BitSet(A.length);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                positiveSet.set(A[i]);
                max = Math.max(max, A[i]);
            }
        }
        //All -ves
        if (max == Integer.MIN_VALUE) {
            return 1;
        }

        System.out.println(positiveSet);
        System.out.println(max);
        BitSet interested = positiveSet.get(0, max);
        System.out.println(interested);
        minPositiveInt = positiveSet.previousSetBit(max - 1);
        System.out.println(minPositiveInt);
        if (interested.cardinality() == 0) {
            minPositiveInt = 1;
        } else if (minPositiveInt <= 0) {
            minPositiveInt = positiveSet.nextClearBit(max);
        } else {
            minPositiveInt = minPositiveInt + 1;
        }

        return minPositiveInt;
    }*/


 /*   public int solution(int[] A) {
        int minPosInt = 1;
        BitSet set = new BitSet();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                set.set(A[i]);
                max = Math.max(max, A[0]);
            }
        }
        if (max == Integer.MIN_VALUE) {
            return minPosInt;
        }

        System.out.println("" + "," + set.size());

        minPosInt = set.nextClearBit(1);
        return minPosInt;
    }*/

    public int solution(int[] A) {
        int minPosInt = 1;
        int[] set = new int[100_000];
        //int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                set[A[i]] = A[i];
            }
        }
        for (int i = 0; i < set.length; i++) {
            if (set[i] == 0) {
                return i + 1;
            }
        }
        /*if (max == Integer.MIN_VALUE) {
            return minPosInt;
        }*/

        //System.out.println("" + "," + set.size());

//        minPosInt = set.nextClearBit(1);
        return set.length + 1;
    }

}
