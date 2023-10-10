    package com.nomura.sandeep.chronicle.dp;

import com.google.common.collect.Sets;

import java.util.*;

public class GenerateInterleavingStrings {

    public Set<String> in = new HashSet<>();

    // O(2^n)
    private void brute(String string1, int pos1, String string2, int pos2, char[] interleaved, int intpost, int indent) {
        // System.out.printf("str1 = %s, m=%d, str2=%s, n=%d , inter= %s , i = %d \n", string1, pos1, string2, pos2, new String(interleaved), intpost);
        if (intpost == string1.length() + string2.length()) {
            in.add(new String(interleaved));
        }
        if (pos1 < string1.length()) {
            interleaved[intpost] = string1.charAt(pos1);
            brute(string1, pos1 + 1, string2, pos2, interleaved, intpost + 1, indent);
        }
        if (pos2 < string2.length()) {
            interleaved[intpost] = string2.charAt(pos2);
            brute(string1, pos1, string2, pos2 + 1, interleaved, intpost + 1, indent);
        }

    }

    // O(n^2)
    @SuppressWarnings("unchecked")
    private Set<String> dp(String string1, String string2) {
        long start = System.currentTimeMillis();

        int m = string1.length();
        int n = string2.length();

        List<char[]>[][] mem = new ArrayList[m + 1][n + 1];

        //init the mem cache....
        for (int row = 0; row <= m; row++) {
            for (int col = 0; col <= n; col++) {
                mem[row][col] = new ArrayList<>();
            }
        }

        // fill the top row
        for (int i = 1; i <= m; i++) {
            char[] el = new char[i];
            for (int c = 0; c < i; c++) {
                el[c] = string1.charAt(c);
            }
            mem[0][i].add(el);
        }


        // fill the left col
        for (int col = 1; col <= n; col++) {
            char[] el = new char[col];
            for (int c = 0; c < col; c++) {
                el[c] = string2.charAt(c);
            }
            mem[col][0].add(el);
        }

        // fill the cache
        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                mem[row][col].addAll(append(mem[row][col - 1], string1.charAt(col - 1)));
                mem[row][col].addAll(append(mem[row - 1][col], string2.charAt(row - 1)));
            }
        }
        System.out.printf("Time taken for DP method = %d \n", System.currentTimeMillis() - start);

        Set<String> out = new HashSet<>();
        //print
        List<char[]> res = mem[m][n];
        for (char[] chars : res) {
            out.add(new String(chars));
        }

        return out;
    }

    private List<char[]> append(List<char[]> one, char add) {
        List<char[]> ret = new ArrayList<>();
        for (char[] chars : one) {
            char[] newchars = Arrays.copyOf(chars, chars.length + 1);
            newchars[chars.length] = add;
            ret.add(newchars);
        }
        return ret;
    }

    private void printInterleavingStringsBrute(String str1, String str2) {
        long start = System.currentTimeMillis();
        in = new HashSet<>();
        brute(str1, 0, str2, 0, new char[str1.length() + str2.length()], 0, 0);
        System.out.printf("Time taken for brute force method = %d \n", System.currentTimeMillis() - start);
//        System.out.printf("count = %d ,", in.size());
//        System.out.println(in);
    }


    public static void main(String[] args) {
        GenerateInterleavingStrings st = new GenerateInterleavingStrings();
        st.printInterleavingStringsBrute("abcdefghi", "jklmnopqr");
        Set out = st.dp("abcdefghi", "jklmnopqr");

        Set diff = Sets.difference(out, st.in);

        System.out.println(diff);
    }
}