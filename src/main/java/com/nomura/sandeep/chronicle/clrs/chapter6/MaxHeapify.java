package com.nomura.sandeep.chronicle.clrs.chapter6;

/**
 * Created by sandeep on 12/14/2016.
 */
public class MaxHeapify {

    public static void main(String[] args) {
        int[] A = new int[]{2, 4, 10, 14, 7, 9, 3, 16, 8, 1};
        MaxHeapify m = new MaxHeapify();
//        m.maxHepifyIterative(A, 1, A.length);
        m.maxHepify(A, 1, A.length);

        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }

    }

    public void maxHepify(int[] A, int index, int heapSize) {
        int l = leftIndex(A, index);
        int r = rightIndex(A, index);
        int largestIndex;
        int length = heapSize;
        if (l < length && A[l] > A[index]) {
            largestIndex = l;
        } else {
            largestIndex = index;
        }

        if (r < length && A[r] > A[largestIndex]) {
            largestIndex = r;
        }

        if (largestIndex != index) {
            int temp = A[index];
            A[index] = A[largestIndex];
            A[largestIndex] = temp;
            maxHepify(A, largestIndex, heapSize);
        }


    }


    public void maxHepifyIterative(int[] A, int index, int heapSize) {
        int largestIndex = -1;
        while (true) {
            int l = leftIndex(A, index);
            int r = rightIndex(A, index);

            if (l < heapSize && A[l] > A[index]) {
                largestIndex = l;
            } else {
                largestIndex = index;
            }

            if (r < heapSize && A[r] > A[largestIndex]) {
                largestIndex = r;
            }
            if (largestIndex == index) {
                return;
            }

            int temp = A[index];
            A[index] = A[largestIndex];
            A[largestIndex] = temp;

            index = largestIndex;
        }


    }


    /**
     * for a 0 Index array .. ....
     */
    int leftIndex(int[] A, int index) {
        int left = 2 * index + 1;
        return (left < A.length) ? left : A.length;
    }


    int rightIndex(int[] A, int index) {
        int righ = 2 * index + 2;
        return (righ < A.length) ? righ : A.length;
    }

}
