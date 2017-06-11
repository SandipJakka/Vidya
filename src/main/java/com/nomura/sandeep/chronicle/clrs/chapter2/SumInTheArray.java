package com.nomura.sandeep.chronicle.clrs.chapter2;

import java.util.Arrays;

/**
 * Describe and O(nlgn) alogorithm that given a Set S and an int X, identifies whether
 * S[i] + S[j] = X
 * return 1 if present else 0.
 * <p>
 * n lg n + n * lgn = O ( n log n )
 */
public class SumInTheArray {
    public static void main(String[] args) {
        int[] A = new int[]{31, 41, 59, 26, 41, 58};
        SumInTheArray s = new SumInTheArray();

        System.out.println("===> " + s.solution(A, 117));
        System.out.println("===> " + s.solution(A, 119));
        System.out.println("===> " + s.solution(A, 82));
        System.out.println("===> " + s.solution(A, 31));
    }

    // O( n log n ) and Space = O(1)
    public int solution(int[] A, int X) {
        int present = 0;
        Arrays.parallelSort(A);  //n lg n
        for (int i = 0; i < A.length; i++) {
            int b = (X - A[i]);
            if (Arrays.binarySearch(A, b) > 0) {  //lg n
                return 1;
            }

        }
        return present;
    }


   /* public int solution1(int[] A, int X) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            s.add()
        }
*/
//    }

}
