package com.nomura.sandeep.chronicle.leet;

import java.util.BitSet;

/**
 * Created by sandeep.jakka on 4/27/19.
 * <p>
 * <p>
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 * <p>
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
 * <p>
 * Example 1:
 * <p>
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 * Example 2:
 * <p>
 * Input: J = "z", S = "ZZ"
 * Output: 0
 */
public class NumberOfJewels {
    private int numJewelsInStones(String J, String S) {
        BitSet bits = new BitSet(70);

        for (char j : J.toCharArray()) {
            bits.set(j - 65);
        }
        int count = 0;
        for (char s : S.toCharArray()) {
            int idx = s - 65;
            if (bits.get(idx)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfJewels n = new NumberOfJewels();

        System.out.println(n.numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(n.numJewelsInStones("zZ", "ZZzzzA"));
    }
}