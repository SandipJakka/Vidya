package com.nomura.sandeep.chronicle.elements.arrays;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by sandeep.jakka on 12/9/17.
 */
public class PrintSpiralMatrix {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] arr2 = new int[][]{{1, 2, 3}, {5, 6, 7}, {9, 10, 11}};
        PrintSpiralMatrix p = new PrintSpiralMatrix();
//        p.printSprialMatrix(arr);
        // p.printSprialMatrix(arr2);

        //System.out.println(p.spiralMatrixOut(arr));
        //System.out.println(p.antiSpiralMatrixOut(arr2));

        int M[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19, 20},
        };
     /*   p.printDiagonalMatrix(arr);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        p.printDiagonalMatrix(arr2);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        p.printDiagonalMatrix(M);

        System.out.println("====================================");

        p.zigzag(arr);*/

//        System.out.println(p.findKInSpiral(M, 3));
//        System.out.println(p.findKInSpiral(M, 5));
//        System.out.println(p.findKInSpiral(M, 8));
//        System.out.println(p.findKInSpiral(M, 11));
        System.out.println(p.findKInSpiral(M, 20));
    }

    public void printSprialMatrix(int[][] arr) {
        int x = 0, y = 0, count = 0, rt = 1;
        while (count < arr.length * arr.length) {
            int len = arr.length - rt;
            for (int i = y; i < len; i++) {
                count = process(arr[x][y], count);
                y++;
            }
            for (int i = x; i < len; i++) {
                count = process(arr[x][y], count);
                x++;
            }
            for (int i = y; i > rt - 1; i--) {
                count = process(arr[x][y], count);
                y--;
            }
            for (int i = x; i > rt - 1; i--) {
                count = process(arr[x][y], count);
                x--;
            }

            rt = rt + 1;
            x = x + 1;
            y = y + 1;
        }
    }

    //TODO : fix this guy
    public void zigzag(int[][] arr) {
        // List<Integer> set = Lists.newArrayList();
        int[][] kShift = new int[][]{{0, 1}, {1, -1}, {0, 1}, {-1, 0}};
        int x = 0, y = 0, dir = 0;
        for (int i = 0; i < arr.length * arr[0].length; i++) {
            System.out.println(arr[x][y] + "");
            arr[x][y] = 0;
            int next_x = x + kShift[dir][0];
            int next_y = y + kShift[dir][1];
            if (next_x < 0 || next_x >= arr.length || next_y < 0 || next_y >= arr.length || arr[next_x][next_y] == 0) {
                dir = (dir + 1) % 4;
                next_x = x + kShift[dir][0];
                next_y = y + kShift[dir][1];
            }
            x = next_x;
            y = next_y;
        }

    }


    public int findKInSpiral(int[][] arr, int k) {
        if (arr == null || k < 0 || k > arr.length * arr[0].length) {
            return -1;
        }
        int level = findLevel(arr.length, arr[0].length, k);
        return findK(arr, arr.length + level, arr[0].length + level, level, k);
    }

    private int findLevel(int row, int col, int k) {
        int lvl = 0;
        int ele = noOfElementsInALevel(row, col);
        while (k > ele) {
            row = row - 2 * lvl;
            col = col - 2 * lvl;
            ele = ele + noOfElementsInALevel(row, col);
            ++lvl;
        }
        return lvl;
    }

    int noOfElementsInALevel(int rows, int cols) {
        return (2 * rows) + (2 * cols) - 4;
    }

    private int findK(int[][] arr, int row, int col, int level, int k) {
        int x = -1, y = -1;
        if (k <= col) {
            x = level;
            y = k - 1;
        } else if (k <= (col + row - 1)) {
            x = (k - col);
            y = col - 1;
        } else if (k < (col + row - 1 + row - 1)) {
            x = row - 1;
            y = (row - 1 - (k - (col + row - 1)));
        } else if (k < (col + row - 1 + row - 1 - col - 2)) {
            x = (col - 1 - (k - (col + row - 1 + row - 1)));
            y = level;
        } else {
            System.out.println("Something went wrong!!!");
        }
        System.out.println(String.format("k=%d , (%d,%d)", k, x, y));
        return arr[x][y];

    }


    public List<Integer> spiralMatrixOut(int[][] arr) {
        List<Integer> set = Lists.newArrayList();
        int[][] kShift = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, dir = 0;
        for (int i = 0; i < arr.length * arr.length; i++) {
            set.add(arr[x][y]);
            arr[x][y] = 0;
            int next_x = x + kShift[dir][0];
            int next_y = y + kShift[dir][1];
            if (next_x < 0 || next_x >= arr.length || next_y < 0 || next_y >= arr.length || arr[next_x][next_y] == 0) {
                dir = (dir + 1) % 4;
                next_x = x + kShift[dir][0];
                next_y = y + kShift[dir][1];
            }
            x = next_x;
            y = next_y;
        }

        return set;
    }

    public List<Integer> antiSpiralMatrixOut(int[][] arr) {
        List<Integer> set = Lists.newArrayList();
        int[][] kShift = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int x = 0, y = 0, dir = 0;
        for (int i = 0; i < arr.length * arr.length; i++) {
            set.add(arr[x][y]);
            arr[x][y] = 0;
            int next_x = x + kShift[dir][0];
            int next_y = y + kShift[dir][1];
            if (next_x < 0 || next_x >= arr.length || next_y < 0 || next_y >= arr.length || arr[next_x][next_y] == 0) {
                dir = (dir + 1) % 4;
                next_x = x + kShift[dir][0];
                next_y = y + kShift[dir][1];
            }
            x = next_x;
            y = next_y;
        }

        return set;
    }

    public void printDiagonalMatrix(int[][] arr) {
        int x = 0, y = 0;
        int c = arr[0].length;
        int r = arr.length;

        System.out.println(arr[0][0]);
        // -2 as we already printed the first one [0][0]
        for (int i = 0; i < (r + c - 2); i++) {
            // get the next starting point
            Point next = nextPoint(x, y, r, c);
            //start
            x = next.x;
            y = next.y;
            do {
                System.out.print(arr[x][y] + "   ");
                x = x - 1;
                y = y + 1;
            } while (withRange(x, y, r, c));
            //revert back
            x = next.x;
            y = next.y;
            System.out.println("");
        }
    }

    private boolean withRange(int x, int y, int r, int c) {
        if (x < 0 || x >= r || y < 0 || y >= c) {
            return false;
        }
        return true;
    }

    private Point nextPoint(int x, int y, int r, int c) {
        if (x < r - 1) {
            return new Point(x + 1, 0);
        } else if (y < c - 1) {
            return new Point(r - 1, y + 1);
        }
        return null;
    }

    class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    private int process(int element, int count) {
        System.out.println(element);
        return ++count;
    }
}
