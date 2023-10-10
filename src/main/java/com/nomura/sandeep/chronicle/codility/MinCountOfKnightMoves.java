package com.nomura.sandeep.chronicle.codility;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

/**
 * The knight is the piece in the game of chess that, in one turn, can move two squares
 * horizontally and one square vertically or two squares vertically and one square horizontally.
 * <p>
 * An infinite chessboard is given. All of its squares are empty except for the square with coordinates
 * (0, 0), where a knight stands.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int A, int B); }
 * <p>
 * that, given two numbers A and B, returns the minimum number of turns required for
 * the knight to move from square (0, 0) to square (A, B). The function should return −1 if the
 * knight cannot reach the given square. The function should return −2 if the required number of turns
 * exceeds 100,000,000.
 * <p>
 * For example, given A = 4 and B = 5 the function should return 3, because the knight requires three
 * turns to move from square (0, 0) to square (4, 5):
 * <p>
 * in the first turn the knight moves from square (0, 0) to square (2, 1);
 * in the second turn the knight moves from square (2, 1) to square (3, 3);
 * in the third turn the knight moves from square (3, 3) to square (4, 5).
 * Please notice that it is impossible to reach square (4, 5) from (0, 0) in less than 3 moves.
 * <p>
 * <p>
 * In another example, given A = 1 and B = 0 the function should also return 3:
 * <p>
 * in the first turn the knight moves from square (0, 0) to square (2, 1);
 * in the second turn the knight moves from square (2, 1) to square (0, 2);
 * in the third turn the knight moves from square (0, 2) to square (1, 0).
 * <p>
 * Assume that:
 * <p>
 * A and B are integers within the range [−100,000,000..100,000,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(1);
 * expected worst-case space complexity is O(1).
 */
public class MinCountOfKnightMoves {

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int A, int B) {
        int count = 0;
        Map<Point, Integer> memo = new HashMap<>();
        count = helper(new Point(0, 0), A, B, count, memo);
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private int helper(Point point, int endX, int endY, int count, Map<Point, Integer> memo) {
        if (point.x == endX && point.y == endY) {
            return count;
        }
        if (point.x > endX || point.y > endY || point.x < 0 || point.y < 0) {
            return Integer.MAX_VALUE;
        }
        count += 1;
        Point point1 = new Point(point.x + 1, point.y + 2);
        Point point2 = new Point(point.x + 2, point.y + 1);
        Point point3 = new Point(point.x - 1, point.y - 2);
        Point point4 = new Point(point.x - 2, point.y - 1);
        int one = memo.containsKey(point1) ? memo.get(point1) : helper(point1, endX, endY, count, memo);
        int two = memo.containsKey(point2) ? memo.get(point2) : helper(point2, endX, endY, count, memo);
        int three = memo.containsKey(point3) ? memo.get(point3) : helper(point3, endX, endY, count, memo);
        int four = memo.containsKey(point4) ? memo.get(point4) : helper(point4, endX, endY, count, memo);

        return min(min(min(one, two), three), four);

        /* int fir = helper(point + 1, startY + 2, endX, endY, count);
        int sec = helper(startX + 2, startY + 1, endX, endY, count);
        if (startX > 0 && startY > 0) {
            int three = helper(startX - 1)
        }
        return fir == -1 ? sec : (sec == -1 ? fir : Math.min(fir, sec));*/
    }


    public static void main(String[] args) {
        MinCountOfKnightMoves m = new MinCountOfKnightMoves();
        System.out.println(m.solution(4, 5));
        System.out.println(m.solution(4, 5));
    }
}
