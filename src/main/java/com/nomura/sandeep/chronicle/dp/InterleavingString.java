package com.nomura.sandeep.chronicle.dp;

public class InterleavingString {

    private boolean isInterleavingCheckBrute(String toCheck, String str) {
        boolean inter = false;
        int i = 0, j = 0;
        while (i < toCheck.length()) {
            char t = toCheck.charAt(i);
            if (str.charAt(j) == t) {
                if (j == str.length() - 1) {
                    inter = true;
                    break;
                }
                j++;
            }
            i++;
        }
        return inter;
    }

    private boolean isInterleavingStrings(String str1, String str2, String toCheck) {
        return toCheck.length() == (str1.length() + str2.length()) &&
                isInterleavingCheckBrute(toCheck, str1) &&
                isInterleavingCheckBrute(toCheck, str2);
    }

    private boolean isInterleavingRecursive(String str1, String str2, String toCheck) {
        return toCheck.length() == (str1.length() + str2.length()) &&
                recursiveCheck(str1, 0, str2, 0, toCheck, 0);
    }

    // O(2^n)
    private boolean recursiveCheck(String str1, int string1Start, String str2, int string2Start, String toCheck, int tocheckStringStart) {
        if (tocheckStringStart == toCheck.length() - 1) {
            return string1Start >= str1.length() - 1 && string2Start >= str2.length() - 1;
        }

        if (string1Start < str1.length() && str1.charAt(string1Start) == toCheck.charAt(tocheckStringStart)) {
            return recursiveCheck(str1, ++string1Start, str2, string2Start, toCheck, ++tocheckStringStart);
        }
        return string2Start < str2.length() && str2.charAt(string2Start) == toCheck.charAt(tocheckStringStart)
                && recursiveCheck(str1, string1Start, str2, ++string2Start, toCheck, ++tocheckStringStart);
    }

    public static void main(String[] args) {
        InterleavingString l = new InterleavingString();

        System.out.printf("result = %s \n", l.isInterleavingStrings("xyz", "abcd", "xaybzcd"));
        System.out.printf("result = %s \n", l.isInterleavingStrings("xyz", "abcd", "xaybzc1"));
        System.out.printf("result = %s \n", l.isInterleavingRecursive("xyz", "abcd", "xaybzcd"));
        System.out.printf("result = %s \n", l.isInterleavingRecursive("bcc", "bbca", "bbcbcac"));
        System.out.printf("result = %s \n", l.isInterleavingRecursive("bbca", "bcc", "bbcbcac"));
        System.out.printf("result = %s \n", l.isInterleavingStrings("bbca", "bcc", "bbcbcac"));
    }
}