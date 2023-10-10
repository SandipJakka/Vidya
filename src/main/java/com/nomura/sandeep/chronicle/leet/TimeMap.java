package com.nomura.sandeep.chronicle.leet;

import java.util.*;

class TimeMap {

    private final Map<String, Map<Integer, String>> map;
    private final Map<String, List<Integer>> list;

    /**
     * Initialize your data structure here.
     */
    public TimeMap() {
        map = new HashMap<>();
        list = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        Map<Integer, String> val = map.get(key);
        List<Integer> listVal = list.get(key);
        if (val == null) {
            val = new HashMap<>();
            listVal = new ArrayList<>();
        }
        val.put(timestamp, value);
        listVal.add(timestamp);
        map.put(key, val);
        list.put(key, listVal);
    }

    public String get(String key, int timestamp) {
        Map<Integer, String> val = map.get(key);
        if (val == null) {
            return "";
        } else if (val.containsKey(timestamp)) {
            return val.get(timestamp);
        } else {
            List<Integer> allTimeStamps = list.get(key);
            int index = Collections.binarySearch(allTimeStamps, timestamp);
            int valueAtIndx = (-1 * (index)) - 2;
            if (valueAtIndx >= 0) {
                return val.getOrDefault(allTimeStamps.get(valueAtIndx), "");
            } else {
                return "";
            }
        }
    }


    public static void main(String[] args) {
        TimeMap kv = new TimeMap();
        kv.set("foo", "bar", 1); // store the val "foo" and value "bar" along with timestamp = 1
        System.out.printf("%s \n ", kv.get("foo", 1));  // output "bar"
        System.out.printf("%s \n ", kv.get("foo", 3)); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
        kv.set("foo", "bar2", 4);
        System.out.printf("%s \n ", kv.get("foo", 4)); // output "bar2"
        System.out.printf("%s \n ", kv.get("foo", 5)); //output "bar2"


//        ["TimeMap","set","set","get","get","get","get","get"]
//[[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]

//        kv.set("love", "high", 10);
//        kv.set("love", "low", 20);
//        System.out.printf("%s \n ", kv.get("love", 5)); // output "bar2"

    }
}