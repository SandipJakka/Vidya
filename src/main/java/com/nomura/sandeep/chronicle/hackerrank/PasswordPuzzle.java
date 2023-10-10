package com.nomura.sandeep.chronicle.hackerrank;

import java.util.*;

public class PasswordPuzzle {

    static class Problem {
        final String[] passwords;
        final String loginAttempt;
        final Map<String, String> map;

        Problem(String[] passwords, String loginAttempt) {
            this.passwords = passwords;
            this.loginAttempt = loginAttempt;
            map = new HashMap<>();
            Arrays.stream(passwords).forEach(val -> map.put(val, ""));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Problem[] problems = new Problem[t];
        for (int j = 0; j < t; j++) {
            int n = in.nextInt();
            String[] passwords = new String[n];
            for (int i = 0; i < n; ++i) {
                passwords[i] = in.next();
            }
            String combined = in.next();
            problems[j] = new Problem(passwords, combined);
        }
        for (Problem problem : problems) {
//            solution(problem.passwords, problem.loginAttempt, problem.map);
        }

    }

    private static void sol(String[] passwords, String loginAttempt) {
        Stack<String> stack = new Stack<>();
        boolean action = crack(passwords, loginAttempt, stack);
        if (action) {
            System.out.println(stack);
        } else {
            System.out.println("WRONG PASSWORD");
        }
    }

    private Map<String, Boolean> memo = new HashMap<>(1000);

    private static boolean crack(String[] passwords, String keys, Stack<String> stack) {
 /*       if len(password) == 0:
        return True

        if password in memo:
        return False

        for val in keys:
        if password[:len(val)] == val:
        res.append(val)
        memo[password] = True

        if _crack(password[len(val):], keys, res):
        return True

            # remove last element
        del res[-1]

        return False
 */

        if (passwords.length == 0) {
           return true;
        }

       /* for (String key : keys){

        }*/


        return false;
    }

    private static void solution(String[] passwords, String combined, Map<String, String> map) {
        Arrays.sort(passwords, Comparator.comparingInt(String::length).reversed());
        int element = 0;
        System.out.println(solutionHelper(combined, combined, element, passwords, map));
    }

    private static String solutionHelper(String combined, String matched, int element, String[] passwords, Map<String, String> map) {
        if (element == passwords.length || "".equals(matched)) {
            if ("".equals(matched)) {
                combined = combined.trim();
                combined = combined.replaceAll("\\s{2,}", " ");
                return combined;
            } else {
                return "WRONG PASSWORD";
            }
        }
        String word = passwords[element];
        matched = matched.replaceAll(word, "");
        List<String> list = Arrays.asList(combined.split("\\s"));
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (!map.containsKey(str)) {
                str = combined.startsWith(str) ? str.replaceAll(word, word + " ") :
                        (combined.endsWith(str) ? str.replaceAll(word, " " + word) : str.replaceAll(word, " " + word + " "));
                list.set(i, str);
            }
        }
        StringBuilder builder = new StringBuilder();
        list.stream().forEach(v -> builder.append(" " + v));
        return solutionHelper(builder.toString(), matched, element + 1, passwords, map);
    }
}