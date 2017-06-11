package com.nomura.sandeep.chronicle.codility;

/**
 * Created by sandeep on 11/20/2016.
 */
public class CyclicRotation {

    public static void main(String[] args) {
        int A[] = new int[]{3, 8, 6, 7, 9};
        CyclicRotation cr = new CyclicRotation();
        A = cr.solution(A, 3);
        for (int i = 0; i < A.length; i++)
            System.out.print(" " + A[i]);
    }

/*
  // With Extra Storage...
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
*/

    //Without extra storage....

    public int[] solution(int[] A, int K) {
        if (A.length == 0 || K <= 0 || A.length == 1) {
            return A;
        }
        if (K > A.length) {
            K = K % A.length;
        }
        for (int i = 0; i < K; i++) {
            int prev = A[A.length - 1], curr = A[0];
            for (int j = 0; j < A.length; j++) {
                curr = A[j];
                A[j] = prev;
                prev = curr;
            }
        }
        return A;
    }
}
