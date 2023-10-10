package com.nomura.sandeep.chronicle.elements.arrays;

import java.util.Arrays;
import java.util.Random;

public class BiggestNMinusOneProduct {

    public static long findBiggestNMinusOneProduct(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int minNegativeNumberIdx = -1, maxNegativeNumberIdx = -1, minNonNegativeIdx = -1, zeroIdx = -1;
        int numberOfNegativeNumbers = 0, numberOfZeros = 0;
        long product = 1;
        for (int index = 0; index < A.length; index++) {
            if (A[index] < 0) {
                numberOfNegativeNumbers++;
                if (minNegativeNumberIdx == -1 || A[index] < A[minNegativeNumberIdx]) {
                    minNegativeNumberIdx = index;
                }
                if (maxNegativeNumberIdx == -1 || A[index] > A[maxNegativeNumberIdx]) {
                    maxNegativeNumberIdx = index;
                }
            } else if (A[index] > 0) {
                if (minNonNegativeIdx == -1 || A[index] < A[minNonNegativeIdx]) {
                    minNonNegativeIdx = index;
                }
            } else {
                numberOfZeros++;
                zeroIdx = index;
            }
        }

        int skipIdx = -1;

        if (numberOfZeros == 1 && (numberOfNegativeNumbers % 2) == 0) {
            skipIdx = zeroIdx;
        } else {
            skipIdx = (numberOfNegativeNumbers % 2 == 0) ?
                    (minNonNegativeIdx != -1) ? minNonNegativeIdx : maxNegativeNumberIdx :       // ignore the min non negative index ( since we can't ignore any negatives )
                    maxNegativeNumberIdx;
        }

        System.out.printf("Ignoring indx = %d %n", skipIdx);
        for (int i = 0; i < A.length; i++) {
            if (skipIdx == i) {
                continue;
            }
            product = product * A[i];
        }
        System.out.printf("Product :%d for array : %s %n", product, Arrays.toString(A));
        return product;
    }

    public static void main(String[] args) {
        findBiggestNMinusOneProduct(new int[]{3, 2, -1, 6});
        findBiggestNMinusOneProduct(new int[]{3, 2, -1, -6, 0});
        findBiggestNMinusOneProduct(generateArray(4, -3, 9));
        findBiggestNMinusOneProduct(generateArray(4, 1, 10));
        findBiggestNMinusOneProduct(generateArray(4, -10, -1));
    }

    static int[] generateArray(int number, int start, int end) {
        Random r = new Random();
        return r.ints(number, start, end).toArray();
    }
}
