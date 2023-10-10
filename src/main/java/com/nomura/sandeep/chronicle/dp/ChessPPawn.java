package com.nomura.sandeep.chronicle.dp;


import java.util.HashSet;
import java.util.Set;

public class ChessPPawn {

    private class ChessSquare {
        final int row;
        final int col;

        private ChessSquare(int row, int y) {
            this.row = row;
            this.col = y;
        }
    }


    private Set<ChessSquare> validSquares(ChessSquare from, int maxRows, int maxCols) {
        Set<ChessSquare> valid = new HashSet<>(8);

        if (from.row - 2 >= 0 && from.col - 1 >= 0) {
            valid.add(new ChessSquare(from.row - 2, from.row - 1));
        }
        if (from.row - 2 >= 0 && from.col + 1 < maxCols) {
            valid.add(new ChessSquare(from.row - 2, from.col + 1));
        }
        if (from.row - 1 >= 0 && from.col - 2 >= 0) {
            valid.add(new ChessSquare(from.row - 1, from.col - 2));
        }
        if (from.row - 1 >= 0 && from.col - 2 >= 0) {
//            valid.
        }


        return valid;
    }
}
