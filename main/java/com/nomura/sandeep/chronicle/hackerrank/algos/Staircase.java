package com.nomura.sandeep.chronicle.hackerrank.algos;

import java.util.Scanner;

/**
 *
 */
public class Staircase {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int spaceCnt = 0;
        for (int i = 0; i < n; i++) {
            spaceCnt = n - (i + 1);
            System.out.print(new String(new char[spaceCnt]).replace("\0", " ") + new String(new char[n - spaceCnt]).replace("\0", "#") + "\n");
        }
    }
}
