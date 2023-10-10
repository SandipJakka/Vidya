package com.nomura.sandeep.chronicle.elements.chapter1;

import static junit.framework.Assert.assertEquals;

public class ClosestIntWithSameBitCount {

    public static void main(String[] args) {
        assertEquals(5, closestIntWithSameBitCount(6));
        assertEquals(90, closestIntWithSameBitCount(92));
        assertEquals(87, closestIntWithSameBitCount(79));
        assertEquals(11, closestIntWithSameBitCount(7));
        assertEquals(2147483646, closestIntWithSameBitCount(Integer.MAX_VALUE - 2));
    }


    private static final int NO_OF_BITS_IN_A_INT = 32;

    public static int closestIntWithSameBitCount(int number) {
        assert (number > 0);
        int ret = -1;
        for (int i = 0; i < NO_OF_BITS_IN_A_INT - 1; ++i) {
            int mask1 = (number >>> i) & 1; // move ith bit to the right AND 1 = ith bit
            int mask2 = (number >>> (i + 1)) & 1; // Move i + 1 th bit AND 1 = i+1 bit
            if (mask1 != mask2) {
                ret = swap(number, i, i + 1);
                break;
            }
        }
        System.out.println(String.format("Input = %s, Out = %s ", Integer.toBinaryString(number), Integer.toBinaryString(ret)));
        return ret;
    }

    private static int swap(int number, int i, int j) {
        assert (j > i && number > 0);
        return number ^ (1 << i | 1 << j);
    }
}
