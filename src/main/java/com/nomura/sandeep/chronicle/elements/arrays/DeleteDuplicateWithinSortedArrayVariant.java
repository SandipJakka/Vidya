package com.nomura.sandeep.chronicle.elements.arrays;


import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Input : Sorted Array and  an integer m ,
 * Task: Update the Array A so that if 'x' appears m times in the Array A,
 * then it should appear exactly min(2,m) times in the Array A
 */
public class DeleteDuplicateWithinSortedArrayVariant {

    public static void main(String[] args) {
        assertTrue(Arrays.equals(new int[]{2, 3, 5, 5, 7, 11, 11, 13, 0}, deleteDuplicate(new int[]{2, 3, 5, 5, 7, 11, 11, 11, 13}, 3)));
        assertTrue(Arrays.equals(new int[]{2, 3, 5, 5, 7, 11, 11, 13, 0}, deleteDuplicate(new int[]{2, 3, 5, 5, 7, 11, 11, 11, 13}, 2)));
        assertTrue(Arrays.equals(new int[]{2, 3, 5, 5, 7, 11, 11, 13, 0, 0}, deleteDuplicate(new int[]{2, 3, 5, 5, 5, 7, 11, 11, 11, 13}, 3)));
        assertTrue(Arrays.equals(new int[]{2, 3, 5, 7, 11, 13, 0, 0, 0}, deleteDuplicate(new int[]{2, 3, 5, 5, 7, 11, 11, 11, 13}, 1)));
    }


    @NotNull
    public static int[] deleteDuplicate(@NotNull int[] arr, int m) {
        int write_indx = Math.min(2, m);
        for (int i = Math.min(2, m), j = 0; i < arr.length; ++i, ++j) {
            if (!allInRangeAreEqual(j, i, arr)) {
                arr[write_indx++] = arr[i];
            }
        }
        for (int i = (arr.length - 1); i > (write_indx - 1); --i) {
            arr[i] = 0;
        }
        return arr;
    }

    private static boolean allInRangeAreEqual(int start, int end, int[] arr) {
        for (int i = end; i > start; --i) {
            if (arr[i] != arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}