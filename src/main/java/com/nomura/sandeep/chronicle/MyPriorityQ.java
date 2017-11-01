package com.nomura.sandeep.chronicle;

import java.util.LinkedList;
import java.util.List;

public class MyPriorityQ {

    private final List<Integer> orderedList;
    private Integer min;
    private int index;

    public MyPriorityQ() {
        orderedList = new LinkedList<>();
    }

    public static void main(String[] args) {
        int k = 0;
        MyPriorityQ q = new MyPriorityQ();
        while (k < 5) {
            System.out.println("==========================" + k);
            int child1 = q.child1(k);
            int p = q.parent(child1);
            System.out.println("child1 = " + child1 + " parent =" + p);
            int child2 = q.child2(k);
            int p1 = q.parent(child2);
            System.out.println("child2 = " + child2 + " parent =" + p1);
            k++;
        }
        System.out.println("===>" + q.parent(9) + Math.pow(2.0, 3.0));
    }

/*
    private boolean hasParentIndex() {
         ///   return ((int)index/2) ;
    }
*/

    public void add(Integer ele) {
        orderedList.add(ele);
        index++;
/*
        while ( ){

        }
*/
    }

    private int parent(int cIndex) {
        return ((cIndex - 1) / 2);
    }

    private int child1(int pindex) {
        return ((int) (Math.pow(pindex, 2))) + 1;
    }

    private int child2(int pindex) {
        return ((int) (Math.pow(pindex, 2))) + 2;
    }

}
