package com.nomura.sandeep.chronicle.leet;


import java.util.Arrays;
import java.util.Comparator;

public class MaxRampWidth {
    public static int maxWidthRampII(int[] A) {
        int n = A.length;
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            b[i] = i;
        }
        Arrays.sort(b, Comparator.comparingInt(i -> A[i]));
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(b));
        for (int x : b) {
            System.out.printf("%d ,", A[x]);
        }
        System.out.printf("\n");

        int mn = n;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, b[i] - mn);
            mn = Math.min(mn, b[i]);
        }
        return ans;
    }


    private static int maxWidthRampI(int[] A) {
        int n = A.length;
        //contains max to the right of each index
        int[] rMax = new int[n];
        rMax[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], A[i]);
        }
//        System.out.println(Arrays.toString(rMax));
//        System.out.println(Arrays.toString(A));


        int left = 0, right = 0;
        int ans = 0;
        while (right < n) {
            while (left < right && A[left] > rMax[right]) {
                left++;
            }
            ans = Math.max(ans, right - left);
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[]{6, 0, 8, 2, 1, 5};
//        System.out.printf("%d \n", MaxRampWidth.maxWidthRampII(a));
        System.out.printf("%d \n", MaxRampWidth.maxWidthRampI(a));
    }
}
