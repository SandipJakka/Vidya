package com.nomura.sandeep.chronicle.leet.blind.seventyfive;

import java.util.Arrays;

/**
 * 238. Product of Array Except Self
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 * <p>
 * Accepted
 * 1.9M
 * Submissions
 * 3M
 * <p>
 * Concept : Prefix array and suffix array -- and re-using the answer array.
 */
public class ID04ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int product = 1;
        int[] answer = new int[nums.length];
        // build the suffix prodcuts
        for (int idx = nums.length - 1; idx > 0; idx--) {
            product = product * nums[idx];
            answer[idx] = product;
        }
        //reset
        int prefix = 1;
        for (int idx = 0; idx < nums.length - 1; idx++) {
            answer[idx] = prefix * answer[idx + 1];
            prefix = prefix * nums[idx];
        }
        // last value is set from prefix product.
        answer[nums.length - 1] = prefix;
        return answer;
    }

    public static void main(String[] args) {
        ID04ProductExceptSelf p = new ID04ProductExceptSelf();
        print(p.productExceptSelf(new int[]{1, 2, 3, 4}));
        print(p.productExceptSelf(new int[]{-1, 1, 0, -3, 3}));
    }

    public static void print(int[] arr) {
        String s = Arrays.toString(arr);
        System.out.println(s);
    }
}
