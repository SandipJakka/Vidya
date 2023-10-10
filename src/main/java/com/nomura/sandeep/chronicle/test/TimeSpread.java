package com.nomura.sandeep.chronicle.test;

import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class TimeSpread {

    private void timeSpread(int numberOfThreads, Runnable action) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(numberOfThreads);
        CountDownLatch ready = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(numberOfThreads);
        ExecutorService executors = Executors.newFixedThreadPool(numberOfThreads);
        try {
            for (int i = 0; i < numberOfThreads; i++) {
                executors.execute(() -> {
                    start.countDown();
                    try {
                        ready.await();
                        action.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        end.countDown();
                    }
                });
            }
            start.await(); //wait for all threads to start
            long startTime = System.nanoTime();
            ready.countDown();
            end.await();
            long endTime = System.nanoTime();
            System.out.println((endTime - startTime));
        } finally {
            executors.shutdown();
        }

    }


    //Generic betterUnion method
    //PECS .. Producer "extends" Consumer "super"...
    private static <E> Set<E> betterUnion(Set<? extends E> set1, Set<? extends E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }


    private static <E> Set<E> union(Set<E> set1, Set<E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public interface UnaryFunction<T> {
        T apply(T arg);
    }

    //Generic singletion pattern
    private static UnaryFunction<Object> ID_FUNC = (arg) -> arg;

    @SuppressWarnings("unchecked")
    private static <T> UnaryFunction<T> idFunction() {
        UnaryFunction<T> idFunc = (UnaryFunction<T>) ID_FUNC;
        return idFunc;
    }

    // recursive type bound
    // read as "any type that can be compared to itself..
    private static <E extends Comparable<? super E>> Optional<E> max(Collection<? extends E> collection) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            //throw new IllegalArgumentException("Passed collection is either null or has no elements");
            return empty();
        }
        E max = null;
        for (E el : collection) {
            if (max == null) {
                max = el;
            }
            if (el.compareTo(max) > 0) {
                max = el;
            }
        }
        return (null == max) ? empty() : of(max);
    }


    // If a parameter type appears only once , then replace it with wildcard

    public static void main(String[] args) {
   /*     AtomicInteger coun = new AtomicInteger();
        TimeSpread ts = new TimeSpread();
        try {
            ts.timeSpread(10, coun::decrementAndGet);

            System.out.println("coun" + coun);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

        Set<Number> setOfNumbers = Sets.newHashSet(2.5, 3.5);
        Set<Double> setOfDoubles = Sets.newHashSet(2.5, 4.5);

        System.out.println(betterUnion(setOfNumbers, setOfDoubles));
//        System.out.println(union(setOfNumbers, setOfDoubles));


        String[] strings = {"Peter", "Paul", "Mary"};
        UnaryFunction<String> names = idFunction();
        for (String s : strings) {
            System.out.println(names.apply(s));
        }

        Number[] numbers = {1, 2.0, 3L};
        UnaryFunction<Number> nums = idFunction();
        for (Number n : numbers) {
            System.out.println(nums.apply(n));
        }

        System.out.println(max(setOfDoubles));
    }
}
