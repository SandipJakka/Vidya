package com.nomura.sandeep.chronicle.leet.premium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 * <p>
 * Input: s = "A", numRows = 1
 * Output: "A"
 */
public class ZigZagCharacters {

    public static void main(String[] args) {
        convert("PAYPALISHIRING", 4);
    }

    public static  String convert(String s, int numRows) {
        if ("".equals(s.trim()) || numRows < 2) {
            return s;
        }

        Map<Integer, List<Character>> result = new HashMap<>();

        for (int i = 0; i < numRows; i++) {
            result.put(i, new ArrayList<>());
        }

        char[] chars = s.toCharArray();
        int listNo = 0;
        int count = 0;
        boolean fl = false;
        int oneLess = numRows -1;
        while (count < chars.length) {
            char character = chars[count];
            result.get(listNo).add(character);
            if (!fl) {
                listNo++;
                fl = false;
                if (listNo >= oneLess){
                    fl = true;
                    listNo = oneLess;
                }
            }else{
                listNo--;
                fl = true;
                if (listNo <= 0){
                    listNo = 0;
                    fl = false;
                }
            }
            count++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0 ; i < numRows ; i++){
            List<Character> characters = result.get(i);
            characters.forEach(stringBuilder::append);
        }
        String r = stringBuilder.toString();
       // System.out.println(r);

        return r;
    }




    static  String optimizied(String s, int numRows) {
        if ("".equals(s.trim()) || numRows < 2) {
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        int count = Math.min(numRows, s.length());
        for (int i = 0; i < count ;i++){
            list.add(new StringBuilder());
        }
        int currRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()){
            list.get(currRow).append(c);
            if (currRow == 0 || currRow == numRows-1 ){
                goingDown = !goingDown;
            }
            currRow  = currRow + (goingDown ? 1: -1);
        }
        StringBuilder result = new StringBuilder();
        list.stream()
            .map(StringBuilder::toString)
            .map(result::append);

        return result.toString();
    }

    /*private boolean nextRow(int count, int numRows, int listno) {
        int topOrBottom = (numRows - 1 + numRows - 1);
        if (count < topOrBottom) {
            return true;
        }
        if (listno -1 ==0){
            return
        }

        return false;
    }*/
}
