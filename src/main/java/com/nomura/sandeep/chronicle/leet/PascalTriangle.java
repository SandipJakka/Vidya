package com.nomura.sandeep.chronicle.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listOfLists = new ArrayList<>();
        if (numRows >= 1) {
            List<Integer> list = new ArrayList<>(1);
            list.add(1);
            listOfLists.add(list);
        }
        if (numRows >= 2) {
            List<Integer> list = new ArrayList<>(2);
            list.add(1);
            list.add(1);
            listOfLists.add(list);
        }
        for (int i = 3; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>(Collections.nCopies(i, 0));
            int start = 0, end = i - 1;
            list.set(start, 1);
            start++;
            list.set(end, 1);
            end--;
            int count = 2;
            int mid = (i / 2) + 1;
            while (start < mid) {
                List<Integer> integers = listOfLists.get(listOfLists.size() - 1);
                int val = integers.get(start - 1) + integers.get(start);
                list.set(start, val);
                start++;
                count++;
                if (count < i) {
                    list.set(end, val);
                    end--;
                    count++;
                }
            }
            listOfLists.add(list);
        }
        return listOfLists;
    }

    public static void main(String[] args) {
        PascalTriangle p = new PascalTriangle();
        List<List<Integer>> list = p.generate(5);
        System.out.printf("%s \n", list);
    }
}
