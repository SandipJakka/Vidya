package com.nomura.sandeep.chronicle.codility;

/**
 * Created by sandeep on 11/26/2016.
 */
public class PassingCars {

    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 0, 1, 1};
        int[] A1 = new int[]{0, 0, 0, 0, 0};
        int[] A2 = new int[]{0};
        int[] A3 = new int[]{1, 1};
        int[] A4 = new int[]{1, 1};
        int[] A5 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        PassingCars p = new PassingCars();

        System.out.println("===> " + p.solution(A));
        System.out.println("===> " + p.solution(A1));
        System.out.println("===> " + p.solution(A2));
        System.out.println("===> " + p.solution(A3));
        System.out.println("===> " + p.solution(A4));
        System.out.println("===> " + p.solution(A5));
    }

    public int solution(int[] A) {
        int max = 0;
        int wholeSum = 0;
        for (int i = 0; i < A.length; i++) {
            wholeSum = wholeSum + A[i];
        }
//        System.out.println("ws " + wholeSum);
        int rollingSum = 0;
        for (int i = 0; i < A.length; i++) {
            rollingSum = rollingSum + A[i];
            if (A[i] == 0) {
//                System.out.println("i = " + i + " rollingSum = " + rollingSum);
                max = max + (wholeSum - rollingSum);
                if (max > 1_000_000_000) {
                    return -1;
                }
//                System.out.println("max = " + max);
            }
        }
        return max;
    }
}
