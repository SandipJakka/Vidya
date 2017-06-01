package com.nomura.sandeep.chronicle;

public class MyCyclicBarrier {

    private final int mazThreads;
    private int arrivedThreads = 0;

    public MyCyclicBarrier(int mazThreads) {
        this.mazThreads = mazThreads;
    }

    public static void main(String[] args) {
        MyCyclicBarrier barrier = new MyCyclicBarrier(2);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    barrier.await();
                    System.out.println(" [  All Arrived1 :]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    barrier.await();
                    System.out.println(" [  All Arrived2 :]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
        th2.start();
    }

    public synchronized void await() throws InterruptedException {
        arrivedThreads++;
        while (arrivedThreads < mazThreads) {
            System.out.println(Thread.currentThread().getName() + " is waiting");
            wait();
        }
        notifyAll();
    }
}
