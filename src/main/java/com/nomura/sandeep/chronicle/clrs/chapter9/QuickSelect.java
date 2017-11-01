package com.nomura.sandeep.chronicle.clrs.chapter9;

/**
 * Using Median of medians to calculate the PIVOT...
 * <p>
 * Basically calculate the ith smallest element in O(n) time.
 */
public class QuickSelect {

    private static int counter = 0;


    private static int partition(int[] A, final int left, final int right, final int pivotIndex) {
        swap(A, right, pivotIndex);
        return partition(A, left, right);
    }

    private static void swap(int[] A, final int i, final int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static int partition(int[] A, final int start, final int end) {
        int pivot = A[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            counter++;
            if (A[j] <= pivot) {
                i = i + 1;
                if (i != j) {
                    swap(A, i, j);
                }
            }
        }
        swap(A, i + 1, end);
        return i + 1;
    }

/*
    private static int medianBasedPartition(int[] A, int left, int right) {
        int pivot = pivot(A, left, right);
        return partition(A, left, right, pivot);
    }
*/

    private static int randomizedPartition(int[] A, int left, int right) {
//        int pivot = pivot(A, left, right);
        int pivot = randomizedPivot(A, left, right);
        return partition(A, left, right, pivot);
    }

    private static int randomizedPivot(int[] a, int left, int right) {
        return randomBetween(left, right);
    }

    private static int randomBetween(int start, int end) {
        int max = (end - start);
        int maxBits = maxBits(max);
        int rand = randomize(maxBits);
        while (start + rand > end) {
            rand = randomize(maxBits);
        }
        return rand + start;
    }

    private static int randomize(int maxBits) {
        int rand = 0;
        for (int i = 1; i <= maxBits; i++) {
            rand = 2 * rand + (Math.random() > 0.5 ? 1 : 0);
        }
        return rand;
    }

    private static int maxBits(int max) {
        int ONE = 1;
        int counter = 1;
        while (true) {
            int value = ONE << counter;
            if (value > max) {
                return counter;
            }
            counter++;
        }
    }

    public static int recursiveSelect(int[] A, int left, int right, int n) {
        if (left == right) {
            return left;
        }
        int q = randomizedPartition(A, left, right);
        if (n == q) {
            return A[q];
        } else {
            int k = q - left + 1;
            if (n < k) {
                return recursiveSelect(A, left, k, n);
            } else {
                return recursiveSelect(A, k + 1, right, n);
            }
        }
    }

    public static int select(int[] A, int left, int right, final int n) {
        while (true) {
            if (left == right) {
                return left;
            }
            int q = randomizedPartition(A, left, right);
            if (n == q) {
                return A[q];
            } else if (n < q) {
                right = right - 1;
            } else if (n > q) {
                left = left + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{21, 56, 3, 99, 14, 17, 81};
        //3rd largest......
        System.out.println("ith smallest : Iter ===>" + QuickSelect.select(A, 0, A.length - 1, 3));
        System.out.println("ith Recursive ===>" + QuickSelect.recursiveSelect(A, 0, A.length - 1, 3));
    }

    private int random(int[] A) {
        return randomBetween(0, A.length - 1);
    }

}
