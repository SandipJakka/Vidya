package com.nomura.sandeep.chronicle.leet.blind.seventyfive;

/**
 * Given an integer array nums, find the
 * subarray
 *  with the largest sum, and return its sum.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Calculate the maxSum subarray at each element and keep on counting the same.
 *
 */

public class ID05MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int maxSum = nums[0];
        int maxSumTillCurrentElement = nums[0]; ;

        for (int i=1 ; i < nums.length; i++){
            maxSumTillCurrentElement = Math.max(maxSumTillCurrentElement + nums[i], nums[i]);
            maxSum = Math.max(maxSum,maxSumTillCurrentElement);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        ID05MaxSubArray maxSubArray = new ID05MaxSubArray();

        System.out.println(maxSubArray.maxSubArray(new int []{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray.maxSubArray(new int []{1}));
        System.out.println(maxSubArray.maxSubArray(new int []{5,4,-1,7,8}));
        System.out.println(maxSubArray.maxSubArray(new int []{-5,-4,-1,-7,-8}));
    }
}
