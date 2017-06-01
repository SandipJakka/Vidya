package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 4/8/2016.
 */
public class FrogJump {
    public static void main(String[] args) {
        FrogJump fp = new FrogJump();
        int[] A = new int[]{ 1,3, 1,4, 2, 3,5,4};
        System.out.println("===>"+ fp.solution(7,A));
    }

    public int solution(int X, int[] A) {
        if (A.length == 0 || X <0) {
           return -1;
        }
        int ret = -1;

        for (int i = 0; i < A.length; i++) {
            if (X == A[i]){
                ret =i;
                break;
            }
        }

        return ret;

    }
}
