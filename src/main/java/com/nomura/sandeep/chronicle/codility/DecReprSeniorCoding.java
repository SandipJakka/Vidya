package com.nomura.sandeep.chronicle.codility;

import java.util.ArrayList;
import java.util.List;

/**
 * Two non-negative integers are called siblings if they can be obtained from each other by
 * rearranging the digits of their decimal representations. For example, 123 and 213 are siblings. 535 and 355
 * are also siblings.
 * <p>
 * A set consisting of a non-negative integer N and all of its siblings is called the family of N.
 * For example, the family of 553 comprises three numbers: 355, 535 and 553.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N); }
 * <p>
 * that, given a non-negative integer N, returns the largest number in the family of N.
 * <p>
 * For example, given N = 213 the function should return 321. Given N = 553 the function should return 553.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [0..10,000].
 * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */
public class DecReprSeniorCoding {

    public int solution(int N) {
        List<Integer> digits = getDigits(N);
        digits.sort(Integer::compareTo);

        int max = 0;
        int multiplier = 1;
        for (int digit : digits) {
            max = max + digit * multiplier;
            multiplier *= 10;
        }
        return max;
    }

    private List<Integer> getDigits(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            digits.add(d);
        }
        return digits;
    }

    public static void main(String[] args) {
        DecReprSeniorCoding d = new DecReprSeniorCoding();

        System.out.println(String.format("%d ---> %d ", 123, d.solution(123)));
        System.out.println(String.format("%d ---> %d ", 213, d.solution(123)));
        System.out.println(String.format("%d ---> %d ", 553, d.solution(553)));
        System.out.println(String.format("%d ---> %d ", 1234, d.solution(1234)));
        System.out.println(String.format("%d ---> %d ", 130, d.solution(130)));
    }

}
