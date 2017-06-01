package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 4/8/2016.
 */
public class ArrayRightShift {

    public static void main(String[] args) {
        ArrayRightShift sh = new ArrayRightShift();

        int[] A = new int[]{3, 8, 9, 7, 6};
        A = sh.solution(A, 3);
        for (int i = 0; i < A.length; i++)
            System.out.print(" " + A[i]);
    }

    public int[] solution(int[] A, int K) {
        if (A.length == 0 || K < 0 || A.length == 1) {
            return A;
        }
        if (K == 0) {
            return A;
        }
        int tmp = K;
        if (K > A.length) {
            tmp = K % A.length;
        }
        int startIndex = A.length - tmp;
        int[] B = new int[A.length];
        int i = 0;
        int j = startIndex;
        while (i < A.length) {
            B[i] = A[j];
            i++;
            int tmp1 = j + 1;
            if (tmp1 >= A.length) {
                j = 0;
            } else {
                j++;
            }
        }
        return B;
    }
}
