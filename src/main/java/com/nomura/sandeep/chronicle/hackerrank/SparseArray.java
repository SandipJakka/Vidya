package com.nomura.sandeep.chronicle.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SparseArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int strCt = scanner.nextInt();
        List<String> strings = new ArrayList<>(strCt);
        for (int i = 0; i < strCt; i++) {
            strings.add(scanner.next());
        }
        int qCt = scanner.nextInt();
        List<String> queryList = new ArrayList<>();
        for (int i = 0; i < qCt; i++) {
            queryList.add(scanner.next());
        }
        strings.sort((o1, o2) -> o1.compareTo(o2));
        printAns(strings, queryList);
    }

    private static void printAns(List<String> strings, List<String> queryList) {
        int counter = 0;
        for (String query : queryList) {
            counter = 0;
            for (String string : strings) {
                if (query.equals(string)) {
                    counter++;
                } else if (string.charAt(0) > query.charAt(0)) {
                    break;
                }
            }
            System.out.println(counter);
        }
    }
}