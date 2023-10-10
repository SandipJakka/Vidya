package com.nomura.sandeep.chronicle.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class WordSearch {
    private boolean exist2(char[][] board, String word) {
        boolean result;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    result = dsf(board, word, i, j, 0);
                    if (result) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dsf(char[][] board, String word, int rowIdx, int colIdx, int charIdx) {
        if (charIdx == word.length()) {
            return true;
        }
        if (rowIdx < 0 || rowIdx >= board.length || colIdx < 0 || colIdx >= board[rowIdx].length) {
            return false;
        }
        if (board[rowIdx][colIdx] != word.charAt(charIdx)) {
            return false;
        }

        boolean result;
        /* keeps track of visited ..*/
        board[rowIdx][colIdx] += 60;
        result = dsf(board, word, rowIdx - 1, colIdx, charIdx + 1)
                || dsf(board, word, rowIdx + 1, colIdx, charIdx + 1)
                || dsf(board, word, rowIdx, colIdx - 1, charIdx + 1)
                || dsf(board, word, rowIdx, colIdx + 1, charIdx + 1);

        /* back to original once visited */
        board[rowIdx][colIdx] -= 60;
        return result;
    }

    private boolean exist(char[][] board, String word) {
        int noOfRows = board.length;
        int noOfCols = board[0].length;
        boolean[][] visited = new boolean[noOfRows][noOfCols];
        List<Cell> start = starting(board, word.charAt(0));
        if (start.size() == 0) {
            return false;
        }
        for (Cell cell : start) {
            visited = init(visited, false);
            visited[cell.row][cell.col] = true;

            int row = cell.row;
            int col = cell.col;
            char[] chars = word.toCharArray();
            int i = 1;
            for (; i < chars.length; i++) {
                char currentChar = chars[i];
                boolean found = false;
                // row -1
                if ((row - 1) >= 0 && !visited[row - 1][col] && currentChar == board[row - 1][col]) {
                    visited[row - 1][col] = true;
                    row = row - 1;
                    found = true;
                }
                // row +1
                if (!found && (row + 1) < noOfRows && !visited[row + 1][col] && currentChar == board[row + 1][col]) {
                    visited[row + 1][col] = true;
                    row = row + 1;
                    found = true;
                }

                if (!found && (col - 1) >= 0 && !visited[row][col - 1] && currentChar == board[row][col - 1]) {
                    visited[row][col - 1] = true;
                    col = col - 1;
                    found = true;
                }

                if (!found && (col + 1) < noOfCols && !visited[row][col + 1] && currentChar == board[row][col + 1]) {
                    visited[row][col + 1] = true;
                    col = col + 1;
                    found = true;
                }

                if (!found) {
                    break;
                }
            }
            // if we have iterated the whole string ( meaning we have found all chars )
            // and the last char is found on the board.
            if (i == chars.length && chars[i - 1] == board[row][col]) {
                return true;
            }

        }
        // we did not find any matches....
        return false;
    }

    private List<Cell> starting(char[][] board, char letter) {
        List<Cell> starting = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == letter) {
                    starting.add(new Cell(row, col));
                }
            }
        }
        return starting;
    }

    private static class Cell {
        private final int row;
        private final int col;

        private Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private boolean[][] init(boolean[][] visited, boolean flag) {
        for (boolean[] booleans : visited)
            Arrays.fill(booleans, flag);
        return visited;
    }

    public static void main(String[] args) {
        WordSearch w = new WordSearch();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        System.out.println(w.exist(board, "ABCCED"));
        System.out.println(w.exist(board, "SEE"));
        System.out.println(w.exist(board, "ABCB"));

        char[][] board1 = new char[][]{
                {'a', 'b'}
        };
        System.out.println(w.exist(board1, "ba"));

        char[][] board3 = new char[][]{
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}

        };

        System.out.println(w.exist(board3, "AAB"));
        System.out.println(w.exist2(board3, "AAB"));
    }
}