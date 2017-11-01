package com.nomura.sandeep.chronicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by sandeep on 4/8/2016.
 */
public class MinimalPositiveNumber {

    public static void main(String[] args) {
        MinimalPositiveNumber pm = new MinimalPositiveNumber();
        int[] A = new int[]{1, 3, 6, 4, 1, 2};
        System.out.println("=====>" + pm.solution(A));
    }

    public int solution(int[] A) {
        int max = 0;

        List<Boolean> list = new ArrayList<>(Arrays.asList(new Boolean[A.length + 1]));
        Collections.fill(list, Boolean.FALSE);

        if (A.length == 0 || A.length > Integer.MAX_VALUE) {
            return 0;
        }
        boolean anyPositive = false;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                continue;
            }
            max = Integer.max(A[i], max);
            anyPositive = true;
            list.set(A[i], Boolean.TRUE);
        }
        if (!anyPositive) {
            return 1;
        }
        for (int j = max; j > 0; j--) {
            if (list.get(j) == Boolean.FALSE) {
                return j;
            }
        }
        return max;
    }

    /*public int solution(int[] A) {
        TreeSet<Integer> set = new TreeSet<>();
        if (A.length == 0 || A.length > Integer.MAX_VALUE) {
            return 0;
        }
        set.addAll(Ints.asList(A));
        Integer maxElement = set.descendingSet().first();
        if (maxElement < 0) {
            return 1;
        }
        Iterator<Integer> iter = set.descendingIterator();
        Integer prev = null;
        while (iter.hasNext()) {
            Integer current = iter.next();
            if (prev != null && (prev.intValue() - 1) != current.intValue()) {
                return (prev - 1);
            }
            prev = current;
        }
        return 1;
    }*/
}

