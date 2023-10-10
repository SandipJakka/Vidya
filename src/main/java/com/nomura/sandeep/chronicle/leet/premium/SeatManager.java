package com.nomura.sandeep.chronicle.leet.premium;

import java.util.BitSet;

public class SeatManager {

    private final BitSet seats;
    private final int n;

    public SeatManager(int n) {
        seats = new BitSet(n - 1);
        this.n = n;
    }

    public int reserve() {
        int index = 0;
       // System.out.println(String.format("len = %d : car =%d", seats.length(), seats.cardinality()));
        if (seats.cardinality() < n) {
            index = seats.nextClearBit(0);
            seats.set(index);
        }
        //System.out.println(index + 1);
        return index + 1;

    }

    public void unreserve(int seatNumber) {
        seats.flip(seatNumber-1);
    }

    public void available() {
        int available = (seats.length() - seats.cardinality() - 1);
        // System.out.println("len " + seats.length());
        //System.out.println("set " + seats.cardinality());
        System.out.println(available);

    }

    public static void main(String[] args) {
        SeatManager seatManager = new SeatManager(5);
        seatManager.reserve();
        seatManager.reserve();
        seatManager.unreserve(2);
        seatManager.reserve();
        seatManager.reserve();
        seatManager.reserve();
        seatManager.reserve();
        seatManager.unreserve(5);
        seatManager.available();
    }
}
