package com.nomura.sandeep.chronicle.elements.recursion;


import com.google.common.collect.Lists;

import java.util.List;

public class AllKSubsets {

    public static void solution(int k, int n) {
        if (k > n) {
            throw new IllegalArgumentException("k has to be smaller than n");
        }
        List<List<Integer>> results = Lists.newArrayList();
        helper(0, n, k, results);
        results.forEach(el -> System.out.println(el));
    }

    private static void helper(int startIdx, int n, int k, List<List<Integer>> results) {
        String indent = new String(new char[startIdx]).replace('\0', '.');
        System.out.println(String.format("%s helper(%d)", indent, startIdx));
        if (startIdx >= (n - k + 1)) {
            return;
        }
        int endIdx = startIdx + 1;
        List<Integer> current;
        while (endIdx <= (n - k + 1)) {
            current = Lists.newArrayList();
            current.add(startIdx + 1);
            for (int i = endIdx; i < (endIdx + k - 1); i++) {
                current.add(i + 1);
            }
            endIdx++;
            results.add(current);
        }
        helper(startIdx + 1, n, k, results);
    }

    public static void main(String[] args) {
        //AllKSubsets.solution(2, 5);
        AllKSubsets.solution(2, 10);
    }

}