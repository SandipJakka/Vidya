package com.nomura.sandeep.chronicle;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by sandeep on 4/1/2016.
 */
public class MyConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "2");
        System.out.println("args = [" + args + "]");
    }

}
