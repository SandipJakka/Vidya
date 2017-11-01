package com.nomura.sandeep.chronicle.elements.chapter1;

/**
 * Created by sandeep.jakka on 10/31/17.
 */
public class PalindromeNumber {


    public static void main(String[] args) {
        isPalindromeWithoutSwap(127721);
        isPalindromeWithoutSwap(12721);
        isPalindromeWithoutSwap(12);
    }

    /**
     * NumberOfDigitsInANumber = Floor( log10( number ) ) + 1
     * msd = 1/power(10 ^ (NumberOfDigitsInANumber -1))
     *
     * @param input
     * @return
     */

    public static boolean isPalindromeWithoutSwap(int input) {
        assert (input > 0);
        int number = input;
        int numberOfDigits = (int) Math.floor(Math.log10(number)) + 1;
        int mostSignificantDigitMask = (int) Math.pow(10, numberOfDigits - 1);
        for (int i = 0; i < numberOfDigits / 2; ++i) {
            // 1/10 ^ numberOfDigits -1 gives the most significant digit
            if (number / mostSignificantDigitMask != number % 10) {
                System.out.println(String.format("Number %d is NOT Palindrome", input));
                return false;
            }
            number = number % mostSignificantDigitMask; // remove Most Significant Digit
            number = number / 10; // remove Least Significant Digit
            mostSignificantDigitMask = mostSignificantDigitMask / 100;
        }
        System.out.println(String.format("Number %d is a  Palindrome", input));
        return true;
    }

    public static boolean isPalindromeBySwap(int number) {
        assert (number > 0);
        return (swap(number) == number);
    }


    public static int swap(int number) {
        assert (number > 0);
        int res = 0;
        while (number > 0) {
            res = res * 10 + number % 10;
            number = number / 10;
        }
        return res;
    }
}