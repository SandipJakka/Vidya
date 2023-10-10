package com.nomura.sandeep.chronicle.leet.blind.seventyfive;

/**
 * Given an integer array nums, find a
 * subarray
 *  that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * This is a type of Kadane's algorithm.
 */
public class ID06MaxSubArrayProduct {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int answer = nums[0] ;

        int minProductSoFar = 1 ;
        int maxProductSoFar = 1;
        int tmp =1 ;

        for (int num : nums) {
            if (num == 0) {
                //reset if we find 0
                minProductSoFar = 1;
                maxProductSoFar = 1;
                answer = Math.max(answer, num);
                continue;
            }
            // since minProductSoFar is overwritten below, we need its needs for max calculation.
            tmp = minProductSoFar;
            //we need both min and max as neg * nega = +ve , so min is also required.
            minProductSoFar = Math.min(num, Math.min(num * maxProductSoFar, num * minProductSoFar));
            maxProductSoFar = Math.max(num, Math.max(num * maxProductSoFar, num * tmp));
            answer = Math.max(answer, maxProductSoFar);
        }
        return answer;
    }
}
