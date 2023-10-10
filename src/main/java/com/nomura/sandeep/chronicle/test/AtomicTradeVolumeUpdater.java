package com.nomura.sandeep.chronicle.test;


import java.util.concurrent.atomic.AtomicReference;

public class AtomicTradeVolumeUpdater {
    private static class TradeVolume {
        private final double price;
        private final double quantity;

        private TradeVolume(double price, double quantity) {
            this.price = price;
            this.quantity = quantity;
        }
    }

    private final AtomicReference<TradeVolume> currentTradeVol =
            new AtomicReference<>(new TradeVolume(0.0d, 0.0d));


    void update(double newPrice, double newQuantity) {
        while (true) {
            TradeVolume old = currentTradeVol.get();
            if (currentTradeVol.compareAndSet(old, new TradeVolume(newPrice, newQuantity))) {
                return;
            }
        }
    }
}
