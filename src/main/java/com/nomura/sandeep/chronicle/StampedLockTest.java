package com.nomura.sandeep.chronicle;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private final StampedLock stampedLock = new StampedLock();
    Integer mutex = -1;

    public static void main(String[] args) {
        StampedLockTest test = new StampedLockTest();
        try {
            Runnable r = () -> test.testOptimisticRead();
            Runnable r2 = () -> {
                try {
                    test.writeToSharedVariable(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(r).start();
            Thread.sleep(100);
            new Thread(r2).start();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void testOptimisticRead() {
        long optimLock = stampedLock.tryOptimisticRead();
        try {
            taskToDoWithReadLock_Optimistically();
            if (!stampedLock.validate(optimLock)) {
                long readL = stampedLock.readLock();
                try {
                    System.out.println("Reading ... " + mutex);
                } finally {
                    stampedLock.unlockRead(readL);
                }
            }
        } catch (InterruptedException exp) {
            System.out.println("Exception : " + exp);
        }
    }

    public void taskToDoWithReadLock_Optimistically() throws InterruptedException {
        synchronized (stampedLock) {
            System.out.println("Locked ...... ");
            Thread.sleep(5000);
        }
        System.out.println("Reading ....withoutLock" + mutex);
        //Thread.currentThread().sleep(5000);
    }

    public void writeToSharedVariable(int someValue) throws InterruptedException {
        // So exception in case we don't get the lock
        long writeLock = stampedLock.tryWriteLock(2, TimeUnit.MICROSECONDS);
        try {
            mutex = someValue;
            System.out.println("Ha.. Ha ... Ha.. New value : " + mutex);
        } catch (Exception exp) {
            System.out.println("Ah exception " + exp);
            throw exp;
        } finally {
            stampedLock.unlockWrite(writeLock);
        }
    }
}
