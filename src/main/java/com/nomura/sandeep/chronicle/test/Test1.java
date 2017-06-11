package com.nomura.sandeep.chronicle.test;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by sandeep on 4/10/2016.
 */
public class Test1 {
    public static void main(String[] args) {
        int[] A = new int[]{4, 3, 2, 5, 1, 1, 6, 8, 9, 10};
        //  int[] A = new int[]{1,3,-3};
        System.out.println("===> " + solution(A));
        System.out.println("===> " + solution1(A));

    }

    public static int solution1(int[] A) {
        if (A.length == 0) {
            return -1; //Invalid
        }
        List<Integer> list = IntStream.of(A).boxed().collect(Collectors.toList());
        int max = 0;
        for (int i = 0; i < A.length - 1; i++) {
            OptionalInt max1 = max(list.subList(0, i + 1));
            OptionalInt max2 = max(list.subList(i + 1, list.size()));
            max = Integer.max(max, Math.abs(max1.getAsInt() - max2.getAsInt()));
        }
        return max;
    }


    private static OptionalInt max(List<Integer> A) {
        return A.stream().mapToInt(i -> i).max();
    }

    public static int solution(int[] A) {
        if (A.length == 0) {
            return -1; //Invalid
        }
        int[] max = new int[A.length];
        //int j=0;
        int max1 = -Integer.MAX_VALUE;
        for (int i = A.length - 1; i >= 0; i--) {
            max1 = Integer.max(max1, A[i]);
            max[i] = max1;
            // j++ ;
        }
        max1 = 0;
        int max2 = 0;
        int ret = -1;
        for (int i = 0; i < A.length; i++) {
            max1 = Integer.max(max1, A[i]);
            max2 = Integer.max(max2, Math.abs(max1 - max[i]));

        }

        return max2;

    }

}
