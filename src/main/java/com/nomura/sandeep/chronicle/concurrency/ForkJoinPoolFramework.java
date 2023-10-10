package com.nomura.sandeep.chronicle.concurrency;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ForkJoinPoolFramework {

    public void calculatePrimes(int max) throws ExecutionException, InterruptedException {
        Executors.newCachedThreadPool();

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        List<Integer> primes = forkJoinPool.submit(() -> IntStream.range(1, max)
                .parallel()
                .filter(this::isPrime)
                .boxed()
                .peek(System.out::println)
                .collect(toList()))
                .get();


        System.out.printf("Primes : %d %n", primes.size());
//        primes.stream().forEach(System.out::println);


        forkJoinPool.shutdown();
    }

    private boolean isPrime(long number) {
        //System.out.printf("%d,", number);
        return number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .parallel()
                .noneMatch(x -> (number % x) == 0);
    }

    public static void main(String[] args) {
        ForkJoinPoolFramework fp = new ForkJoinPoolFramework();
        try {
            fp.calculatePrimes(1000);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}