package com.nomura.sandeep.chronicle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 */
public class MyExchanger<V> {

    private final ThreadLocal<Boolean> first = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };
    private BlockingQueue<QObject> array = new ArrayBlockingQueue<QObject>(2);

    public static void main(String[] args) {
        MyExchanger exchanger = new MyExchanger();

        class ExchangerRunnable implements Runnable {

            MyExchanger exchanger = null;
            Object object = null;

            public ExchangerRunnable(MyExchanger exchanger, Object object) {
                this.exchanger = exchanger;
                this.object = object;
            }

            public void run() {
                try {
                    System.out.println("Runnable called.....");
                    Object previous = this.object;

                    this.object = this.exchanger.exchange(this.object);

                    System.out.println(
                            Thread.currentThread().getName() +
                                    " exchanged " + previous + " for " + this.object
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, "A");

        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, "B");

        Thread t1 = new Thread(exchangerRunnable1);
        t1.setName("Thread -A");
        t1.start();
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Thread t2 = new Thread(exchangerRunnable2);
        t2.setName("Thread -B");
        t2.start();


    }

    public V exchange(V obj) throws InterruptedException {
        QObject qObject = null;
        synchronized (this) {
            if (array.isEmpty()) {
                first.set(true);
            }
            qObject = new QObject(obj);
            array.add(qObject);
        }
        if (first.get()) {
            qObject.doWait();
        } else {
            synchronized (this) {
                array.peek().doNotify();
                return array.remove().get();
            }
        }
        return array.remove().get();
    }


    class QObject {
        private final V one;
        private boolean toWait = false;

        QObject(V one) {
            this.one = one;
        }

        public synchronized void doWait() throws InterruptedException {
            while (!toWait) {
                System.out.println("Waiting" + Thread.currentThread().getName());
                wait();
            }
        }

        public synchronized void doNotify() {
            toWait = true;
            notify();
        }

        public V get() {
            return one;
        }
    }

}
