package com.nomura.sandeep.chronicle.codility;

import java.util.Arrays;
import java.util.Random;

/**
 * John was sitting near to a fireplace in his house, trying to get
 * some warmth from the fire. Fighting his cold at the end of a freezing, short,
 * dark winter day, he started wondering why it always had to be so cold during this season.
 * That was when he came up with an idea.
 * <p>
 * John stated that winter is the initial part of the year in which it is always colder than
 * in the remaining part. This latter part is called 'summer'. Then he assumed that summer is
 * always warmer than winter; that is, any temperature measured in winter is colder than every
 * temperature measured in summer.
 * <p>
 * Then he searched the Internet and found the previous year's meteorological data, which contained
 * the year's temperature measurements. He began to wonder if it might be possible to divide the year
 * into winter and summer so that winter comes before summer and each winter's temperature measurement
 * is smaller than any temperature measured in summer. In case there are many such possible partitions,
 * find the one in which the winter period is as short as possible. (It is quite cold now; there is really no reason for winter to be longer than necessary...)
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] T); }
 * <p>
 * that, given a sequence T of temperature measurements (specified as integer numbers),
 * finds the partition of the year into winter and summer that meets the conditions above and makes
 * winter as short as possible, then returns the length of the winter. Both winter and summer have to be at
 * least one day long. You can assume that there exists at least one partition that satisfies this condition.
 * <p>
 * For example, given:
 * <p>
 * T = [5, -2, 3, 8, 6]
 * the function should return 3, as after partitioning the year into winter: [5, -2, 3] and summer: [8, 6],
 * each winter's measurement is smaller than each summer's temperature.
 * <p>
 * On the other hand, for the following array:
 * <p>
 * T = [-5, -5, -5, -42, 6, 12]
 * the function should return 4. The partition that minimizes the length of the winter is [-5, -5, -5, -42]
 * and [6, 12]. Winter could also have length 5, but the function should return the shortest possible winter.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [2..300,000];
 * each element of array T is an integer within the range [−1,000,000,000..1,000,000,000];
 * There will be at least one correct way to divide the year into winter and summer.
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for
 * input arguments).
 */
public class WinterAndSummer {

    static class MinMax {
        int max;
        int min;

        @Override
        public String toString() {
            return String.format("(%d,%d)", max, min);
        }
    }

    public int solution(int[] T) {
        MinMax[] minMaxArray = new MinMax[T.length];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < T.length; i++) {
            MinMax m = new MinMax();
            max = Math.max(max, T[i]);
            m.max = max;
            minMaxArray[i] = m;
        }

        int min = Integer.MAX_VALUE;
        for (int i = T.length - 1; i > 0; i--) {
            MinMax m = minMaxArray[i];
            min = Math.min(min, T[i]);
            m.min = min;
            minMaxArray[i] = m;
        }

        int count = 1;
        for (int i = 0; i < minMaxArray.length; i++) {
            MinMax m = minMaxArray[i];
            if (m.max < minMaxArray[i + 1].min) {
                break;
            }
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        WinterAndSummer w = new WinterAndSummer();

        Random r = new Random();
        int[] f1 = new int[]{-2025119707, -1986509297, 1385862334, 540898579, 1311230262, 481426321, 1800557879, -207349567, -1784317539, 203899190};
        System.out.println("f1 --> " + w.solution(f1));

        int[] t1 = new int[]{5, -3, 2, 8, 6};
        // (5,5) ( 5,-3), (5,-3), (8,-3) , (8,-3) --> ( max, small)
        // (5,-3) ( 5,-3), (5,2), (8,6) , (8,6) ( --> max, <--- max )
//        System.out.println(Arrays.toString(t1));
        System.out.println("t1 --->" + w.solution(t1));

        int[] t2 = new int[]{-5, -5, -5, -42, 6, 12};
        // (-5, -42) (-5, -42)  (-5,-42 ) (-5, -42) ( 6,6 ) ( 12,12 )
//        System.out.println(Arrays.toString(t2));
        System.out.println("t2 ---->" + w.solution(t2));

        for (int i = 0; i < 5; i++) {
            int[] t3 = r.ints(10, -100, 100).toArray();
            System.out.println(Arrays.toString(t3));
            System.out.println(w.solution(t3));
        }

    }

}
