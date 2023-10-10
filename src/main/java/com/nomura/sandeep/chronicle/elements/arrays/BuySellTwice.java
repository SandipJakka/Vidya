package com.nomura.sandeep.chronicle.elements.arrays;

import static org.junit.Assert.assertEquals;

public class BuySellTwice {

    public static void main(String[] args) {
        assertEquals(55, BuySellTwice.maxProfitForBuySellTwice(new int[]{310, 315, 275, 295, 260, 270, 290, 230, 255, 250}));
        assertEquals(10, BuySellTwice.maxProfitForBuySellTwice(new int[]{12, 11, 13, 9, 12, 8, 14, 13, 15}));
        assertEquals(22, BuySellTwice.maxProfitForBuySellTwice(new int[]{12, 11, 13, 15, 9, 12, 11, 8, 14, 13, 15, 17, 8, 21}));
        assertEquals(37, BuySellTwice.maxProfitForBuySellTwice(new int[]{12, 0, 11, 13, 4, 9, 16, 11, 8, 14, 0, 15, 17, 20, 21}));
    }


    public static int maxProfitForBuySellTwice(int[] arr) {
        int min = 0, max = 0, secondMax = 0, maxProfit = 0;
        int current = 0;
        int temp = 0;
        boolean first = true;

        for (int i = 0; i < arr.length; ++i) {
            min = arr[i];
            max = arr[i];
            first = true;
            // iterate as long as we think we are in the same trade.
            while (i < arr.length && (first ? arr[i] >= min : arr[i] > min)) {
                max = Math.max(max, arr[i]);
                ++i;
                first = false;
            }
            --i;
            current = max - min;
            if (current > maxProfit) {
                temp = maxProfit;
                maxProfit = current;
            } else {
                temp = current;
            }
            secondMax = Math.max(secondMax, temp);
        }
        System.out.printf("Max = %d, secondMax = %d \n", maxProfit, secondMax);
        return maxProfit + secondMax;
    }


/*
    public static int maxProfitForBuySellTwice(int[] arr) {
        int min = arr[0], max = arr[0];
        int maxProfit = 0, secondMax = 0;
        int currentMax = 0;
        boolean newTrade = false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                max = arr[i];
                newTrade = true;
            }
            max = Math.max(max, arr[i]);
            currentMax = (max - min);
            if (currentMax > maxProfit) {
                //   secondMax = maxProfit;
                maxProfit = currentMax;
                continue;
            }
            if (newTrade && currentMax > secondMax) {
                secondMax = currentMax;
                newTrade = false;
            }
        }
        System.out.printf("Max = %d, secondMax = %d", maxProfit, secondMax);
        return maxProfit + secondMax;
    }
*/


}