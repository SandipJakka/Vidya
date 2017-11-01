package com.nomura.sandeep.chronicle.codility;

/**
 *
 */
public class BinaryGap {
    public static void main(String[] args) {
        BinaryGap bg = new BinaryGap();

        System.out.println("==========>" + bg.count(6));
    }

    public int count(int input) {
        int count = 0;
        int result = 0;

        if (input < 1) {
            return 0;
        } else {
            boolean shouldCount = false;
            while (input > 0) {
                if ((input & 1) == 1) {
                    result = Integer.max(result, count);
                    count = 0;
                    shouldCount = true;
                } else {
                    if (shouldCount) {
                        count++;
                    }
                }

                input = input >> 1;
            }

        }
        return result;
    }
}
