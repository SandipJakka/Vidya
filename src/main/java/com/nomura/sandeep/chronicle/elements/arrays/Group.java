package com.nomura.sandeep.chronicle.elements.arrays;

/**
 * Array contains 4 different keys. Arrange them so that the same types elements are together
 */
public class Group {
    enum Color {
        RED, GREEN, BLUE, BLACK
    }

    public static Color[] groupByBruteForce(Color[] input) {
        int red = 0, green = 0, blue = 0, black = 0;

        for (Color color : input) {
            if (Color.RED.equals(color)) {
                ++red;
            } else if (Color.GREEN.equals(color)) {
                ++green;
            } else if (Color.BLACK.equals(color)) {
                ++black;
            } else if (Color.BLUE.equals(color)) {
                ++blue;
            }
        }
        int i = 0;
        for (i = 0; i < red; ++i) {
            input[i] = Color.RED;
        }
        for (; i < (red + green); ++i) {
            input[i] = Color.GREEN;
        }
        for (; i < (red + green + black); ++i) {
            input[i] = Color.BLACK;
        }
        for (; i < input.length - 1; ++i) {
            input[i] = Color.BLUE;
        }
        return input;
    }

    public static Color[] group(Color[] input) {
        /**
         * 0 - red : contains RED
         * red - green : GREEN
         * green - blue : BLUE
         * blue - length : BLACK
         */
        int red = 0, green = 0, blue = 0, black = input.length;

        while ((red + green + blue) < black) {

        }


        return input;
    }

}
