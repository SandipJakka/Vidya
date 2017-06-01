package com.nomura.sandeep.chronicle;

public class MyCountDownLatch {
    private final int maxPermits;

    private int counter = 0;

    public MyCountDownLatch(int maxPermits) {
        this.maxPermits = maxPermits;
        counter = maxPermits;
    }

    public static void main(String[] args) {
        final MyCountDownLatch latch = new MyCountDownLatch(3);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    System.out.println("Completed.................]");
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Do expensive job

                    Thread.sleep(1250);
                    latch.countDown();
                    latch.countDown();
                    latch.countDown();

                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
        });
        th.setName("Await thread....");
        th.start();
        th2.setName("Countdown thread");
        th2.start();
    }

    public synchronized void await() throws InterruptedException {
        while (counter > 0) {
            System.out.println(Thread.currentThread().getName() + " is waiting");
            wait();
        }
    }

    public synchronized void countDown() {
        if (counter > maxPermits) {
            throw new IllegalStateException("All the countdowns ...dpone. Cant be re-set");
        }
        counter--;
        if (counter == 0) {
            notifyAll();
        }
    }
}
