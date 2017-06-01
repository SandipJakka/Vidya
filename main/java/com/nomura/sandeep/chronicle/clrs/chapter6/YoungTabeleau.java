package com.nomura.sandeep.chronicle.clrs.chapter6;

/**
 * m x n matrix
 * <p>
 * . rows sorted left ==> right
 * . columns sorted top ==> bottom
 * . contains 'non-existent' value ( infinity ) in some A[i,j]
 * . r <= m x n elements
 * <p>
 * EXTRACT-MIN( A ) in O ( m + n ) time
 */
public class YoungTabeleau {

    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 7, 9}, {8, 10, 11}, {16, 21, 36}};

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(" " + A[i][j]);
            }
            System.out.println(" ");
        }
        YoungTabeleau y = new YoungTabeleau();
        System.out.println("==>" + y.extractMin(A));


        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(" " + A[i][j]);
            }
            System.out.println(" ");
        }
    }

    private static int right(int[][] A, int col) {
        int colLenght = A[0].length - 1;
        return col + 1 < colLenght ? col + 1 : colLenght;

    }

    private static int bottom(int[][] A, int row) {
        int rowLenght = A.length - 1;
        return row + 1 < rowLenght ? row + 1 : rowLenght;
    }

    private int extractMin(int[][] A) {
        int min = A[0][0];
        A[0][0] = Integer.MAX_VALUE;
        youngTabeleauify(A, 0, 0);
        return min;
    }

    private void youngTabeleauify(int[][] A, int row, int col) {
        int nextRightColIndex = right(A, col);
        int nextBottomRowIndex = bottom(A, row);
        int min = A[row][col];
        int minCol = col;
        int minRow = row;
        if (A[row][nextRightColIndex] < A[row][col]) {
            min = A[row][nextRightColIndex];
            minRow = row;
            minCol = nextRightColIndex;
        }
        if (A[nextBottomRowIndex][col] < min) {
            min = A[nextBottomRowIndex][col];
            minRow = nextBottomRowIndex;
            minCol = col;
        }
        if (minCol != col || minRow != row) {
            int temp = A[row][col];
            A[row][col] = min;
            A[minRow][minCol] = temp;

            youngTabeleauify(A, minRow, minCol);
        }
    }
}