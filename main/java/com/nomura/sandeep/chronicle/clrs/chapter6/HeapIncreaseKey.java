package com.nomura.sandeep.chronicle.clrs.chapter6;

/**
 * Created by sandeep on 12/15/2016.
 */
public class HeapIncreaseKey {

    public static void main(String[] args) {
        HeapIncreaseKey h = new HeapIncreaseKey();

        int[] A = new int[]{};
    }


    private static int parent(int index) {
        return (index - 1) / 2;
    }

    public void heapIncreaseKey(int[] A, int index, int key) {
        if (key < A[index]) {
            throw new IllegalArgumentException("Key can't be smaller");
        }

        A[index] = key;

        while (index > 0 && A[index] < A[parent(index)]) {
            int temp = A[index];
            A[index] = A[parent(index)];
            A[parent(index)] = temp;
            index = parent(index);
        }

    }
}
