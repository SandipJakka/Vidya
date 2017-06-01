package com.nomura.sandeep.chronicle.clrs.chapter5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Implement RANDOM(A,B) using only RANDOM(0,1) ...
 * What is the expected runtime of this algorithm
 * ??
 */
public class Random5_1_2 {

    public int random(int a, int b) {
        Collections.shuffle(new ArrayList<>());
        return 0;
    }


}
