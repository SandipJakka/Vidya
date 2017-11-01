package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 4/8/2016.
 */
public class OddPair {

    public static void main(String[] args) {
        OddPair op = new OddPair();
        int[] A = new int[]{9, 3, 9, 7, 9, 7, 9};
        System.out.println("===>" + op.solution(A));
    }

    public int solution(int[] A) {
        if (A.length == 0 || A.length % 2 != 1) {
            throw new IllegalArgumentException("");
        }
        int odd = 0;

        for (int i = 0; i < A.length; i++) {
            odd = A[i] ^ odd;
        }

        return odd;

    }
}
