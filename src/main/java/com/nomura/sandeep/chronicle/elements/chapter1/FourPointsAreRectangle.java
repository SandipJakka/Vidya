package com.nomura.sandeep.chronicle.elements.chapter1;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Given 4 points (x,y) .. check if they are vertices of a rectangle
 */
public class FourPointsAreRectangle {
    static class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(4, 4);
        Point p2 = new Point(9, 4);
        Point p3 = new Point(4, 10);
        Point p4 = new Point(9, 10);
        Point p5 = new Point(6, 10);
        Point p6 = new Point(11, 10);
        Point p7 = new Point(4, 1);
        Point p8 = new Point(5, 5);
        Point p9 = new Point(9, 5);

        assertTrue(doPointsFormAnRectangle(p1, p2, p3, p4));

        assertFalse(doPointsFormAnRectangle(p1, p2, p3, p5));
        assertFalse(doPointsFormAnRectangle(p1, p2, p3, p6));
        assertFalse(doPointsFormAnRectangle(p1, p2, p3, p7));
        assertFalse(doPointsFormAnRectangle(p1, p2, p8, p7));

        assertTrue(doPointsFormAnRectangle(p1, p2, p8, p9));


    }

    public static boolean doPointsFormAnRectangle(Point p1, Point p2, Point p3, Point p4) {
        boolean pointsOnSameHLine = pointsOnSameHorizontalLine(p1, p2) ||
                pointsOnSameHorizontalLine(p1, p3) ||
                pointsOnSameHorizontalLine(p1, p4);
        boolean pointsOnSameVLine = pointsOnSameVerticalLine(p1, p2) ||
                pointsOnSameVerticalLine(p1, p3) ||
                pointsOnSameVerticalLine(p1, p4);
        return pointsOnSameHLine && pointsOnSameVLine;
    }

    private static boolean pointsOnSameHorizontalLine(Point p1, Point p2) {
        return p1.x == p2.x;
    }

    private static boolean pointsOnSameVerticalLine(Point p1, Point p2) {
        return p1.y == p2.y;
    }

}