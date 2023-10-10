package com.nomura.sandeep.chronicle.clrs.chapter2;

import java.util.Arrays;

/**
 * Created by sandeep.jakka on 1/14/18.
 */
public class CountInversions {

    public static void main(String[] args) {
        CountInversions countInversions = new CountInversions();
        int arr[] = new int[]{1, 20, 6, 4, 5};
//        int arr[] = {8, 4, 2, 1};
        System.out.println(countInversions.countInversions(arr));
        System.out.println("===================================");
        int arr1[] = new int[]{1, 20, 6, 4, 5};
        System.out.println(countInversions.countInversions2(arr1));
    }

    public int countInversions2(int[] arr) {
        int[] temp = new int[arr.length];
        return countInversionsUtil2(arr, temp, 0, arr.length - 1);
    }


    public int countInversions(int[] arr) {
        return countInversionsUtil(arr, 0, arr.length - 1);
    }


    private int countInversionsUtil2(int[] arr, int[] temp, int start, int end) {
        System.out.println(String.format("conversionUtils2(%d,%d)", start, end));
        int mid = 0, inversionCount = 0;
        if (end > start) {
            mid = (start + end) / 2;
            inversionCount = countInversionsUtil2(arr, temp, start, mid);
            inversionCount += countInversionsUtil2(arr, temp, mid + 1, end);

            inversionCount += merge2(arr, temp, start, mid + 1, end);
            System.out.println("final2  : " + Arrays.toString(arr));
        }

        return inversionCount;
    }

    private int countInversionsUtil(int[] arr, int start, int end) {
        System.out.println(String.format("conversionUtils(%d,%d)", start, end));
        int mid = 0, inversionCount = 0;
        if (end > start) {
            mid = (start + end) / 2;
            inversionCount = countInversionsUtil(arr, start, mid);
            inversionCount += countInversionsUtil(arr, mid + 1, end);

            inversionCount += merge(arr, start, mid + 1, end);
            System.out.println("final  : " + Arrays.toString(arr));
        }

        return inversionCount;
    }

    private int merge(int[] arr, int start, int mid, int end) {
        System.out.println("---------------------------------------");
        System.out.println(String.format("merge(%d,%d,%d)", start, mid, end));
        int n1 = mid - start + 1;
        int n2 = end - mid;
        int inversionCount = 0;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0, x = start; i < n1; i++, x++) {
            L[i] = arr[x];
        }

        for (int i = 0, x = mid + 1; i < n2; i++, x++) {
            R[i] = arr[x];
        }
        System.out.println(String.format("n1=%d,n2=%d", n1, n2));
        System.out.println("L : " + Arrays.toString(L));
        System.out.println("R : " + Arrays.toString(R));


        int i = 0, j = 0, k = start;

        while ((i < n1) && (j < n2)) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i += 1;
            } else {
                arr[k] = R[j];
                j += 1;
                inversionCount = inversionCount + (mid - i);
            }
            k++;
        }


      /*  for (; i < n1 && j < n2 && k < end; k++) {

        }*/

        while (i < L.length) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < R.length) {
            arr[k] = R[j];
            j++;
            k++;
        }
        System.out.println("Inv = " + inversionCount);
        System.out.println("---------------------------------------");
        return inversionCount;
    }

    int merge2(int arr[], int temp[], int left, int mid, int right) {
        System.out.println("---------------------------------------");
        System.out.println(String.format("merge2(%d,%d,%d)", left, mid, right));
        System.out.println(Arrays.toString(temp));
        int i, j, k;
        int inv_count = 0;

        i = left; /* i is index for left subarray*/
        j = mid;  /* j is index for right subarray*/
        k = left; /* k is index for resultant merged subarray*/
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];

         /*this is tricky -- see above explanation/diagram for merge()*/
                inv_count = inv_count + (mid - i);
            }
        }

      /* Copy the remaining elements of left subarray
       (if there are any) to temp*/
        while (i <= mid - 1)
            temp[k++] = arr[i++];

      /* Copy the remaining elements of right subarray
       (if there are any) to temp*/
        while (j <= right)
            temp[k++] = arr[j++];

      /*Copy back the merged elements to original array*/
        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        System.out.println("Inv = " + inv_count);
        System.out.println("---------------------------------------");
        return inv_count;
    }
}
