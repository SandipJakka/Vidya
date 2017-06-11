package com.nomura.sandeep.chronicle.codility;

/**
 * Created by sandeep on 11/26/2016.
 */
public class CountDiv {

    public static void main(String[] args) {
        CountDiv c = new CountDiv();
        System.out.println("==>" + c.solution(6, 11, 2));
        System.out.println("==>" + c.solution(8, 11, 7));
        System.out.println("==>" + c.solution(11, 15, 7));
        System.out.println("==>" + c.solution(7, 14, 7));
        System.out.println("==>" + c.solution(7, 21, 7));
        System.out.println("==>" + c.solution(2, 2_000_000_000, 1000_000));
        System.out.println("==>" + c.solution(0, 1, 11));
        System.out.println("==>" + c.solution(11, 14, 2));
        System.out.println("==>" + c.solution(10, 10, 5));
        System.out.println("==>" + c.solution(10, 10, 7));
        System.out.println("==>" + c.solution(10, 10, 20));
//        System.out.println("==>" + (0 % 11) + "," + (1 % 11));
       /* System.out.println("==>" + c.solution(6, 11, 2));
        System.out.println("==>" + c.solution(6, 11, 2));
        System.out.println("==>" + c.solution(6, 11, 2));
        System.out.println("==>" + c.solution(6, 11, 2));
        System.out.println("==>" + c.solution(6, 11, 2));
        System.out.println("==>" + c.solution(6, 11, 2));*/
    }


    public int solution(int A, int B, int K) {
        int div = 0;
        if (A % K == 0) {
            div = div + 1;
        }

        div = (B / K) - (A / K) + div;
        return div;
    }

}
