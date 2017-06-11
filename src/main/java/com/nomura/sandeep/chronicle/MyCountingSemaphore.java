package com.nomura.sandeep.chronicle;

public class MyCountingSemaphore {
    private final int max;
    private int count = 0;

    public MyCountingSemaphore(int max) {
        this.max = max;
        this.count = 0;
    }

    public static void main(String[] args) {
        class Sem implements Runnable {
            private final MyCountingSemaphore semaphore;

            Sem(MyCountingSemaphore semaphore) {
                this.semaphore = semaphore;
            }

            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        MyCountingSemaphore semaphore = new MyCountingSemaphore(2);
        Thread t1 = new Thread(new Sem(semaphore));
        Thread t2 = new Thread(new Sem(semaphore));
        Thread t3 = new Thread(new Sem(semaphore));


        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(50);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void acquire() throws InterruptedException {
        while (count == max) {
            System.out.println("Waiting :" + Thread.currentThread().getName());
            wait();
        }
        if (count == 0) {
            notifyAll();
        }
        count++;
        System.out.println("Counting :  " + Thread.currentThread().getName() + "======>" + count);
    }

    public synchronized void release() throws InterruptedException {
        while (count == 0) {
            System.out.println("Waiting Releasing:" + Thread.currentThread().getName());
            wait();
        }
        System.out.println("Counting Releasing:  " + Thread.currentThread().getName() + "======>" + count);
        if (count == max) {
            notifyAll();
        }
        count--;

    }
}
