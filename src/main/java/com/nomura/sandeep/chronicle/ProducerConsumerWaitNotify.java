package com.nomura.sandeep.chronicle;

public class ProducerConsumerWaitNotify {

    Thread task;
    Object mon = new Object();

    public static void main(String[] args) throws InterruptedException {

        ProducerConsumerWaitNotify t = new ProducerConsumerWaitNotify();
        t.waitForOtherTaskToCompleteAndContinue();
    }

    public void waitForOtherTaskToCompleteAndContinue() throws InterruptedException {
        createTask();
        synchronized (task) {
            task.start();
            task.wait();
        }

    }

    public void createTask() {
        Runnable r2 = () -> {
            synchronized (mon) {
                //Do something
                try {
                    Thread.sleep(10000);
                    System.out.println("Completed the task to done....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.notify();

            }
        };
        task = new Thread(r2);
    }
}
