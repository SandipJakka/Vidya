package com.nomura.sandeep.chronicle.elements.arrays;

import java.util.BitSet;

public class LongestSubstringWithoutRepitions {

    public static int solution(String string) {
        int maxLen = 0;
        if (null == string || "".equals(string)) {
            return maxLen;
        }
        int currLen = 0;
        BitSet bitSet = new BitSet(26);

        for (int i = 0; i < string.length(); i++) {
            char current = string.charAt(i);
            int calculated = current - 'a';

            if (bitSet.get(calculated)) {
                maxLen = Math.max(maxLen, currLen);

            }
        }

        return maxLen;
    }
}
