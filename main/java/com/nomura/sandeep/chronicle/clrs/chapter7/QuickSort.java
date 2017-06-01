package com.nomura.sandeep.chronicle.clrs.chapter7;

/**
 * Created by sandeep on 12/25/2016.
 */
public class QuickSort {

    private static int counter = 0;

    public static void main(String[] args) {
        QuickSort q = new QuickSort();

       /* int[] A = new int[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1};  ///32
        int[] A1 = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};  //23
        int[] A2 = new int[]{5, 4, 3, 2, 1};  //10
        int[] A3 = new int[]{4, 4, 4, 4, 4}; //10
        int[] A5 = new int[]{1, 2, 3, 4, 5}; //10


        q.quickSort(A, 0, A.length - 1);

        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }
        System.out.println("");
        System.out.println("==> No of iterations  ====>" + counter);
        System.out.println(" Algo complexity " + complexity(A))*/
        ;

        System.out.println(q.maxBits(9));

        for (int i = 0; i < 10; i++) {
            System.out.println(q.randomBetween(0, 10));
        }

       /* int[] A = new int[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
           q.partitionRandomMedianOf3Elements(A, 0, A.length - 1);
        q.quickSort(A, 0, A.length - 1);
        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }*/


    }

    private static double complexity(int[] A) {
        return ((double) A.length * log(A.length, 2));
    }

    static int log(int x, int base) {
        return (int) (Math.log(x) / Math.log(base));
    }

    public void quickSort(int[] A, int start, int end) {
        if (start < end) {
            int pivot = partition(A, start, end);
//            int pivot = partitionRandomMedianOf3Elements(A, start, end);
//            int pivot = partitionWithMidPivot(A, start, end);
            quickSort(A, start, pivot - 1);
            quickSort(A, pivot + 1, end);
        }
    }

    private int partition(int[] A, int start, int end) {
        int pivot = A[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            counter++;
            if (A[j] <= pivot) {
                i = i + 1;
                if (i != j) {
                    swap(A, i, j);
                }
            }
        }
        swap(A, i + 1, end);
        return i + 1;
    }


    private int partitionRandomMedianOf3Elements(int[] A, int start, int end) {
        int one = random(A);
        int two = random(A);
        int three = random(A);
        int median = median(A, one, two, three);
        swap(A, end, median);
        return partition(A, start, end);
    }

    private int median(int[] A, int o, int t, int th) {
        int one = A[o];
        int two = A[t];
        int three = A[th];
        if (one > two && one < three) {
            return o;
        } else if (two > one && two < three) {
            return t;
        }
        return th;
    }

    private int random(int[] A) {
        return randomBetween(0, A.length - 1);
    }

    private int randomBetween(int start, int end) {
        int max = (end - start);
        int maxBits = maxBits(max);
        int rand = randomize(maxBits);
        while (start + rand > end) {
            rand = randomize(maxBits);
        }
        return rand + start;
    }

    private int randomize(int maxBits) {
        int rand = 0;
        for (int i = 1; i <= maxBits; i++) {
            rand = 2 * rand + (Math.random() > 0.5 ? 1 : 0);
        }
        return rand;
    }

    private int maxBits(int max) {
        int ONE = 1;
        int counter = 1;
        while (true) {
            int value = ONE << counter;
            if (value > max) {
                return counter;
            }
            counter++;
        }
    }


    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private int partitionWithMidPivot(int[] A, int start, int end) {
        int pivotIndex = (start + end) / 2;
        int pivot = A[pivotIndex];
        int i = start - 1;
        for (int j = start; j <= end; j++) {
            if (j != pivotIndex) {
                counter++;
                if (A[j] <= pivot) {
                    i = i + 1;
                    swap(A, i, j);
                }
            }
        }
        swap(A, i + 1, end);
        System.out.println("Pivot = " + (i + 1));
        return i + 1;
    }

}
