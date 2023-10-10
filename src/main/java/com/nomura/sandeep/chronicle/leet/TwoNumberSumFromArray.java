package com.nomura.sandeep.chronicle.leet;

import java.util.Arrays;

/**
 * Created by sandeep.jakka on 10/16/18.
 */
public class TwoNumberSumFromArray {

    public int[] twoSum(int[] nums, int target) {
        int[] diffs = diffs(nums, target);
        boolean found = false;
        int one = -1, two = -1;
        for (int index = 0; !found && index < diffs.length; index++) {
            one = index;
            two = search(nums, diffs[index], index);
            if (two != -1) {
                found = true;
            }
        }
        if (found) {
            return new int[]{one, two};
        } else {
            return new int[]{};
        }
    }

    private static int[] diffs(int[] nums, int target) {
        int[] diffs = new int[nums.length];
        for (int index = 0; index < nums.length; index++) {
            diffs[index] = target - nums[index];
        }
        return diffs;
    }

    private static int search(int[] array, int searchKey, int startIdx) {
        for (int i = startIdx + 1; i < array.length; i++) {
            if (array[i] == searchKey) {
                return i;
            }
        }
        return -1;
    }

    private static int binarySearch(int[] sortedArray, int searchKey, int lowIdx, int hightIdx) {
        if (lowIdx <= hightIdx) {
            int midIdx = (lowIdx + hightIdx) >>> 1;
            if (sortedArray[midIdx] == searchKey) {
                return midIdx;
            } else if (searchKey < sortedArray[midIdx]) {
                return binarySearch(sortedArray, searchKey, lowIdx, midIdx - 1);
            } else {
                return binarySearch(sortedArray, searchKey, midIdx + 1, hightIdx);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        TwoNumberSumFromArray t = new TwoNumberSumFromArray();
        int[] nums = new int[]{2, 7, 11, 15};

        System.out.printf("%s %n", Arrays.toString(t.twoSum(nums, 9)));
        System.out.printf("%s %n", Arrays.toString(t.twoSum(nums, 18)));
        System.out.printf("%s %n", Arrays.toString(t.twoSum(nums, 17)));


        int[] nums2 = new int[]{3, 2, 4};
        System.out.printf("%s %n", Arrays.toString(t.twoSum(nums2, 6)));
        System.out.printf("%s %n", Arrays.toString(t.twoSum(new int[]{3, 3}, 6)));
    }
}
