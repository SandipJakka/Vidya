package com.nomura.sandeep.chronicle.leet.blind.seventyfive;

import java.util.HashSet;
import java.util.Set;

public class ID03ContainsDuplicate {
    /**
     * 217. Contains Duplicate
     *
     * Input: nums = [1,2,3,1]
     * Output: true
     *
     * <p>
     * Example 2:
     * Input: nums = [1,2,3,4]
     * Output: false
     * @param nums
     * @return
     *
     *
     * Concept : Set
     * Or
     * Sort and see if two neighboring elements are the same.
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int number : nums) {
            if (set.contains(number)) {
                return true;
            } else {
                set.add(number);
            }
        }

        return false;
    }
}