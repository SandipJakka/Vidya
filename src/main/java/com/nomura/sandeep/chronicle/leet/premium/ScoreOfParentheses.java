package com.nomura.sandeep.chronicle.leet.premium;

import java.util.Stack;

/***
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: 1
 * Example 2:
 *
 * Input: s = "(())"
 * Output: 2
 * Example 3:
 *
 * Input: s = "()()"
 * Output: 2
 * Example 4:
 *
 * Input: s = "(()(()))"
 * Output: 6
 */
public class ScoreOfParentheses {

    public static void main(String[] args) {
        scoreOfParentheses("(()(()))");
    }

    public static int scoreOfParentheses(String s) {
        if ("".equals(s.trim())) {
            return 0;
        }
        if (s.length() == 2) {
            return 1;
        }
        if (s.length() == 4) {
            return 2;
        }
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        /*boolean previousOpen = true;
        int groupCounter = 0;
        char[] chars = s.toCharArray();
        for (int index = 0; index < chars.length - 1; index++) {
            char c = chars[index];
            if ('(' == c) {
                if (!previousOpen) {
                    count += groupCounter * 2;
                }
                stack.push(c);
                groupCounter = 1;
                previousOpen = true;
            } else {
                //if (previousOpen) {
                groupCounter = groupCounter + 1;
                //}
                stack.pop();
                previousOpen = false;
            }
        }
        count = groupCounter + count;
        int result = stack.isEmpty() ? count : (count * 2);
        System.out.println(String.format("count = %d", result));
        return result;*/


        for (char c : s.toCharArray()) {
            if ('(' == c) {
                stack.push(count);

                count = 0;
            } else {
                count = stack.pop() + Math.max(count * 2, 1);

            }
        }
        System.out.println(String.format("count = %d", count));
        return count;
    }
}
