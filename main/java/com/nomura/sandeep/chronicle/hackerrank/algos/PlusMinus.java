package com.nomura.sandeep.chronicle.hackerrank.algos;

import java.util.Scanner;

/**
 * Created by sandeep on 1/8/2017.
 */
public class PlusMinus {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int no = scanner.nextInt();
        int pos = 0, neg = 0, ze = 0;
        for (int i = 0; i < no; i++) {
            int ele = scanner.nextInt();
            if (ele == 0) {
                ze++;
            } else if (ele > 0) {
                pos++;
            } else {
                neg++;
            }
        }
        System.out.printf("%f", Double.valueOf(pos) / Double.valueOf(no));
        System.out.println();
        System.out.printf("%f", Double.valueOf(neg) / Double.valueOf(no));
        System.out.println();
        System.out.printf("%f", Double.valueOf(ze) / Double.valueOf(no));
    }
}
