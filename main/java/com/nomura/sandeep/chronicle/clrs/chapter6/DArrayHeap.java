package com.nomura.sandeep.chronicle.clrs.chapter6;

/**
 * A d-ary heap is like a binary heap , with non-leaf nodes have d children instead of 2 children.
 * <p>
 * Implement
 * EXTRACT-MAX ( A )
 * INSERT( A, k)
 * INCREASE-KEY(A,i,k)
 * </p>
 */
public class DArrayHeap {

    public static void main(String[] args) {
        int[] A = new int[]{6, 11, 9, 8, 3, 10, 14, 7, 1};

        DArrayHeap d = new DArrayHeap();
//        d.maxHeapify(A, 0, 4, A.length);
//        d.maxHeapifyRecursive(A, 0, 4, A.length);

        int heapSize = A.length;
        d.buildMaxHeap(A, 4, heapSize);

        System.out.println("==>" + d.xtractMax(A, 4, heapSize));
        heapSize = heapSize - 1;
        for (int i = 0; i < heapSize; i++) {
            System.out.print(" " + A[i]);
        }

    }

    public static int parent(int[] A, int index, int d) {
        int parent = (index - d) / d;
        return parent < A.length ? parent : -1;
    }

    public static int leftMostChild(int[] A, int index, int d) {
        int left = (d * index) + 1;
//        return left < A.length ? left : -1;
        return left < A.length ? left : A.length;
    }

    public static int rightMostChild(int[] A, int index, int d) {
        int right = (d * index) + d;
//        return right < A.length ? right : -1;
        return right < A.length ? right : A.length;
    }

    public int xtractMax(int[] A, int d, int heapSize) {
        buildMaxHeap(A, d, heapSize);
        int max = A[0];
        A[0] = A[heapSize - 1];
        maxHeapify(A, 0, d, heapSize - 1);
        return max;
    }


//    public


    public void buildMaxHeap(int[] A, int d, int heapSize) {
        maxHeapify(A, 0, d, heapSize);
        for (int i = (d / 2); i >= 0; i--) {
            maxHeapify(A, i, d, heapSize);
        }

    }

    public void maxHeapify(int[] A, int index, int d, int heapSize) {
        int largestIndex = index;
//        System.out.println("Index :" + index);
        while (true) {
            int leftMostChild = leftMostChild(A, index, d);
            int rightMostChild = rightMostChild(A, index, d);
            int max = A[index];
            int rangeToCheck = Math.max(0, Math.min(rightMostChild, Math.min(heapSize, A.length)));
            for (int i = leftMostChild; i < rangeToCheck; i++) {
                if (A[i] > max) {
                    max = A[i];
                    largestIndex = i;
                }
            }

            if (largestIndex == index) {
                return;
            }
            int temp = A[index];
            A[index] = max;
            A[largestIndex] = temp;

            index = largestIndex;
        }

    }

    public void maxHeapifyRecursive(int[] A, int index, int d, int heapSize) {
        int largestIndex = -1;

        int leftMostChild = leftMostChild(A, index, d);
        int rightMostChild = rightMostChild(A, index, d);
        int max = Integer.MIN_VALUE;
        int rangeToCheck = Math.min(rightMostChild, Math.min(heapSize, A.length));
        for (int i = leftMostChild; i < rangeToCheck; i++) {
            if (A[i] > max) {
                max = A[i];
                largestIndex = i;
            }

        }
        if (largestIndex > 0 && largestIndex != index) {
            int temp = A[index];
            A[index] = max;
            A[largestIndex] = temp;
            maxHeapifyRecursive(A, largestIndex, d, heapSize);
        }
    }


}
