package com.nomura.sandeep.chronicle.hackerrank.dynamic;

/**
 * Created by sandeep.jakka on 9/4/18.
 */
public class LenghtMax {

    public static int maxSubStringLengthDP(String string) {
        int n = string.length();
        int maxLen = 0;
        int maxSum = 0;
        int[][] sum = new int[n][n];
        // Lower diagonal of matrix is not used (i>j)
        // Filling diagonal values.
        for (int i = 0; i < n; i++) {
            sum[i][i] = string.charAt(i) - '0';
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                int k = len / 2;
                int indexJ = j - k;
                int indexI = indexJ + 1;
                System.out.printf("i=%d, j= %d ,k=%d, len =%d indexI=%d, indexJ=%d %n", i, j, k, len, indexI, indexJ);
                sum[i][j] = sum[i][indexJ] + sum[indexI][j];

                // Update if ‘len’ is even, left and right
                // sums are same and len is more than maxLen
                if (len % 2 == 0 && sum[i][indexJ] == sum[indexI][j] && len > maxLen) {
                    maxLen = len;
                    maxSum = sum[indexI][j];
                }

            }
            System.out.printf("-----------------------%n");
        }

        System.out.printf("maxlen =%d, maxSum = %d %n", maxLen, maxSum);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d  ", sum[i][j]);
            }
            System.out.printf("%n");
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(LenghtMax.maxSubStringLengthDP("9430723"));
    }
}
