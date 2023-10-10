package com.nomura.sandeep.chronicle.leet.blind.seventyfive;

import com.sun.xml.internal.bind.v2.model.core.ID;

//leetcode problem 121
public class ID02BestTimeToBuySellStocks {
    /**
     * prices = [7, 1, 5, 3 ,  6, 4]
     * b, s
     * s < b ====> move b to s and move s fwd
     *
     * @param prices
     * @return
     */

    // array use 2 pointers.( next to each other )
    public int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }

        int maxProfit = 0;
        int buyIdx = 0;
        int sellIdx = 1;

        for (int i = 0; i < prices.length-1; i++) {
            maxProfit = Math.max(prices[sellIdx]-prices[buyIdx],maxProfit);
            //buyIdx should always point to the lowest value in the array.
            // sellIdx is the runner that needs to run thro' the array
            if (prices[sellIdx] < prices[buyIdx]){
                buyIdx = sellIdx;
            }
            sellIdx++;
        }
        return Math.max(maxProfit, 0);
    }

    public static void main(String[] args) {
        ID02BestTimeToBuySellStocks stocks = new ID02BestTimeToBuySellStocks();
        System.out.println(stocks.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(stocks.maxProfit(new int[]{7, 6,4, 3,1}));
    }
}
