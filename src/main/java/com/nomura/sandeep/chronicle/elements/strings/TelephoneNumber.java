package com.nomura.sandeep.chronicle.elements.strings;

import java.util.Stack;

public class TelephoneNumber {

    String[] map = new String[]{"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public void decode(String number) {
        int digits = 0;
        Stack<String> stack = new Stack<>();
        helper(number, digits, stack);
    }

    private void helper(String number, int digit, Stack<String> stack) {
//        System.out.println(String.format("%s,%d", number, digit));
        if (number.length() == stack.size()) {
            System.out.println(stack);
            return;
        }
        for (char c : map[number.charAt(digit) - '0'].toCharArray()) {
            stack.push(String.valueOf(c));
            helper(number, digit + 1, stack);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        TelephoneNumber t = new TelephoneNumber();
        t.decode("79");
        t.decode("949958298");
    }

}