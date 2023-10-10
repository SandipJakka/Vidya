package com.nomura.sandeep.chronicle.codility;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q),
 * such that 0 ≤ P ≤ Q < N, is called a slice of array A. The sum of a slice (P, Q) is the
 * total of A[P] + A[P+1] + ... + A[Q].
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns the number of slices whose sum equals 0.
 * <p>
 * The function should return −1 if the number of such slices exceeds 1,000,000,000.
 * <p>
 * For example, given:
 * <p>
 * A[0] =  2
 * A[1] = -2
 * A[2] =  3
 * A[3] =  0
 * A[4] =  4
 * A[5] = -7
 * the function should return 4. Slices whose sum equals 0 are: (0, 1), (0,5), (2, 5), (3, 3).
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−10,000..10,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for
 * input arguments).
 */
public class CountZeroSumSlices {
}
