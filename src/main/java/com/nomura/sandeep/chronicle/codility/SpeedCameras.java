package com.nomura.sandeep.chronicle.codility;

import java.util.Arrays;

/**
 * Created by sandeep on 12/3/2016.
 */
public class SpeedCameras {

    public static void main(String[] args) {
        int A[] = new int[]{5, 1, 0, 2, 7, 0, 6, 6, 1};
        int B[] = new int[]{1, 0, 7, 4, 2, 6, 8, 3, 9};

        SpeedCameras s = new SpeedCameras();

        System.out.println("===> " + s.solution(A, B, 2));
    }

    public int solution(int[] A, int[] B, int K) {
        int min = 0;
        InterSection[] nodes = new InterSection[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            if (nodes[A[i]] == null) {
                nodes[A[i]] = new InterSection(A[i]);
            }
            nodes[A[i]].connecting = nodes[A[i]].connecting + B[i];
            nodes[A[i]].count = nodes[A[i]].count + 1;
            if (nodes[B[i]] == null) {
                nodes[B[i]] = new InterSection(B[i]);
            }
            nodes[B[i]].connecting = nodes[B[i]].connecting + A[i];
            nodes[B[i]].count = nodes[B[i]].count + 1;

        }

        Arrays.parallelSort(nodes, ((InterSection o1, InterSection o2) -> o1.count >= o2.count ? -1 : 1));


        return min;
    }

    class InterSection {
        private final int number;
        String connecting = "";
        int count = 0;


        InterSection(int number) {
            this.number = number;
        }
    }


}
