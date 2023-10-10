package com.nomura.sandeep.chronicle.dp;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Change {

    private int minNumOfCoinsToEqualTheSum(int[] available, int amount) {
//        Arrays.sort(available);
        // int end = indexOfLargestCoin(available, amount);
//        return minNumberOfCoinsBrute(available, 0, end + 1, amount, new ArrayList<Integer>());
        List<Integer> t = Arrays.stream(available)
                .boxed()
                .collect(Collectors.toList());
        return minCoins(t, t.size(), amount);
    }

    private int minCoins(List<Integer> availableCoins, int end, int amountToPay) {
//        System.out.printf("coins={%s}, end = %d, amount = %d \n", Arrays.toString(available), end, amount);
        if (amountToPay == 0) {
            return 0;
        }
        if (availableCoins.contains(amountToPay)) {
            return 1;
        }
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < end; i++) {
            if (availableCoins.get(i) <= amountToPay) {
                int temp = minCoins(availableCoins, end, amountToPay - availableCoins.get(i));
                if (temp + 1 < min) {
                    min = temp + 1;
                }
            }
        }
        return min;
    }


    private int minCoinsDp(int[] coins, int amount) {
        // indicates the min number of coins required for each amount.
        int[] mins = new int[amount + 1];
        // indicates which coin denomination.
        int[] picks = new int[amount + 1];

        //init the arrays
        Arrays.fill(mins, Integer.MAX_VALUE);
        mins[0] = 0;

        Arrays.fill(picks, -1);
        picks[0] = 0;


        for (int coin = 0; coin < coins.length; coin++) {
            for (int tot = 1; tot < amount + 1; tot++) {
                // T[i] = min (T[i], 1+ T[tot - coins[i]);
                if (coins[coin] <= tot) {
                    if (1 + mins[tot - coins[coin]] < mins[tot]) {
                        mins[tot] = 1 + mins[tot - coins[coin]];
                        picks[tot] = coin;
                    }
                }
            }
        }
        for (int i = 1; i < mins.length; i++) {
            System.out.printf("coin = %d , min = %d , coins = %s \n", i, mins[i], Arrays.toString(coinsPickedForCount(picks, coins, i, mins)));
        }
//        System.out.printf(" mins = {%s} \n", Arrays.toString(mins));
//        System.out.printf(" mins = {%s} \n", Arrays.toString(picks));
//        System.out.printf("picks for %s \n ", Arrays.toString(coinsPickedForCount(picks, coins, amount, mins)));

        return mins[amount];

    }

    int[] coinsPickedForCount(int[] picks, int[] coins, int amount, int[] mins) {
        int[] pickedCoins = new int[mins[amount]];
        int i = 0;
        while (amount > 0) {
            pickedCoins[i] = coins[picks[amount]];
            amount = amount - pickedCoins[i];
            ++i;
        }
        return pickedCoins;
    }

   /* private List<Integer> minNumberOfCoinsBrute(int[] available, int start, int end, int amount, List<Integer> list) {
        System.out.printf("avail = %s, start = %d, end = %d, amount = %d  \n", Arrays.toString(available), start, end, amount);
        if (end < start) {
            return list;
        }

        int minLenght = Integer.MAX_VALUE;
        List<Integer> minLenArray = new ArrayList<>();

        int current = available[start];
        int count = (amount / current);
        int remainder = (amount % current);

        int[] holder = new int[count];
        Arrays.fill(holder, current);

        List<Integer> t = Arrays.stream(holder).boxed().collect(Collectors.toList());
        if (remainder == 0) {
            if (t.size() < minLenght) {
                minLenght = holder.length;
                minLenArray = t;
                System.out.printf("minLen = %s \n ", minLenArray);
            }
            t = minNumberOfCoinsBrute(available, start + 1, end, amount, t);
        } else {
            for (int j = 0; j < start; j++) {
                List<Integer> temp = Arrays.stream(holder).boxed().collect(Collectors.toList());
                List<Integer> temp2 = minNumberOfCoinsBrute(available, 0, start, remainder, t);
                temp.addAll(temp2);
                if (temp.size() < minLenght) {
                    minLenght = holder.length;
                    minLenArray = temp;
                    System.out.printf("minLen = %s \n ", minLenArray);
                }
            }
        }
        return minLenArray;
    }
*/


    private int indexOfLargestCoin(int[] available, int amount) {
        if (amount > available[available.length - 1]) {
            return available.length - 1;
        }
        int mid, start = 0, end = available.length - 1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            /**  {1,2,5,10,12,50,100,200,1000}  amount = 65 **/
            if (amount >= available[mid] && amount < available[mid + 1]) {
                System.out.printf("Array={%s}, mid = %d, midElement = %d , amount = %d\n", Arrays.toString(available), mid, available[mid], amount);
                return mid;
            }
            if (amount > available[mid] && amount > available[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        throw new RuntimeException("Something bad happened...");
    }

    public static void main(String[] args) {
        int[] available = new int[]{1, 2, 5, 10, 12, 50, 100, 200, 100};
        Change change = new Change();

//        int min = change.minNumOfCoinsToEqualTheSum(available, 35);

        int min = change.minCoinsDp(available, 65);

        System.out.printf("min = %s \n ", min);
    }
}