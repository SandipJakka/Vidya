package com.nomura.sandeep.chronicle.geeks.bits;


import static java.lang.Integer.toBinaryString;

public class BitsBasics {

    public static void main(String[] args) {
/*
        turnOffRightMostBit(3);
        turnOffRightMostBit(4);
        turnOffRightMostBit(7);

        isPowerOfTwo(8);
        isPowerOfTwo(12);
*/

/*
        turnONRightMostBit(3);
        turnONRightMostBit(4);
        turnONRightMostBit(7);
*/

/*
        turnOffTrailing1s(3);
        turnOffTrailing1s(4);
        turnOffTrailing1s(5);
        turnOffTrailing1s(7);

        isNumber2NMinus1(7);
        isNumber2NMinus1(10);
*/

/*
        turnONTrailing0s(21);
        turnONTrailing0s(23);
*/
        int x = 0;
        System.out.println(~x);

    }

    public static int turnOffRightMostBit(int x) {
        System.out.println("Turn off last bit");
        System.out.println("Before :  x =" + toBinaryString(x));
//        Integer.
//        System.out.println("(x-1) :  x-1 =" + toBinaryString(x - 1));
        x = x & (x - 1);
        System.out.println(" After : x = " + toBinaryString(x));
        return x;
    }

    public static boolean isPowerOfTwo(int x) {
        boolean isPowerOfTwo = (turnOffRightMostBit(x) == 0);
        System.out.printf("Is %d a powerOf 2 : %b \n", x, isPowerOfTwo);
        return isPowerOfTwo;
    }

    public static int turnONRightMostBit(int x) {
        System.out.println("Turn ON last bit , x = " + x);
        System.out.println("Before :  x =" + toBinaryString(x));
        System.out.println("(x-1) :  =" + toBinaryString(x + 1));
        x = x | (x + 1);
        System.out.println(" After : x = " + toBinaryString(x));
        System.out.println("----------------------------------");
        return x;
    }


    public static int turnOffTrailing1s(int x) {
        System.out.println("Turn Off trailing 1s  , x = " + x);
        System.out.println("Before :  x =" + toBinaryString(x));
        System.out.println("(x+1) :  =" + toBinaryString(x + 1));
        x = x & (x + 1);
        System.out.println(" After : x = " + toBinaryString(x));
        System.out.println("----------------------------------");
        return x;
    }

    public static boolean isNumber2NMinus1(int x) {
        boolean is = (turnOffTrailing1s(x) == 0);
        System.out.printf("Is %d a 2^n-1  : %b \n", x, is);
        return is;
    }

    public static int turnONTrailing0s(int x) {
        System.out.println("Turn ON trailing 1s  , x = " + x);
        System.out.println("Before :  x =" + toBinaryString(x));
        System.out.println("(x-1) :  =" + toBinaryString(x - 1));
        x = x | (x - 1);
        System.out.println(" After : x = " + toBinaryString(x));
        System.out.println("----------------------------------");
        return x;
    }

}
