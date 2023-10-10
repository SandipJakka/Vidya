package com.nomura.sandeep.chronicle.elements.graphs;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MapWithPriorityQueue<K, V extends DjikstrasApplication.Node> {
    private final Map<K, V> map = new HashMap<>();
    private final PriorityQueue<V> priorityQueue;

    public MapWithPriorityQueue(Comparator<V> comparator) {
        priorityQueue = new PriorityQueue<>(comparator);
    }

    // O(1)
    public boolean isPresent(K key) {
        return map.containsKey(key);
    }

    // O(1)
    public V get(K key) {
        return map.get(key);
    }

    // O(n log n )
    public void insertOrUpdate(K key, V value) {
        if (map.containsKey(key)) {
            //update
            map.put(key, value);
            priorityQueue.remove(value);
            priorityQueue.add(value);
        } else {
            priorityQueue.add(value);
            map.put(key, value);
        }
    }

    // O(1)
    public V getMinimum() {
        V ele = priorityQueue.poll();
        map.remove(ele.id);
        return ele;
    }

    public boolean isEmpty() {
        return map.isEmpty() && priorityQueue.isEmpty();
    }

    public void print() {
        map.forEach((k, v) -> System.out.printf(" %d --> %s \n", k, v));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }
}