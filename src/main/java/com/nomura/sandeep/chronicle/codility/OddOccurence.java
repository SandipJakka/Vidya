package com.nomura.sandeep.chronicle.codility;

/**
 * Created by sandeep on 11/20/2016.
 */
public class OddOccurence {


    public static void main(String[] args) {
        OddOccurence oc = new OddOccurence();
        int A[] = new int[]{2, 3, 2, 3, 9};
        System.out.println(oc.solution(A));
    }

    public int solution(int[] A) {
        int odd = 0;
        if (A.length == 0 || (A.length % 2) == 0) {
            return -1;
        }
        for (int i = 0; i < A.length; i++) {
            odd = odd ^ A[i];
        }
        return odd;
    }

}
