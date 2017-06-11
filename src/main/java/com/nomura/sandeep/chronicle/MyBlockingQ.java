package com.nomura.sandeep.chronicle;


import java.util.LinkedList;
import java.util.List;

public class MyBlockingQ<T> {
    private final List<T> queue;
    private final int max;

    public MyBlockingQ(int max) {
        this.max = max;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(T ele) throws InterruptedException {
        while (queue.size() == max) {
            wait();
        }
        queue.add(ele);
        if (queue.size() == 0) {
            notifyAll();
        }
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        /** Notify first and then remove... */
        if (queue.size() == max) {
            notifyAll();
        }
        return queue.remove(0);
    }
}
