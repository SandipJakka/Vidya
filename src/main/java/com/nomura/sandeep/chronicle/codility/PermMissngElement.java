package com.nomura.sandeep.chronicle.codility;

/**
 * A zero-indexed array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 * <p>
 * Your goal is to find that missing element.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * that, given a zero-indexed array A, returns the value of the missing element.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 2
 * A[1] = 3
 * A[2] = 1
 * A[3] = 5
 * the function should return 4, as it is the missing element.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class PermMissngElement {

    public static void main(String[] args) {
        int[] A = new int[]{2, 3, 1, 5};
        PermMissngElement pm = new PermMissngElement();
        System.out.println("===> " + pm.solution(A));
    }

    /// Taking size as long is the key here... why though ??? //TODO ???
    public int solution(int[] A) {
        long size = (A.length + 1);
        long sumOfNElements = (size * (size + 1)) / 2;
        long sum = 0;
        for (int i = 0; i < A.length; i++)
            sum = sum + A[i];
        return (int) (sumOfNElements - sum);
    }


}
