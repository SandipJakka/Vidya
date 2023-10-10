package com.nomura.sandeep.chronicle.codility;

/**
 * Consider a non-empty string S = S[0]S[1]...S[Q-1] consisting of Q characters.
 * The period of this string is the smallest positive integer P such that:
 * <p>
 * P ≤ Q / 2 and
 * S[K] = S[K+P] for every K, where 0 ≤ K < Q − P.
 * For example, 8 is the period of "codilitycodilityco" and 7 is the period of "abracadabracadabra".
 * <p>
 * A positive integer M is the binary period of a positive integer N if M is the period of the binary representation of N.
 * <p>
 * For example, 4 is the binary period of 955, because the binary representation of 955 is "1110111011" and its period is 4.
 * <p>
 * You are given an implementation of a function:
 * <p>
 * class Solution { public int solution(int N); }
 * <p>
 * This function, when given a positive integer N, returns the binary period of N. The function returns −1
 * if N does not have a binary period.
 * <p>
 * For example, given N = 955 the function returns 4, as explained in the example above.
 * <p>
 * The attached code is still incorrect on some inputs. Despite the error(s),
 * the code may produce a correct answer for the example test cases. The goal of the exercise is to find and fix the bug(s) in the implementation.
 * You can modify at most two lines.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..1,000,000,000].
 * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */
public class BugfixingBinaryPeriod {

    public int solution(int N) {
        char[] arr = Integer.toBinaryString(N).toCharArray();
        return period(arr, arr.length / 2);
    }


    private int period(char[] arr, int startIndex) {
        if (startIndex == 0) {
            return -1;
        }
        if (check(arr, 0, startIndex)) {
            return startIndex;
        } else {
            return period(arr, startIndex - 1);
        }
    }

    private boolean check(char[] arr, int startIndexFirst, int startIndexSecond) {
        for (int i = startIndexFirst, j = startIndexSecond; i < startIndexSecond; i++, j++) {
            if (arr[i] != arr[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BugfixingBinaryPeriod b = new BugfixingBinaryPeriod();
        System.out.println(String.format("%d , %s ===> %d", 955, Integer.toBinaryString(955), b.solution(955)));
        System.out.println(String.format("%d , %s ===> %d", 755, Integer.toBinaryString(755), b.solution(755)));

        for (int i = 100; i < 1000; i++) {
            int solution = b.solution(i);
            if (solution > -1) {
                System.out.println(String.format("%d , %s ===> %d", i, Integer.toBinaryString(i), solution));
            }
        }
    }
}
