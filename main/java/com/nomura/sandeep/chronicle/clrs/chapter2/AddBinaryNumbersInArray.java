package com.nomura.sandeep.chronicle.clrs.chapter2;

/**
 * Created by sandeep on 12/10/2016.
 */
public class AddBinaryNumbersInArray {

    public static void main(String[] args) {
        int[] A = new int[]{1, 0, 1, 1, 0};
        int[] B = new int[]{1, 0, 1, 1, 1};
        AddBinaryNumbersInArray a = new AddBinaryNumbersInArray();
        int[] C = a.solution(A, B);

        for (int i = 0; i < C.length; i++) {
            System.out.print(" " + C[i]);
        }
    }


    public int[] solution(int[] A, int[] B) {
        int[] C = new int[A.length + 1];
        int carry = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            C[i + 1] = (A[i] + B[i] + carry) % 2;
            carry = (A[i] + B[i] + carry) / 2;
        }
        C[0] = carry;
        return C;
    }
}
