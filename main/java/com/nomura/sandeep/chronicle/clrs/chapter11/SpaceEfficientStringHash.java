package com.nomura.sandeep.chronicle.clrs.chapter11;

import java.util.Arrays;

/**
 * Problem :
 * 11.3-2
 * Suppose that we hash a string of r characters into m slots by treating it as a radix-128 number
 * and then using the division method. We can easily represent the number m as a 32-bit computer word,
 * but the string of r characters, treated as a radix-128 number, takes many words. How can we apply the division
 * method to compute the hash value
 * of the character string without using more than a constant number of words of storage outside the string itself?
 * <p>
 * <p>
 * http://robentan.blogspot.com/2011/10/space-restricted-string-hashing.html
 */
public class SpaceEfficientStringHash {

    public static void main(String[] args) {
        String str = "http://robentan.blogspot.com/2011/10/space-restricted-string-hashing.html";
        System.out.println(stringHash(str, 128, Integer.MAX_VALUE));
        System.out.println(stringHash(str, 128, 13));
        System.out.println(str.hashCode());

        System.out.println(singlePatternStringMatch(str, "blogspot"));
        System.out.println(singlePatternStringMatch(str, ".html"));
        System.out.println(singlePatternStringMatch(str, "hashing"));
        System.out.println(singlePatternStringMatch(str, ".hashing."));
//        System.out.println(singlePatternStringMatchImproved(str, "blogspot"));
    }

    /**
     * Radix 128 ==> Base 128
     *
     * @param str
     * @param radix
     * @param m     max value to represent the hashValue
     * @return
     */

    public static int stringHash(String str, int radix, int m) {
        int hashValue = 0;
        int len = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0; i < len; ++i) {
            hashValue = (hashValue * radix + chars[i]) % m;
        }
        return hashValue;
    }


/*
    Problem with this idea is we can add a character to the hash function, but cant actually delete any.
    public static boolean singlePatternStringMatchImproved(String string, String search) {
        if (string.equals(search)) {
            return true;
        } else if (search.length() > string.length()) {
            return false;
        }
        int searchLen = search.length();
        char[] input = string.toCharArray();
        int inputHashValue = stringHash(String.valueOf(input[searchLen - 1]), 101, 11);
        String subStr = String.valueOf(Arrays.copyOfRange(input, 0, 0 + searchLen));
        int subStringHashVal = stringHash(subStr, 101, 11);
        for (int i = 1; i < input.length - searchLen + 1; ++i) {
            if ((subStringHashVal == inputHashValue) && subStr.equals(search)) {
                return true;
            }
            subStr = String.valueOf(Arrays.copyOfRange(input, i, i + searchLen));
            subStringHashVal = (subStringHashVal * 128 * input[i]) % 11;
        }
        return false;
    }
*/

    public static boolean singlePatternStringMatch(String string, String search) {
        if (string.equals(search)) {
            return true;
        } else if (search.length() > string.length()) {
            return false;
        }
        int searchLen = search.length();
        char[] input = string.toCharArray();
        int inputHashValue = stringHash(search, 101, 10);
        for (int i = 0; i < input.length - searchLen + 1; ++i) {
            String subStr = String.valueOf(Arrays.copyOfRange(input, i, i + searchLen));
            int subStringHashVal = stringHash(subStr, 101, 10);
            if ((subStringHashVal == inputHashValue) && subStr.equals(search)) {
                return true;
            }
        }
        return false;
    }

}
