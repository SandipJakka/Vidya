package com.nomura.sandeep.chronicle;

/**
 * Created by sandeep on 3/27/2016.
 */
public class MyBasicLock {
    private boolean isLocked = false;
    private Thread lockOwner = null;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
        lockOwner = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (lockOwner != Thread.currentThread()) {
            throw new IllegalMonitorStateException("unlock can only be called by the lock owner");
        }
        isLocked = false;
        lockOwner = null;
        notify();
    }
}
