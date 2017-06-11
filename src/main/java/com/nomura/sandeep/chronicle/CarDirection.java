package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 4/8/2016.
 */
public class CarDirection {

    public static void main(String[] args) {
        CarDirection carDirection = new CarDirection();
        int A[] = new int[]{0, 1, 0, 1, 1};
        System.out.println("===>" + carDirection.solution(A));
    }

    public int solution(int[] A) {
        int[] ones = new int[A.length];
        int[] zeros = new int[A.length];

        if (A.length <= 1) {
            return 0;
        }
        int z = 0;
        int o = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                zeros[z] = i;
                z++;
            } else if (A[i] == 1) {
                ones[o] = i;
                o++;
            }
        }
        int count = 0;
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < o; j++) {
                if (zeros[i] < ones[j]) {
                    count++;
                }
            }
        }
        return count;
    }
   /* public int solution(int[] A) {
        if (A.length <= 1) {
            return 0;
        }
        int count=0;
        for (int i=0; i<A.length;i++){
            count=count+A[i];
        }
        return count ;
    }*/

}
