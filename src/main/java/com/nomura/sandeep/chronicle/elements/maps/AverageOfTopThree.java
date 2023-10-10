package com.nomura.sandeep.chronicle.elements.maps;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * 13.12
 */
public class AverageOfTopThree {

    private static class MinHeap {
        private final PriorityQueue<Integer> minHeap = new PriorityQueue<>(3);

        public void add(int val) {
            if (minHeap.size() >= 3) {
                if (val > minHeap.peek()) {
                    minHeap.poll();
                } else {
                    return;
                }
            }
            minHeap.add(val);
        }
        public int sum() {
            return minHeap.stream().reduce(0, (x, y) -> x + y);
        }
        public int size() {
            return minHeap.size();
        }
    }


    private static class Record {
        public final String studentId;
        public final int score;

        Record(String studentId, int score) {
            this.studentId = studentId;
            this.score = score;
        }
    }


    public int avgOfTopThree(Record[] records) {
        Map<String, MinHeap> studentToTopThreeScores = Maps.newHashMap();
        int max = 0;
        for (Record record : records) {
            MinHeap minHeap;
            if (studentToTopThreeScores.containsKey(record.studentId)) {
                minHeap = studentToTopThreeScores.get(record.studentId);
                minHeap.add(record.score);
                max = minHeap.size() == 3 ? Math.max(max, minHeap.sum()) : max;
            } else {
                minHeap = new MinHeap();
                minHeap.add(record.score);
            }
            studentToTopThreeScores.put(record.studentId, minHeap);
        }
        return (max / 3);
    }


    public static void main(String[] args) {
        AverageOfTopThree topThree = new AverageOfTopThree();
        Record record1 = new Record("abc", 100);
        Record record2 = new Record("xyz", 95);
        Record record3 = new Record("xyz", 10);
        Record record4 = new Record("def", 100);
        Record record5 = new Record("xyz", 98);
        Record record6 = new Record("def", 35);
        Record record7 = new Record("abc", 87);
        Record record8 = new Record("abc", 78);
        Record record9 = new Record("abc", 55);

        Record[] records = new Record[]{record1, record2, record3, record4, record5, record6, record7, record8, record9};

        System.out.println(topThree.avgOfTopThree(records));
    }
}
