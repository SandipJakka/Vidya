package com.nomura.sandeep.chronicle.clrs.Chapter4;

/**
 * Created by sandeep on 12/12/2016.
 */
public class MaxSubArrayDivideAndConquer {

    public static void main(String[] args) {
        int[] A = new int[]{10, 11, 7, 10, 6};
        int[] A1 = new int[]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
        MaxSubArrayDivideAndConquer m = new MaxSubArrayDivideAndConquer();

        System.out.println(" ==> " + m.intermediaryStep(A1));
        System.out.println(" ==> " + m.intermediaryStep(A));
    }

    public int intermediaryStep(int[] A) {
        int[] diffs = new int[A.length - 1];
        for (int i = 0; i < A.length - 1; i++) {
            diffs[i] = A[i + 1] - A[i];
        }
        return findMaxSubArray(diffs, 0, diffs.length - 1);
    }

    public int findMaxSubArray(int[] A, int low, int high) {
        int ret = 0;
        if (low == high) {
            ret = A[low];
        } else {
            int mid = (low + high) / 2;
            int left = findMaxSubArray(A, low, mid);
            int right = findMaxSubArray(A, mid + 1, high);
            int center = findMaxInTheMiddle(A, low, mid, high);
            if (left > right && left > center) {
                ret = left;
            } else if (right > left && right > center) {
                ret = right;
            } else if (center > left && center > right) {
                ret = center;
            }
        }
        return ret;
    }


    private int findMaxInTheMiddle(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE, sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return (leftSum + rightSum);
    }

}
