package com.nomura.sandeep.chronicle.leet.premium;


import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * Constraints:
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces
 */

public class MaxSubStringWithoutRepititon {
    public static void main(String[] args) {
        System.out.println(MaxSubStringWithoutRepititon.lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        // Use a HashMap<Character, Integer> if dup is allowed.
        // Limit the size of Set if at most k distinct char allowed.
        Set<Character> memory = new HashSet<>();
        int result = 0;
        // p1 is a pioneer pointer. So p1 <= length.
        for (int p1 = 1, p2 = 0; p1 <= arr.length; p1++) {
            char cur = arr[p1 - 1];
            while (memory.contains(cur)) {
                memory.remove(arr[p2]);
                p2++;
            }
            memory.add(cur);
            result = Math.max(result, p1 - p2);
            System.out.println("-----------");
            System.out.println(memory);
        }
        return result;

    }
}
