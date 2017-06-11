package com.nomura.sandeep.chronicle;


import java.util.ArrayList;
import java.util.List;

public class MyThreadPool {
    private final MyBlockingQ<Runnable> tasksHolder;
    private final List<WorkerThread> threadPool;
    private boolean isPoolStopped;

    public MyThreadPool(int maxOfThreads, int maxOfTasks) {
        tasksHolder = new MyBlockingQ<>(maxOfTasks);
        threadPool = new ArrayList<>(maxOfThreads);
        for (int i = 0; i < maxOfThreads; i++) {
            threadPool.add(new WorkerThread(tasksHolder));
        }

        for (Thread t : threadPool) {
            t.start();
        }
    }


    public synchronized void submit(Runnable t) throws InterruptedException {
        if (isPoolStopped) {
            throw new IllegalStateException("Pool already is stopped..");
        }
        tasksHolder.enqueue(t);
    }

    public synchronized void stopPool() {
        if (!isPoolStopped) {
            System.out.println("Shutting down pool ");
            for (WorkerThread th : threadPool) {
                th.stopTask();
            }
            isPoolStopped = true;
        } else {
            System.out.println("Already stopped .....");
        }
    }


    class WorkerThread extends Thread {

        private boolean isTaskStopped = false;
        private final MyBlockingQ<Runnable> tasksHolder;

        WorkerThread(MyBlockingQ<Runnable> tasksHolder) {
            this.tasksHolder = tasksHolder;
        }

        @Override
        public void run() {
            while (!isTaskStopped) {
                try {
                    Runnable r = tasksHolder.dequeue(); /** blocking call.. */
                    r.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void stopTask() {
            System.out.println("Shutting down individual threads...");
            isTaskStopped = true;
            this.interrupt();
        }

    }


    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(5, 3);


        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread #1");
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread #2");
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread #3");
            }
        };
        Runnable r4 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread #4");
            }
        };
        Runnable r5 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread #5");
            }
        };

        try {
            pool.submit(r1);
            pool.submit(r2);
            pool.submit(r3);
            pool.submit(r4);
            Thread.sleep(2);
            pool.stopPool();
            pool.submit(r5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.stopPool();
        }


    }
}
