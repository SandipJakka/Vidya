package com.nomura.sandeep.chronicle.clrs.chapter6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted lists of n elements each into a single list in O(n log k ) time.
 */
public class KwayMerging {

    public static void main(String[] args) {
        int[] A = new int[]{5, 15, 20};
        int[] A1 = new int[]{3, 7, 9};
        int[] A2 = new int[]{120, 4000, 8000};
        int[] A3 = new int[]{60, 130, 180};
        List<int[]> list = new ArrayList<>();
        list.add(A);
        list.add(A1);
        list.add(A2);
        list.add(A3);

        KwayMerging k = new KwayMerging();
        int[] merged = k.kwayMerge(list);

        for (int i = 0; i < merged.length; i++) {
            System.out.print(" " + merged[i]);
        }


    }

/*
    public int[] kwayMerge1(List<int[]> klists) {
        int size = klists.size() * klists.get(0).length;
        int[] m = new int[size];
        PriorityQueue<Integer> p = new PriorityQueue<>(size, (Comparator<Integer>) (o1, o2) -> o1.intValue() - o2.intValue());

        for (int i = 0; i < klists.size(); i++) {  // m times
            int[] list = klists.get(i);    // n times
            for (int j = 0; j < list.length; j++) {
                p.add(list[j]);   // O(log n)
            }
        }
        int integer = 0;
        while (!p.isEmpty()) {
            m[integer] = p.poll();  // O(log n)
            integer++;
        }
        return m;
    }*/

    /**
     * O (k lon n ) + O( m log n )
     * ==> o ( m log n)
     *
     * @param klists
     * @return
     */
    public int[] kwayMerge(List<int[]> klists) {
        int k = klists.get(0).length;
        int size = klists.size() * k;
        int[] m = new int[size];
        PriorityQueue<Node> p = new PriorityQueue<>(size, (Comparator<Node>) (o1, o2) -> o1.data - o2.data);

        /// O(k log n )
        for (int i = 0; i < klists.size(); i++) {
            int[] list = klists.get(i);    // k times
            p.add(new Node(list[0], i, 0));  // O(log n)
        }

        int counter = 0;

        // m --> total elements
        // m ( log n)
        while (!p.isEmpty()) {
            Node node = p.poll();  // O(log n)
            m[counter] = node.data;
            if (node.index + 1 < k) {
                p.add(new Node(klists.get(node.listNo)[node.index + 1], node.listNo, node.index + 1));  // O (log n)
            }
            counter++;
        }
        return m;
    }

    class Node {
        final int data;
        final int listNo;
        final int index;

        Node(int data, int listNo, int index) {
            this.data = data;
            this.listNo = listNo;
            this.index = index;
        }
    }


}
