package com.nomura.sandeep.chronicle.concurrency;


import java.util.concurrent.*;
import java.util.concurrent.atomic.DoubleAccumulator;

public class PositionAggregate {
    ConcurrentMap<Integer, DoubleAccumulator> positionPerAccount = new ConcurrentHashMap<>(64, 0.9f, 16);

    public void updatePoistionForAccount(int account, Double newPos) {
        positionPerAccount.computeIfAbsent(account, a -> new DoubleAccumulator(Double::sum, 0.0)).accumulate(newPos);
    }


    public void print() {
        System.out.printf("%d %n", positionPerAccount.size());
        for (Integer key : positionPerAccount.keySet()) {
            System.out.printf("Position for %d is %f %n", key, positionPerAccount.get(key).doubleValue());
        }
    }


    public static void main(String[] args) {
        PositionAggregate po = new PositionAggregate();
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        try {
            int[] accts = new int[]{10, 11};
            for (int i = 0; i < 1000_000; i++) {
                int account = (i & 1) == 0 ? accts[0] : accts[1];
                executorService.submit(() -> po.updatePoistionForAccount(account, 2.0));
            }
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        po.print();
    }
}