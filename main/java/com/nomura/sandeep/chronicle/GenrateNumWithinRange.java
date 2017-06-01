package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 4/8/2016.
 */
public class GenrateNumWithinRange {
    public int solution(int A, int B, int K) {
        if (A > B || K > B || A < 0 || B < 0 || K < 0) {
            return 0;
        }

        int diff = (B / K) - (A / K);
        if (A % K == 0) {
            diff = diff + 1;
        }
        return diff;
    }
}
