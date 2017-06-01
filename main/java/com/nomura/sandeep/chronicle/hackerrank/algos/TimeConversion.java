package com.nomura.sandeep.chronicle.hackerrank.algos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TimeConversion {
    /*
        public static String timeRegex = "(1[01]|0[1-9]):[0-5][0-9]:[0-5][0-9](\\s)?(?i)(am|pm)";
        public static String timeRegexPM = "(1[01]|0[1-9]):[0-5][0-9]:[0-5][0-9](\\s)?(?i)(pm)";
    */
    private final static SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
    private final static SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm:ssa");

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String time = in.next();
        Date dat = null;
        try {
            dat = inFormat.parse(time);
            System.out.println(displayFormat.format(dat));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
