package com.nomura.sandeep.chronicle.hackerrank;

import java.util.Scanner;

/**
 * Created by sandeep on 7/7/2016.
 */
public class BinaryNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        System.out.println(count(n));
    }

    public static int count(int input) {
        int count = 0;
        int result = 0;

        if (input < 1) {
            return 0;
        } else {
            while (input > 0) {
                if ((input & 1) == 1) {
                    count++;
                } else {
                    result = Integer.max(result, count);
                    count = 0;
                }
                input = input >> 1;
            }

        }
        return result > count ? result : count;
    }
}
