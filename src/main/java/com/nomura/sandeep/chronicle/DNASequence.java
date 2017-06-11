package com.nomura.sandeep.chronicle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandeep on 4/9/2016.
 */
public class DNASequence {
    public static void main(String[] args) {
        DNASequence sequence = new DNASequence();

        String S = "TC";
        int[] P = new int[]{0, 0, 1};
        int[] Q = new int[]{1, 0, 1};
        int[] op = sequence.solution(S, P, Q);
        System.out.println("====>" + op);
    }


    public int[] solution(String S, int[] P, int[] Q) {

        int[] out = new int[P.length];
        Map<Character, Integer> map = new HashMap<>();
        map.put(Character.valueOf('A'), 1);
        map.put(Character.valueOf('C'), 2);
        map.put(Character.valueOf('G'), 3);
        map.put(Character.valueOf('T'), 4);
        int min = 0;
        for (int i = 0; i < P.length; i++) {
            min = Integer.MAX_VALUE;
            int start = P[i];
            int end = Q[i];
            do {
                min = Integer.min(map.get(Character.valueOf(S.charAt(start))), min);
                start++;
            } while (start <= end);
            out[i] = min;
        }
        return out;
    }
}
