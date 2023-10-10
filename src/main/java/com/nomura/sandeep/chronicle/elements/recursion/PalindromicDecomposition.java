package com.nomura.sandeep.chronicle.elements.recursion;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PalindromicDecomposition {

    public void solution(String input) {
        List<String> results = Lists.newArrayList();
        Stack<String> stack = new Stack<>();
        helper(input, 0, stack, results);

        results.forEach(el -> System.out.println(el));
    }

    private void helper(String input, int offset, Stack<String> stack, List<String> results) {
        if (offset == input.length()) {

            String palindrom = stack.stream().collect(Collectors.joining());
            results.add(palindrom);
            return;
        }
        for (int i = offset + 1; i <= input.length(); i++) {
            //  System.out.println(String.format("%d,%d", i, offset));
//            System.out.println((i - offset));?

            String prefix = input.substring(offset, offset + ((i - offset) < 0 ? 0 : (i - offset)));
            System.out.println("prefix :" + prefix);
            if (isPalindrome(prefix)) {
                stack.push(prefix);
                helper(input, i, stack, results);
                stack.pop();
            }
        }

    }

    private boolean isPalindrome(String prefix) {

        for (int i = 0, j = prefix.length() - 1; i < j; i++) {
            if (prefix.charAt(i) != prefix.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    void get(String string) {
        print(string, "", 0);
    }

    void print(String s, String ans, int i) {
        int n = s.length();
        if (i == n) {
            System.out.println(ans);
            return;
        }
        for (int j = i; j < n; j++) {
            boolean a = palin(s, i, j);
            if (a) {
                print(s, ans + " " + s.substring(i, i + j - i + 1), j + 1);
            }
        }
    }

    boolean palin(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }


    public static void main(String[] args) {
        PalindromicDecomposition decomposition = new PalindromicDecomposition();
//        decomposition.solution("0204451881");

        decomposition.get("0204451881");
    }

}
