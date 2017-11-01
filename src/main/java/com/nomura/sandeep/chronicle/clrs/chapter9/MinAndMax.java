package com.nomura.sandeep.chronicle.clrs.chapter9;

/**
 * Find both Min and Max at the same time.
 */
public class MinAndMax {

    public static void main(String[] args) {
        int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        MinAndMax m = new MinAndMax();
        m.worstCase(A);
        m.betterCase(A);
    }

    public void worstCase(int[] arr) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int noOfComparision = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
            noOfComparision += 2;
        }
        System.out.printf("Worst case : Max : %d , Min : %d , No of comparisions : %d", max, min, noOfComparision);
    }


    public void betterCase(int[] arr) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int noOfComparision = 0;
        for (int i = 0; i < arr.length; i = i + 2) {
            int one = arr[i];
            int two = Integer.MIN_VALUE;
            int local_min = -1, local_max = -1;
            if (i < arr.length - 1) {
                two = arr[i + 1];
            }
            noOfComparision++;
            if (two == Integer.MIN_VALUE) {
                local_min = one;
                local_max = one;
            } else if (one > two) {
                local_max = one;
                local_min = two;
            } else {
                local_max = two;
                local_min = one;
            }
            noOfComparision++;
            if (local_max > max) {
                max = local_max;
            }
            noOfComparision++;
            if (local_min < min) {
                min = local_min;
            }
        }
        System.out.println("");
        System.out.printf("Better one ::Max : %d , Min : %d , No of comparisions : %d", max, min, noOfComparision);
    }
}
