package com.nomura.sandeep.chronicle;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 0 stop
 * 1 proceed...
 */
public class MatrixPaths {

    public static void main(String[] args) {
        int[][] multi = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

    }

    public Map<String, String> printPaths(int[][] arr) {
        Map<String, String> paths = new LinkedHashMap<>();
        if (arr.length == 0) {
            return paths;
        }
        //int rows = arr.length ;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    continue;
                } else if (arr[i][j] == 1) {

                }
            }
        }
        return paths;
    }
}
