package com.nomura.sandeep.chronicle.concurrency;


import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

public class NonVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            if (number != 42)
                System.out.println(number);
        }
    }

    public static void main(String[] args) {
        // for (int i = 0; i < 1000_000; i++) {
        new ReaderThread().start();
        ready = true;
        number = 42;
        //}

        Map<String, Date> map = Maps.newHashMap();

        System.out.println(new Date());
        map.put("a", new Date());

        System.out.println(map.get("a").getYear());
        Date d = map.get("a");
        d.setYear(1986);
        System.out.println(map.get("a").getYear());

    }
}