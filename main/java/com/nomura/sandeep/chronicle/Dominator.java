package com.nomura.sandeep.chronicle;

public class Dominator {

    public static void main(String[] args) {
        Dominator d = new Dominator();
        int[] A = new int[]{3, 4, 3, 2, 3, -1, 3, 3};
        System.out.println("===> + " + d.solution(A));
    }

    public int solution(int[] A) {
        if (A.length < 1) {
            return -1;
        }
        int size = 0;
        int value = -1;
        for (int i = 0; i < A.length; i++) {
            if (size == 0) {
                size += 1;
                value = A[i];
            } else {
                if (A[i] != value) {
                    size -= 1;
                } else {
                    size += 1;
                }
            }
        }
        int leader = -1;
        int candiate = -1;
        if (size > 0) {
            candiate = value;
        }
        int index = -1;
        int counter = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == candiate) {
                counter++;
                index = i;
            }
        }

        if (counter > A.length / 2) {
            leader = candiate;
        } else {
            index = -1;
        }

        System.out.println("Leader ===> " + leader);
        return index;

    }
}
