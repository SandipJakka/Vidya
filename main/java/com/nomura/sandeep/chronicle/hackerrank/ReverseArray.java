package com.nomura.sandeep.chronicle.hackerrank;

/**
 * Created by sandeep on 7/7/2016.
 */
import java.util.Scanner;

public class ReverseArray {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int i=0; i < n; i++){
                arr[i] = in.nextInt();
            }
            in.close();

            for (int i=n-1; i >= 0 ; i--){
                System.out.print(arr[i]);
                System.out.print(" ");
            }
        }

}
