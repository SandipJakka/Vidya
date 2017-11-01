package com.nomura.sandeep.chronicle.elements.chapter1;

/**
 * Created by sandeep.jakka on 10/30/17.
 */
public class ReverseBits {

    static byte[] prepopulated = new byte[]{11, 10, 01, 00};


    public static void main(String[] args) {
 /*       ReverseBits.Of(10);
        ReverseBits.Of(8);
        ReverseBits.Of(199);
        ReverseBits.Of(44);

        ReverseBits.OfWierd(10);
        ReverseBits.OfWierd(8);
        ReverseBits.OfWierd(199);
        ReverseBits.OfWierd(44);
 */
        ReverseBits.swapBits(10, 0, 1);
        ReverseBits.swapBits(10, 2, 1);


        /*ReverseBits.rev(10);
        ReverseBits.rev(8);
        ReverseBits.rev(199);
        ReverseBits.rev(44);
*/


        ReverseBits.lookup(10);

    }

    public static int Of(int number) {
        System.out.println("Before flip : " + Integer.toBinaryString(number));
        int length = Integer.toBinaryString(number).length();
        int mask = 0;
        if (length <= 4) {
            mask = 0xF;
        } else if (length <= 8) {
            mask = 0xFF;
        } else if (length <= 12) {
            mask = 0xFFF;
        } else {
            mask = 0xFFFF;
        }
        int bitsFlipped = number ^ mask;
        System.out.println("After : " + Integer.toBinaryString(bitsFlipped));
        return bitsFlipped;
    }

    public static void OfWierd(int x) {
        System.out.println("Before : " + Integer.toBinaryString(x));
        x = (x & 0x5555) << 1 | (x & 0xAAAA) >> 1;
        x = (x & 0x3333) << 2 | (x & 0xCCCC) >> 2;
        x = (x & 0x0F0F) << 4 | (x & 0xF0F0) >> 4;
        x = (x & 0x00FF) << 8 | (x & 0xFF00) >> 8;
/*
        x = (x & 0x0000FFFF) << 16 | (x & 0xFFFF0000) >> 16;
*/

        System.out.println("After : " + Integer.toBinaryString(x));
    }

    public static void rev(int number) {
        System.out.println(Integer.toBinaryString(number));
        for (int i = 0; i < Integer.toBinaryString(number).length(); i += 2) {
            number = swapBits(number, i, i + 1);
        }
        System.out.println(Integer.toBinaryString(number));
    }

    public static int swapBits(int number, int i, int j) {
        //ith bit != jth bit
        if (((number << i) ^ 1) != ((number << j) ^ 1)) {
            int mask = 1 << i | 1 << j;
            int sw = number ^ mask;
            return sw;
        }
        return number;
    }

    public static void lookup(int number) {
        System.out.println("B :" + Integer.toBinaryString(number));
        int sw = ((prepopulated[number >> 2] | 0xFF) << 2) | (prepopulated[number >> 2] | 0xFF);
        System.out.println("B :" + Integer.toBinaryString(sw));

    }
}