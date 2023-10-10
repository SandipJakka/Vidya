package com.nomura.sandeep.chronicle.elements.arrays;

/**
 * Created by sandeep.jakka on 6/21/18.
 */
public class EfficientGCD {


    static int gcd(int x, int y) {
        if (x == 0) {
            return y;
        } else if (y == 0) {
            return x;
        } else if (((x & 1) != 0) && ((y & 1) != 0)) { // both x & y are odd
            return gcd(x << 1, y << 1) >> 1;
        } else if (((x & 1) != 0) && ((y & 1) == 0)) {    // x is odd and y is even
            return gcd(x << 1, y) >> 1;
        } else if (((x & 1) == 0) && ((y & 1) != 0)) {  // x is even , y is odd
            return gcd(x, y << 1) >> 1;
        } else {  //both are even
            return (x > y) ? gcd(x - y, y) : gcd(x, y - x);
        }
    }

    static void gcdWithPrint(int x, int y) {
        System.out.printf("GCD for (%d,%d) is %d %n", x, y, gcd(x, y));
    }

    public static void main(String[] args) {
        gcdWithPrint(7, 5);
        gcdWithPrint(24, 300);

        gcdWithPrint(2,4);

        //int x = 10;

        //System.out.printf("%d %n ", (x >> 1));
    }
}