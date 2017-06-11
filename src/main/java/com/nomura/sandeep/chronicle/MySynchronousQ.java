package com.nomura.sandeep.chronicle;

/**
 * Q will only take an element if it an handover to a consumer.
 * Else blocks.
 */
public class MySynchronousQ<T> {
    private Thread reader;
    private Thread writer;
    private T ret;

    public synchronized void enqueue(T obj) throws InterruptedException {
        writer = Thread.currentThread();
        while (reader == null || writer != Thread.currentThread()) {
            System.out.println("obj = [" + obj + "]");
            wait();
        }
        ret = obj;
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        reader = Thread.currentThread();
        notifyAll();
        while (ret == null) {
            System.out.println("dequeue...");
            wait();
        }
        T obj = ret;
        ret = null;
        writer = null;
        reader = null;
        return obj;
    }

    public static void main(String[] args) {
        MySynchronousQ<String> q = new MySynchronousQ<>();

        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    q.enqueue("Sandeep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread rec = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("=========>" + q.dequeue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread sender2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    q.enqueue("Vidya");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread rec2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("==========>" + q.dequeue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        sender.start();
        rec.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sender2.start();
        rec2.start();
    }
}
