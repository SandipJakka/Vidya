package com.nomura.sandeep.chronicle.elements.arrays;

import java.util.Arrays;

import static com.nomura.sandeep.chronicle.elements.arrays.EfficientGCD.gcd;

/**
 * Created by sandeep.jakka on 6/23/18.
 * <p>
 * {3,4,5,6}
 */
public class RotateArrays {
    static int[] rotateArr(int[] A, int rotateBy) {
        if (A == null || A.length == 0 || rotateBy < 0) {
            throw new RuntimeException("Something is off");
        }
        int len = A.length;
        if (rotateBy == 0 || rotateBy % len == 0) {
            return A;
        }
        int noOfCycles = gcd(len, rotateBy);
        int lenghtOfEachCycle = len / noOfCycles;

        for (int cycles = 0; cycles < noOfCycles; cycles++) {
            applyCycle(cycles, rotateBy, lenghtOfEachCycle, A);
        }
        return A;
    }

    private static void applyCycle(int offset, int rotateBy, int lenghtOfEachCycle, int[] A) {
        int temp = A[offset];
        int len = A.length;
        int temp2 = -1;
        System.out.printf("%d,", offset);
        for (int i = 1; i < lenghtOfEachCycle; i++) {
            int idx = (offset + (i * rotateBy)) % len;
            System.out.printf("%d,", idx);
            temp2 = A[idx];
            A[idx] = temp;
            temp = temp2;
        }
        System.out.printf("%n");
        A[offset] = temp;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(rotateArr(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 6)));
        System.out.println(Arrays.toString(rotateArr(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 21)));
        System.out.println(Arrays.toString(rotateArr(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 30)));
    }
}