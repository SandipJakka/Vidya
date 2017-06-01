package com.nomura.sandeep.chronicle;

import java.util.concurrent.*;

/**
 * Created by sandeep on 6/10/2016.
 */
public class ThreadPoolExecExceptionHandlingCase {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final ThreadFactory factory;
    private final Runnable workerThread;

    public ThreadPoolExecExceptionHandlingCase() {
        this.factory = r -> {
            Thread t = new Thread(r);
            t.setName("MAIN_EXECUTION_THREAD");
            t.setUncaughtExceptionHandler((t1, e) -> {
                        System.out.println("Exception in the main execution thread- caught here in uncaughtExceptionHandler");
                    }
            );
            return t;
        };
        workerThread = () -> {
            System.out.println("Doing some work and throwing an exception");
            if ( counter ==2){
                try {
                   for (;;){

                   }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                throw new RuntimeException("Some Runtime exception thrown from the main worker thread;");
            }
        };
    }

    public static void main(String[] args) {
        new ThreadPoolExecExceptionHandlingCase().test();
    }
    int counter = 0;
    private void test()  {
        try {
            Future<?> fu =  executor.submit(workerThread);
            fu.get();
        }catch (InterruptedException exp){

        }catch (ExecutionException ex){
            counter++;
            System.out.println("In exec throws");
            ex.printStackTrace();
            if (counter < 3) {
                test();
            }
        }finally {
            executor.shutdownNow();
        }
    }
}
