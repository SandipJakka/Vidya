package com.nomura.sandeep.chronicle.elements.arrays;

import java.util.Arrays;
import java.util.Stack;

import static junit.framework.Assert.assertEquals;

/**
 * Increment (1,2,9) by adding 1
 */
public class Increment {

    public static void main(String[] args) {
        int[] in = new int[]{1, 2, 9, 9};
        in = increment(in);

        System.out.println(Arrays.toString(in));

        assertEquals(Integer.toBinaryString(13), addTwoBinRepresentationOfNumbers(5, 8));

    }


    public static int[] increment(int[] in) {
        ++in[in.length - 1];
        for (int i = in.length - 1; i > 0 && in[i] == 10; --i) {
            in[i] = 0;
            ++in[i - 1];
        }
        if (in[0] == 10) {
            int[] newArray = new int[in.length + 1];
            newArray[0] = 1;
            newArray[0] = 0;
            for (int i = 1; i < in.length; ++i) {
                newArray[i + 1] = in[i];
            }
            return newArray;
        }
        return in;
    }

    public static String addTwoBinRepresentationOfNumbers(int number1, int number2) {
        Stack<Character> sum = new Stack<>();
        String string1 = Integer.toBinaryString(number1);
        String string2 = Integer.toBinaryString(number2);

        System.out.println(String.format("Number1 = %s, Number2 = %s", string1, string2));

        int max = Math.max(string1.length(), string2.length());
        char prevCarry = '0';
        for (int i = max ; i > 0; --i) {
            for (int j = (Math.min(string1.length(), string2.length())); j > 0; --j) {
                Result result = sum(prevCarry, charAtPositionOrDefaultValue(string1, i));
                Result result1 = sum(result.carry, charAtPositionOrDefaultValue(string2, j));
                prevCarry = result1.carry;
                sum.push(result1.sum);
            }
        }
        StringBuilder buffer = new StringBuilder(sum.capacity());
        while (!sum.isEmpty()) {
            buffer.append(sum.pop());
        }
        System.out.println("Out = " + buffer.toString());
        return buffer.toString();
    }

    private static char charAtPositionOrDefaultValue(String string, int i) {
        return i < string.length() ? string.charAt(i) : '0';
    }

    private static Result sum(char one, char two) {
        if ("1".equals(one) && "1".equals(two)) {
            return new Result('0', '1');
        } else if ("0".equals(one) && "0".equals(two)) {
            return new Result('0', '0');
        } else {
            return new Result('1', '0');
        }
    }

    static class Result {
        public final char sum;
        public final char carry;

        Result(char sum, char carry) {
            this.sum = sum;
            this.carry = carry;
        }
    }

}
