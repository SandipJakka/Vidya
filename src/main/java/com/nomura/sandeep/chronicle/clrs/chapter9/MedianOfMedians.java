package com.nomura.sandeep.chronicle.clrs.chapter9;

import java.util.Arrays;

/**
 * Created by sandeep on 1/6/2017.
 */
public class MedianOfMedians {


    private static int medianOf5(int[] A, final int left, final int right) {
        int right1 = (right + 1) <= A.length ? right + 1 : right;
        int[] copied = Arrays.copyOfRange(A, left, right1);
        Arrays.sort(copied);
        int tempMedianIndex = 0;
        if (copied.length < 4) {
            tempMedianIndex = (copied.length - 1) / 2;
        } else {
            tempMedianIndex = (copied.length / 2);
        }

        int i = 0;
        while (true) {
            if (copied[tempMedianIndex] == A[i]) {
                return i;
            }
            i++;
        }
    }

    private static int pivot(int[] A, final int left, final int right) {
        if (right - left <= 5) {
            return medianOf5(A, left, right);
        }
        for (int i = left; i < right; i = i + 5) {
            int subRight = i + 4;
            if (subRight > right) {
                subRight = right;
            }
            int median5 = medianOf5(A, i, subRight);
            int indexToSwap = left + (int) Math.floor((double) (i - left) / 5);
            swap(A, median5, indexToSwap);
        }
        int right1 = (left + ((int) Math.ceil((double) (right - left) / 5)) - 1);
        int valueToCheck = (left + ((right - left) / 10));
//        return select(A, left, right1, valueToCheck);
        return selectBetter(A, left, right1, valueToCheck);
    }

    private static int medOfMedians(int[] A, final int left, final int right) {
        if (right - left <= 5) {
            return medianOf5(A, left, right);
        }
        int counter = 0;
        for (int i = left; i < right; i = i + 5) {
            int subRight = i + 4;
            if (subRight > right) {
                subRight = right;
            }
            int median5 = medianOf5(A, i, subRight);
            //int indexToSwap = left + (int) Math.floor((double) (i - left) / 5);
            int indexToSwap = counter;
            swap(A, median5, indexToSwap);
            counter++;
        }
//        int right1 = (left + ((int) Math.ceil((double) (right - left) / 5)) - 1);
//        int right1 = counter-1;
//        int valueToCheck = (left + ((right - left) / 10));
//        int valueToCheck = (left + ((right - left) / 10));
//        return select(A, left, right1, valueToCheck);
//        return selectBetter(A, left, right1, valueToCheck);

        return medOfMedians(A, left, counter);
    }

    private static void swap(int[] A, final int i, final int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static int select(int[] A, int left, int right, final int n) {
        while (true) {
            if (left == right) {
                return left;
            }
            int pivotIndex = medOfMedians(A, left, right);
            int q = partition(A, left, right, pivotIndex);
            if (n == q) {
                return A[q];
            } else if (n < q) {
                right = right - 1;
            } else if (n > q) {
                left = left + 1;
            }
        }
    }

    public static int selectBetter(int[] A, int left, int right, int n) {
        while (left < right - 1) {
            int pivotIndex = pivot(A, left, right);
            int q = partition(A, left, right, pivotIndex);
            int k = q - left;
            if (n == k) {
                return A[q];
            } else if (n < k) {
                right = q;
            } else if (n > q) {
                left = left + 1;
                n = n - k - 1;
            }
        }
        return A[left];
    }

    private static int partition(int[] A, int left, int right, int pivotIndex) {
        swap(A, right, pivotIndex);
        int pivot = A[right];
        int j = left - 1;
        for (int i = left; i < right; i++) {
            if (A[i] <= pivot) {
                j++;
                if (i != j) {
                    swap(A, i, j);
                }
            }
        }
        swap(A, right, j + 1);
        return j + 1;
    }


    public static void main(String[] args) {
//        int[] A = new int[]{6, 4, 9, 3, 21, 5, 7, 45, 0, 99, -1, 2};
        int[] A = new int[]{6, 4, 9, 3, 21, 41, 99, 2, 0, -1, 51};

//        System.out.println("----" + pivot(A, 0, A.length - 1));
        System.out.println("----" + A[medOfMedians(A, 0, A.length - 1)]);
        System.out.println("----" + selectBetter(A, 0, A.length - 1, 6));
    }

}
