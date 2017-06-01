package com.nomura.sandeep.chronicle.codility;

import java.util.Arrays;

/**
 * Created by sandeep on 12/8/2016.
 */
public class MaxDiscIntersection {

    public static void main(String[] args) {
        int[] A = new int[]{1, 5, 2, 1, 4, 0};
        int[] A1 = new int[]{1, 2147483647, 0};
        MaxDiscIntersection m = new MaxDiscIntersection();
//        System.out.println("===> " + m.solution(A));
        System.out.println("===> " + m.solution(A1));
    }

    public int solution(int[] A) {
        int sum = 0;
        int n = A.length;
        double[] start = new double[n];
        double[] end = new double[n];
        for (int i = 0; i < n; i++) {
            start[i] = i - A[i];
            end[i] = A[i] + i;
        }
        Arrays.parallelSort(start);

        for (int i = 0; i < n; i++) {
            double e = end[i];
            int index = Arrays.binarySearch(start, e + 0.5);
            int increment = -(index + 1);
            sum += (increment - i - 1);
            if (sum > 10E6) {
                return -1;
            }
        }
        return sum;
    }
}
