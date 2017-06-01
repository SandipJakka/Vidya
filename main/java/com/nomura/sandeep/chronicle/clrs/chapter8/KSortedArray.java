package com.nomura.sandeep.chronicle.clrs.chapter8;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by sandeep on 1/3/2017.
 */
public class KSortedArray {

    public static int[] ksortedArray(int[] A, int k) {
        int[] sorted = new int[A.length];
        Queue<Integer> minPriorityHeap = new PriorityQueue<>(k + 1);
        for (int i = 0; i <= k; i++) {
            minPriorityHeap.add(A[i]);
        }
        int counter = 0;
        for (int i = k + 1; i < A.length; i++) {
            sorted[counter] = minPriorityHeap.poll();
            minPriorityHeap.add(A[i]);
            counter++;
        }
        while (!minPriorityHeap.isEmpty()) {
            sorted[counter++] = minPriorityHeap.poll();
        }
        return sorted;
    }

    public static void kSortMoidifyInput(int[] arr, int k) {
        Queue<Integer> queue = new PriorityQueue<>(k + 1);
        for (int i = 0; i <= k; i++) {
            queue.add(arr[i]);
        }
        int ctr = 0;
        for (int i = k + 1; i < arr.length; i++) {
            arr[ctr++] = queue.poll();
            queue.add(arr[i]);
        }

        while (!queue.isEmpty()) {
            arr[ctr++] = queue.poll();
        }
    }

    public static void main(String[] args) {
        int arr[] = {2, 6, 3, 12, 56, 8};
        int[] actual = ksortedArray(arr, 3);

        for (int i = 0; i < actual.length; i++) {
            System.out.print("," + actual[i]);
        }

        kSortMoidifyInput(arr, 3);
        System.out.println("");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("," + arr[i]);
        }
    }
}
