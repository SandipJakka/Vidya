package com.nomura.sandeep.chronicle.test;

/**
 * Created by sandeep on 4/10/2016.
 */
public class Test2 {

    public static int solution(int[] A) {
        if (A.length <= 0 || A.length >= Integer.MAX_VALUE) {
            return -1; //Incorrect argument.
        }
        int index = 0;
        int sum = 0;
        int count = 0;

        for (int i = 0; i < A.length; i++) {
            index = index + i + 1;
            sum = sum + A[i];
            if (index == sum) {
                count++;
            }
        }
        return count;

    }
}
