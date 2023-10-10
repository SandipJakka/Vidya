package com.nomura.sandeep.chronicle.clrs.chapter5;

import java.util.Random;

/**
 * Implement RANDOM(A,B) using only RANDOM(0,1) ...
 * What is the expected runtime of this algorithm
 * ??
 */
public class Random5_1_2 {

    public static void main(String[] args) {
        for (int i = 0; i < 25; ++i) {
            System.out.println(rand(20, 25));
        }

        for (int i = 0; i < 25; ++i) {
            random(20, 25);
        }

/*
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d %n", rand5());
        }
        System.out.printf("==========%n");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d %n", rand7());
        }
*/
    }

    public static int random(int lower, int higher) {
        if (lower > higher) {
            lower = lower ^ higher;
            higher = lower ^ higher;
            lower = lower ^ higher;
        }
        int result = 0;
        int noOfOutcomes = higher - lower + 1;
        do {
            result = 0;
            for (int i = 0; (1 << i) < noOfOutcomes; ++i) {
                result = (result << 1) | rand(0, 1);
            }
        } while (result >= noOfOutcomes);
        System.out.println("Number : " + (lower + result));
        return lower + result;
    }


    public static int rand(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1));
    }


    public static int rand7() {
        int i;
        do {
            i = 5 * (rand5() - 1) + rand5();  // i is now uniformly random between 1 and 25
            System.out.printf("i = %d %n", i);
        } while (i > 21);
// i is now uniformly random between 1 and 21
        return i % 7 + 1;
    }

    static Random r = new Random();

    private static int rand5() {
        return r.nextInt(5);
    }

}