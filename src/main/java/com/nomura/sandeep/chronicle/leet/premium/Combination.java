package com.nomura.sandeep.chronicle.leet.premium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * <p>
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * Example 2:
 * <p>
 * Input: n = 1, k = 1
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combination {
    public List<List<Integer>> combine(int n, int k) {
        //combination()
        return Collections.emptyList();
    }

    List<List<Integer>> combination(List<List<Integer>> results, int start, int n, int k) {
        if (start == n) {
            return results;
        }
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        while(start < n){

        }
        return combinations;
    }
}
