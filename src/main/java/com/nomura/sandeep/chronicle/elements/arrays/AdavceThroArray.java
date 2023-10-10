package com.nomura.sandeep.chronicle.elements.arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdavceThroArray {

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 4, 1, 1, 0, 2, 3};
        int[] arr2 = new int[]{3, 2, 0, 0, 2, 0, 1};
        int[] arr3 = new int[]{3, 3, 1, 0, 2, 0, 1};

        assertTrue(canBeWon(arr1));
        assertTrue(canBeWon(arr3));
        assertFalse(canBeWon(arr2));
    }

    public static boolean canBeWon(int[] arr) {
        int lookingFor = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; --i) {
            if (arr[i] + i >= lookingFor) {
                lookingFor = i;
            }
        }
        return (lookingFor == 0);
    }

}
