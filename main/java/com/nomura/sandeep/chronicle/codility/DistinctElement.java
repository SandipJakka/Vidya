package com.nomura.sandeep.chronicle.codility;

import java.util.Arrays;

/**
 * Created by sandeep on 12/3/2016.
 */
public class DistinctElement {

    public static void main(String[] args) {
        int[] A = new int[]{2, 1, 1, 2, 3, 1};
        int[] A1 = new int[]{0, 0, 0, 0, 0, 0};
        int[] A2 = new int[]{0};
        DistinctElement d = new DistinctElement();
        System.out.println("==> " + d.solution(A));
        System.out.println("==> " + d.solution(A1));
        System.out.println("==> " + d.solution(A2));
    }

    public int solution(int[] A) {
        int count = 1;
        if (A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return 1;
        }
        Arrays.parallelSort(A);
        for (int i = 0; i < A.length-1; i++) {
            if (A[i] != A[i + 1]) {
                count += 1;
            }
        }
        return count;
    }

}
