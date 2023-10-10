package com.nomura.sandeep.chronicle.leet.premium;

/**
 * There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers
 * enter the store. You are given an integer array customers of length n where customers[i] is the number
 * of the customer that enters the store at the start of the ith minute and all those customers leave after
 * the end of that minute.
 * <p>
 * On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1
 * if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.
 * <p>
 * When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.
 * <p>
 * The bookstore owner knows a secret technique to keep themselves not grumpy
 * for minutes consecutive minutes, but can only use it once.
 * <p>
 * Return the maximum number of customers that can be satisfied throughout the day.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * Example 2:
 * <p>
 * Input: customers = [1], grumpy = [0], minutes = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == customers.length == grumpy.length
 * 1 <= minutes <= n <= 2 * 104
 * 0 <= customers[i] <= 1000
 * grumpy[i] is either 0 or 1.
 */
public class MaxSatisfied {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int happyFrom;
        int happyTo = indexOf(customers, grumpy, minutes);
        if (minutes == 1) {
            happyFrom = happyTo;
        } else {
            happyFrom = happyTo - minutes;
        }
        int satisified = 0;

        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 1 || (i >= happyFrom && i <= happyTo)) {
                satisified += customers[i];
            }
        }
        System.out.println(satisified);
        return satisified;

    }

    int indexOf(int[] customers, int[] grumpy, int mins) {
        int index = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < mins; i++) {
            sum += customers[i];
        }

        if (sum > max && isGrumpyInThePeriod(grumpy, 0, mins - 1)) {
            max = sum;
            index = mins;
        }
        for (int i = mins; i < customers.length; i++) {
            sum = sum + customers[i] - customers[i - mins];
            if (sum > max && isGrumpyInThePeriod(grumpy, i - mins, i)) {
                max = sum;
                index = i;
            }

        }

        return index;
    }

    boolean isGrumpyInThePeriod(int[] grumpy, int from, int end) {
        for (int i = from; i <= end; i++) {
            if (grumpy[i] == 0) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        MaxSatisfied maxSatisfied = new MaxSatisfied();
        maxSatisfied.maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3);
        maxSatisfied.maxSatisfied(new int[]{3, 7}, new int[]{0, 0}, 2);
        maxSatisfied.maxSatisfied(new int[]{4, 10, 10}, new int[]{1, 1, 0}, 2);
        maxSatisfied.maxSatisfied(new int[]{2, 6, 6, 9}, new int[]{0, 0, 1, 1}, 1);
    }
}
