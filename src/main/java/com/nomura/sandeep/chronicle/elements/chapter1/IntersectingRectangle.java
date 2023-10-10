package com.nomura.sandeep.chronicle.elements.chapter1;

import static junit.framework.Assert.*;

public class IntersectingRectangle {


    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(2, 6, 4, 5);
        Rectangle rectangle2 = new Rectangle(4, 4, 5, 6);
        Rectangle rectangle3 = new Rectangle(2, 6, 2, 5);
        Rectangle rectangle4 = new Rectangle(5, 5, 2, 2);


        assertTrue(doRectanglesOverlap(rectangle1, rectangle2));
        assertTrue(doRectanglesOverlap(rectangle2, rectangle2));
        assertFalse(doRectanglesOverlap(rectangle3, rectangle2));

        assertEquals(new Rectangle(4, 6, 2, 4), overlappingArea(rectangle1, rectangle2));
        assertEquals(rectangle4, overlappingArea(rectangle2, rectangle4));
    }


    public static Rectangle overlappingArea(Rectangle r1, Rectangle r2) {
        /**
         *
         *  x and y value are obvious.
         *
         *  width = Max( w1, w2 ) - Max (x1, x2 ) - Abs ( w1 -w2 )
         *
         *  Max(w1, w2) -> straight line from x-axis to max point
         *  Max( x1, x2) -> Deduct from start of x-axis to the intersecting point
         *
         *
         */

        return new Rectangle(Math.max(r1.x, r2.x),
                Math.max(r1.y, r2.y),
                Math.max(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x) - Math.abs((r1.x + r1.width) - (r2.x + r2.width)),
                Math.max(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y) - Math.abs((r1.y + r1.height) - (r2.x + r2.height))
        );
    }

    /**
     * Main idea is check what doesnt intersect
     */
    public static boolean doRectanglesOverlap(Rectangle r1, Rectangle r2) {
        // Rectange R1 is to right of R2 , R1 to the left of R2
        if (r1ToRightSideOfR2(r1, r2) || r1TotheLeftOfR2(r1, r2)) {
            return false;
        }
        // R1 on top of R2 and R1 below R2
        if (r1OnTopOfR2(r1, r2) || r1BelowR2(r1, r2)) {
            return false;
        }
        //else must be intersecting
        return true;
    }

    private static boolean r1BelowR2(Rectangle r1, Rectangle r2) {
        return r2.y >= r1.y + r1.height;
    }

    private static boolean r1OnTopOfR2(Rectangle r1, Rectangle r2) {
        return r1.y >= r2.y + r2.height;
    }

    private static boolean r1TotheLeftOfR2(Rectangle r1, Rectangle r2) {
        return r2.x >= r1.x + r1.width;
    }

    private static boolean r1ToRightSideOfR2(Rectangle r1, Rectangle r2) {
        return r1.x >= r2.x + r2.width;
    }


    static class Rectangle {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Rectangle)) {
                return false;
            }
            Rectangle that = (Rectangle) obj;
            return (that.x == this.x &&
                    that.y == this.y &&
                    that.width == this.width &&
                    that.height == this.height);
        }

        @Override
        public String toString() {
            return String.format(" x = %d , y = %d , w = %d , h = %d", x, y, width, height);
        }
    }
}
