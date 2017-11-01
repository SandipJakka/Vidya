package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 4/8/2016.
 */
public class PermMissingElem {
    public static void main(String[] args) {
        PermMissingElem op = new PermMissingElem();
        int[] A = new int[]{1, 3, 2, 4};
        System.out.println("===>" + op.solution(A));
    }

    public int solution(int[] A) {
        if (A.length == 0) {
            throw new IllegalArgumentException("");
        }
        //  int expectedSum = BigDecimal.valueOf(A.length+1).multiply(BigDecimal.valueOf(A.length+2)).divide(BigDecimal.valueOf(2)).intValue();
        long expectedSum = ((A.length + 1) * (A.length + 2)) / 2;
        long actualSum = 0;
        for (int i = 0; i < A.length; i++) {
            actualSum = actualSum + A[i];
        }

        return (int) (expectedSum - actualSum);

    }
}
