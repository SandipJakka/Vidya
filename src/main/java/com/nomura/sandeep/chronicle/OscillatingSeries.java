package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 4/20/2016.
 */
public class OscillatingSeries {

    public static void main(String[] args) {
        OscillatingSeries test3 = new OscillatingSeries();
        //int[] A = new int[]{5, 0, -2, 6, 3, 4, 4, -3, 5};
        //int[] A = new int[]{5, 0, 2, 1, 9, 7, 11, 6, 25, 19};
        //int[] A = new int[]{1, 2, 3, 5, 6, 7, 8, 9};
        // int [] A = new int[] {-1,-2,0,-10,-9,-25};
        int[] A = new int[]{5, 0, 7, 2, 9, 6, 6, 6, 7, 3, 29, 22, 27, 21, 29, 22};
        //int[] A = new int[]{3, 3, 3};
        System.out.println("=====>" + test3.solution(A));
    }

    public int solution(int[] A) {
        if (A.length == 0) {
            return -1; //Invalid
        }
        int startIndex = 0;
        int endIndex = -1;
        boolean prevInc = !(A[0] > A[1]);
        boolean prevDec = !(A[0] < A[1]);
        boolean prevEqual = (A[0] == A[1]);
        int max = 0;
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {  //Greater
                if (prevInc || prevEqual) {
                    //Going good
                    prevDec = true;
                } else {
                    endIndex = i;
                    max = Integer.max(max, (endIndex - startIndex));
                    System.out.println(" startIndex :" + startIndex + " , endIndex :" + endIndex + " , max : " + max);
                    startIndex = i; //Reset
                    endIndex = 0;
                }
            } else if (A[i] < A[i + 1]) { //
                if (prevDec || prevEqual) {
                    //Going good
                    prevInc = true;
                } else {
                    endIndex = i;
                    max = Integer.max(max, (endIndex - startIndex));
                    System.out.println(" startIndex :" + startIndex + " , endIndex :" + endIndex + " , max : " + max);
                    startIndex = i; //Reset
                    endIndex = 0;
                }
            } else {
                endIndex = i;
                max = Integer.max(max, (endIndex - startIndex));
                System.out.println(" startIndex :" + startIndex + " , endIndex :" + endIndex + " , max : " + max);
                startIndex = i; //Reset
                endIndex = 0;
                prevEqual = true;
                prevDec = false;
                prevInc = false;
            }
        }
        if (endIndex == -1 || endIndex == 0) { //series still going
            endIndex = A.length - 1;
            max = Integer.max(max, (endIndex - startIndex));
            System.out.println(" startIndex :" + startIndex + " , endIndex :" + endIndex + " , max : " + max);
        }

        return max > 1 ? max : 0;
    }

}