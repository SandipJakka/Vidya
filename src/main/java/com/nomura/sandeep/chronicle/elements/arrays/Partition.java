package com.nomura.sandeep.chronicle.elements.arrays;

import java.util.Arrays;

/**
 * Given an Array arr and an index i ... Partition the array such that
 * Elements less than A[i] are before A[i] and elements greater than A[i] are after
 * A[i]
 */
public class Partition {

    public static void main(String[] args) {
        int[] arr = new int[]{11, 7, 11, 45, 2, 11, 6, 4, 11, 21};
//        arr = partition(arr, 8);
//        System.out.println(Arrays.toString(arr));
        arr = partitionBetter(arr, 8);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] partition(int[] arr, int partitionIndex) {
        assert (arr.length > 0 && partitionIndex <= arr.length);
        int pivot = arr[partitionIndex];
        //put the pivot's at the last index....
        int numberOfPivots = 0;
        int j1 = arr.length - 1;
        for (int i = 0; i < arr.length - 1 - numberOfPivots; ++i) {
            if (arr[i] == pivot) {
                numberOfPivots++;
                arr = swap(arr, i, j1);
                do {
                    j1 = j1 - 1;
                } while (arr[j1] == pivot);
            }
        }
        //partition the array
        int j = -1;
        for (int i = 0; i < arr.length - 1 - numberOfPivots; ++i) {
            if (arr[i] <= pivot) {
                j++;
                arr = swap(arr, i, j);
            }
        }
        // swap the pivot back into it's place.
        for (int i = 1; i <= numberOfPivots; i++) {
            arr = swap(arr, j + i, arr.length - i);
        }
        return arr;
    }

    private static int[] swap(int[] arr, int i, int j) {
        assert (i > 0 && i < arr.length && j > 0 && j < arr.length);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    public static int[] partitionBetter(int[] arr, int pivotIndex) {
        int smaller = 0, equal = 0, larger = arr.length;
        int pivot = arr[pivotIndex];

        while (equal < larger) {
            if (arr[equal] < pivot) {
                arr = swap(arr, smaller++, equal++);
            } else if (arr[equal] == pivot) {
                ++equal;
            } else {
                arr = swap(arr, equal, --larger);
            }
        }
        return arr;
    }
}