package com.nomura.sandeep.chronicle;

public class BoundedSemaphore {
    private final int bound;
    private int counter;

    public BoundedSemaphore(int bound, int counter) {
        this.bound = bound;
        this.counter = counter;
    }

    public synchronized void take() throws InterruptedException {
        while (counter == bound) wait();
        counter++;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (counter == 0) wait();
        counter--;
        notify();
    }
}
