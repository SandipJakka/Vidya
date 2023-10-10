package com.nomura.sandeep.chronicle.leet.premium;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 */
public class TrapWater {

    public static void main(String[] args) {
        trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        trap(new int[]{4, 2, 0, 3, 2, 5});
        trap(new int[]{4, 2, 3});
    }


    public static int trap(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int start = 0;
        int next;
        int water = 0;

        while (start < height.length - 1) {
            next = indexOfNextHighest(height, start);
            int minOfStartAndNext = Math.min(height[start], height[next]);
            for (int i = start + 1; i < next; i++) {
                water += minOfStartAndNext - height[i];
            }
            start = next;
        }
        System.out.println(String.format("water = %d", water));
        return water;
    }

    private static int indexOfNextHighest(int[] height, int start) {
        int startVal = height[start];
        int maxTillNow = Integer.MIN_VALUE;
        int indexOfMaxTillNow = -1;
        for (int index = start + 1; index < height.length; index++) {
            if (height[index] > maxTillNow) {
                maxTillNow = height[index];
                indexOfMaxTillNow = index;
            }
            if (height[index] >= startVal) {
                return index;
            }
        }
        if (indexOfMaxTillNow != -1) {
            return indexOfMaxTillNow;
        }
        return start + 1;
    }
}
