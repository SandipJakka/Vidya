package com.nomura.sandeep.chronicle.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class SummationTask extends RecursiveTask<Long> {
    private final int THRESHOLD = 2;
    final long[] array;
    final int lo, hi;

    SummationTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    protected Long compute() {
        //System.out.printf("Thread : %s , hi = %d , lo = %d %n", Thread.currentThread(), hi, lo);
        if (hi - lo < THRESHOLD) {
            long result = 0;
            for (int i = lo; i < hi; ++i) {
                result += array[i];
            }
            return result;
        } else {
            int mid = (lo + hi) >>> 1;
            SummationTask left = new SummationTask(array, lo, mid);
            left.fork();
            SummationTask right = new SummationTask(array, mid, hi);
            long result = right.compute() + left.join();
            return result;
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long[] array1 = LongStream.rangeClosed(1, 100_000_000).toArray();

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        for (int j = 0; j < 100; j++) {
            // long[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            long start = System.currentTimeMillis();
            SummationTask incrementTask = new SummationTask(array1, 0, array1.length);
            forkJoinPool.invoke(incrementTask);
            System.out.printf("th: %d %n", incrementTask.get());
            long end = System.currentTimeMillis();
            System.out.printf(" Threaded : %s %n", (end - start));


            long res = 0;
            start = System.currentTimeMillis();
            for (int i = 0; i < array1.length; i++) {
                res += array1[i];
            }
            end = System.currentTimeMillis();
            System.out.printf("non %d %n", res);
            System.out.printf(" Non-threaded : %s %n", (end - start));
        }
        //
    }
}