package com.nomura.sandeep.chronicle.hackerrank.algos;

import java.util.Scanner;

/**
 *
 */
public class BigSum {
    public static void main(String[] args) {
        long sum = 0;
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int no = scanner.nextInt();
        for (int i = 0; i < no; i++) {
            int element = scanner.nextInt();
            sum += element;
        }
        System.out.println(sum);
    }
}
