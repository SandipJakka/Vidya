package com.nomura.sandeep.chronicle.clrs.chapter2;

/**
 * Created by sandeep on 12/10/2016.
 */
public class InsertionSortReverse {
    public static void main(String[] args) {
        int[] A = new int[]{31, 41, 59, 26, 41, 58};
        InsertionSortReverse i = new InsertionSortReverse();
        A = i.solution(A);
        for (int j = 0; j < A.length; j++) {
            System.out.print(" " + A[j]);
        }
    }

    public int[] solution(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] < key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }

        return A;
    }
}
