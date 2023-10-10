package com.nomura.sandeep.chronicle.elements.recursion;


import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SuperSet {

    public static void solution(int[] A) {
        int n = A.length;
        List<List<Integer>> superSet = new ArrayList<>(n);
        List current;
        for (int i = 0; i < (1 << n); i++) {
            int x = i;

            current = Lists.newArrayList();
            System.out.println("i=" + i);
            while (x > 0) {
                int y = x & ~(x - 1);
                current.add(A[log(y, 2)]);
                x = x & x - 1;

                System.out.println(Integer.toBinaryString(x));
            }

  /*          current = Lists.newArrayList();
            int mask = 1;
            int index = 0;
            while (index < n) {
                if ((i & mask) > 0) {
                    current.add(A[index]);
                }
                mask = mask << 1;
                index++;
            }*/
            superSet.add(current);
        }
        superSet.forEach(el -> System.out.println(el));
    }

    static int log(int x, int base) {
        return (int) (Math.log(x) / Math.log(base));
    }


    public static void recursive(int[] A) {
        List<List<Integer>> superSet = new ArrayList<>(A.length);
        Stack<Integer> stack = new Stack<>();
        helper(0, A, stack, superSet);
        //superSet.forEach(el -> System.out.println(el));
    }

    static Joiner COMMA = Joiner.on(",");

    private static void helper(int selectedSoFar, int[] A, Stack<Integer> stack, List<List<Integer>> superSet) {
        String indent = new String(new char[selectedSoFar]).replace('\0', '.');

        System.out.println(String.format(" %s helper( %d, [%s])", indent, selectedSoFar, COMMA.join(stack)));
        if (selectedSoFar == A.length) {
            superSet.add(Lists.newArrayList(stack.iterator()));
            return;
        }
        stack.push(A[selectedSoFar]);
        helper(selectedSoFar + 1, A, stack, superSet);
        stack.pop();
        helper(selectedSoFar + 1, A, stack, superSet);
    }


    public static void main(String[] args) {
        SuperSet.solution(new int[]{0, 1, 2, 4});

//        SuperSet.recursive(new int[]{0, 1, 2});
    }
}