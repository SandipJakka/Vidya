package com.nomura.sandeep.chronicle.codility;

import java.util.Arrays;

/**
 * Created by sandeep on 12/3/2016.
 */
public class TriangularTriplet {

    public static void main(String[] args) {
        int[] A = new int[]{10, 2, 5, 1, 8, 20};
        int[] A1 = new int[]{10, 50, 5, 1};
        int[] A2 = new int[]{5, 3, 3};
        int[] A3 = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        TriangularTriplet t = new TriangularTriplet();

//        System.out.println("===> " + t.solution(A));
//        System.out.println("===> " + t.solution(A1));
        System.out.println("===> " + t.solution(A3));
    }

    public int solution(int[] A) {
        int yesOrNo = 0;
        Arrays.parallelSort(A);
        for (int i = 0; i < A.length; i++) {
            if (withInRange(i, A) && isTriangle(A[i], A[i + 1], A[i + 2])) {
                yesOrNo = 1;
                break;
            }
        }
        return yesOrNo;
    }

    private boolean isTriangle(int P, int Q, int R) {
        long pq = (P + Q);
        long qr = (Q + R);
        long pr = (P + R);
        return ((pq > R) && (qr > P) && (pr > Q));
    }

    private boolean withInRange(int index, int[] A) {
        return (index >= 0 && index + 1 < A.length && index + 2 < A.length);
    }
}
