package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 4/10/2016.
 */
public class EquillibriumIndex {
    public static void main(String[] args) {
        EquillibriumIndex ei = new EquillibriumIndex();
        int[] A = new int[]{-1, 3, -4, 1, -6, 2, 1};
        System.out.println("===> " + EquillibriumIndex.solution(A));
    }

    public static int solution(int[] A) {
        if (A.length <= 0 || A.length >= Integer.MAX_VALUE) {
            return -1; //Incorrect argument.
        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
        }
        int sumLeft = 0;
        //int sumRight = 0;
        for (int i = 0; i < A.length; i++) {
            int sumRight = sum - sumLeft;
            if (sumLeft == sumRight) {
                return i;
            }
            sumLeft = sumLeft + A[i];
        }
        return -1;

    }

}
