package com.nomura.sandeep.chronicle.hackerrank.mktx;

import java.util.*;
import java.util.function.Function;

/**
 * Created by sandeep.jakka on 4/20/20.
 * <p>
 * Parentheses strings are strings containing only the characters '(' and ')'.
 * A parentheses string is considered balanced when its opening parentheses align with its closing parentheses.
 * For example, "()()" and "(()())" are balanced parentheses strings while ")(", "())(" are not.
 * Given a string consisting of the same number of opening and closing parentheses, determine the minimum number of character swaps
 * required to make the string a balanced parentheses string.
 * <p>
 * Example:
 * s = "))(("
 * Swap the first and the last characters to get "()()", a balanced parentheses string. The minimum number of swaps is 1.
 * <p>
 * Function Description
 * <p>
 * Complete the function minSwaps in the editor below.
 * <p>
 * minSwaps has the following parameter(s):
 * string s: a string of parentheses
 * <p>
 * Returns:
 * int: the minimum number of swaps required to make s a balanced parentheses string.
 * <p>
 * Constraints
 * <p>
 * 2 < length of s < 2*105
 * <p>
 * The length of s is even
 * <p>
 * s contains only the characters '(' and ')'
 * <p>
 * s contains the same number of '(' and ')' characters
 */
public class MinSwapsParenthesis {
    /**
     * Complete the 'minSwaps' function below.
     * <p>
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     * <p>
     * We calculate a balance of each prefix. When the balance becomes negative (-1) we swap the current ')' with the rightmost '('.
     * Of course, overall balance will remain zero as there is an equal number of opening and closing brackets.
     * Complexity : O(n) where n is the order of the length of the string.
     */
    static int minSwaps(String s) {
        if (Objects.nonNull(s) && "".equals(s)) {
            return 0;
        }
        int result = 0;
        Stack<Integer> stack = new Stack<>();

        char[] parens = s.toCharArray();

        for (int i = 0; i < parens.length; i++) {
            if (parens[i] == '(') {
                stack.push(i);
            }
        }
        int open = 0;
        for (int i = 0; i < parens.length; i++) {
            if (parens[i] == '(') {
                open = open + 1;
            } else {
                open = open - 1;
            }
            if (open < 0) {  // we have hit the imbalance and i =')'
                //swap the first imbalance with the last
                int position = stack.pop();
                parens[position] = ')';
                parens[i] = '(';
                open = open + 2; //since the swap has happened.
                ++result;
            }
        }
        return result;
    }


    /*
     * Complete the 'maxEvents' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arrival
     *  2. INTEGER_ARRAY duration
     *
     *  n = 5

        arrival = [1, 3, 3, 5, 7]
        duration = [2, 2, 1, 2, 1]
     */

    public static int maxEvents(List<Integer> arrival, List<Integer> duration) {
        if (Objects.isNull(arrival) || Objects.isNull(duration) ||( arrival.size()!=duration.size())){
            return 0;
        }

        return 0;
    }

    public static int segment(int x, List<Integer> space) {
        if (Objects.isNull(space) || space.isEmpty()){
            return 0;
        }

        if (x == space.size()){
            return space.stream()
                    .reduce(Integer::max).get();

        }
        if (x > space.size()){
            throw new RuntimeException("Wrong X");
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < x; i++){
            Integer val = space.get(i);
//            if (val > max){
//                max = val;
//            }
            if (val < min){
                min = val;
            }
        }

        System.out.printf("min = {%d}, max = {%d} \n", min,max);

        max = min;

        for (int i=x; i < space.size() ; i++){
            min = Math.min(min,space.get(i));
            max = Math.max(max,min);
        }

        return max;
    }

    public static void main(String[] args) {
//        System.out.printf("number of swaps = {%d} \n", MinSwapsParenthesis.minSwaps("))(("));
//        System.out.printf("number of swaps = {%d} \n", MinSwapsParenthesis.minSwaps("())("));



        System.out.printf("max = {%d} \n", MinSwapsParenthesis.segment(3, Arrays.asList(2,5,4,6,8)));
    }
}
