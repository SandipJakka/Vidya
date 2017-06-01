package com.nomura.sandeep.chronicle.test;

/**
 * Created by sandeep on 4/18/2016.
 */
public class Test3 {

    public static void main(String[] args) {
        Test3 test3 = new Test3();
        int[] A = new int[]{5, 0, -2, 6, 3, 4, 4, -3, 5};
        System.out.println(test3.solution(A));
    }

    public int solution(int[] A) {
        if (A.length == 0) {
            return -1; //Invalid
        }

        int maxlenght = 0;
        int len = A.length;
        int prevElem = -1;
        int curr = -1;
        int inc =1;
        int dec = 0;
        int max =0;
        int startIndex=-1;
        int endIndex=0;
        int currDir=-1;
        int prevDir =-1;
        for (int i = 0; i < len; i++) {
            currDir =  prevElem > A[i]? inc : dec;
            if (currDir!=prevDir && i < startIndex){
                startIndex = i;
            }


        }
        return maxlenght;
    }
}
