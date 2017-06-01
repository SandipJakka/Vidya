package com.nomura.sandeep.chronicle.hackerrank.dynamic;

/**
 * Created by sandeep on 12/4/2016.
 */
public class FibonacciWithMemoization {

    public static void main(String[] args) {
        FibonacciWithMemoization fm = new FibonacciWithMemoization();
        int[] memo = new int[6];
        System.out.println("===>" + fm.fib(5, memo));
        System.out.println("===>" + fm.fib1(5));

        for (int i = 0; i < memo.length; i++) {
            System.out.print("  " + memo[i]);
        }
    }

    int fib(int n, int[] memo) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (memo[n] == 0) {
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        }
        return memo[n];
    }

    int fib1(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib1(n - 1) + fib1(n - 2);
        }
    }


}
