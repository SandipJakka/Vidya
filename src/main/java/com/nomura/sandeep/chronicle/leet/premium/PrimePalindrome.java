package com.nomura.sandeep.chronicle.leet.premium;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Given an integer n, return the smallest prime palindrome greater than or equal to n.
 * <p>
 * An integer is prime if it has exactly two divisors: 1 and itself. Note that 1 is not a prime number.
 * <p>
 * For example, 2, 3, 5, 7, 11, and 13 are all primes.
 * An integer is a palindrome if it reads the same from left to right as it does from right to left.
 * <p>
 * For example, 101 and 12321 are palindromes.
 * The test cases are generated so that the answer always exists and is in the range [2, 2 * 108].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 6
 * Output: 7
 * Example 2:
 * <p>
 * Input: n = 8
 * Output: 11
 * Example 3:
 * <p>
 * Input: n = 13
 * Output: 101
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 108
 */
public class PrimePalindrome {
    public int primePalindrome(int n) {

/*
        for (int i = n; i <= 10_000_000_00; i++) {
            if (isPrime(i) && isPalindrome(i)) {
                return i;
            }
        }
        return -1;*/

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
        int each = (100_000_000 - n) / 8;
        int from = n, to = each;
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            futures.add(threadPoolExecutor.submit(new Worker(from, to)));
            from = to + 1;
            to = to + each;
        }
        int res = Integer.MAX_VALUE;
        for (Future<Integer> integerFuture : futures){
            try {
                int min = integerFuture.get();
                if ( min > -1){
                    res = Math.min(min, res);
                    return res;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return  res;
    }

    class Worker implements Callable<Integer> {

        private final int from;
        private final int to;

        Worker(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Integer call() {
            for (int i = from; i <= to; i++) {
                if (isPrime(i) && isPalindrome(i)) {
                    return i;
                }
            }
            return -1;
        }
    }

    private static boolean isPrime(int number) {
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime(100);
    }

    private static boolean isPalindrome(int number) {
        return (number == reverse(number));
    }

    private static int reverse(int number) {
        int decimals = 0;
        int len = String.valueOf(number).length();
        int[] digits = new int[len];

        while (number > 0) {
            int rem = number % 10;
            digits[decimals] = rem;
            number = number / 10;
            decimals++;
        }

        int ret = 0;
        for (int n : digits) {
            ret = ret + n * (int) Math.pow(10, decimals - 1);
            decimals = decimals - 1;
        }

        return ret;
    }

    public static void main(String[] args) {
        // System.out.println(reverse(43));
        PrimePalindrome p = new PrimePalindrome();
        System.out.println(p.primePalindrome(9989900));
    }
}
