package com.nomura.sandeep.chronicle.codility;

/**
 * Created by sandeep on 11/24/2016.
 */
public class FrogJmp {
    public static void main(String[] args) {
        FrogJmp jmp = new FrogJmp();
        System.out.println("====>" + jmp.solution(10, 85, 30));
    }

    public int solution(int X, int Y, int D) {
        Long noOfJumps;
        long distance = (Y - X);
        int addfactor = 0;
        if (!(distance % D == 0)) {
            addfactor = 1;
        }
        noOfJumps = (distance / D);
        return noOfJumps.intValue() + addfactor;

    }

}
