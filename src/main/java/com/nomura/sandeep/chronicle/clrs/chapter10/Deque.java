package com.nomura.sandeep.chronicle.clrs.chapter10;

/**
 * Implement an Deque using a Single array.
 */
public class Deque {
    private final int[] array;
    private final int frontQLen;
    private final int backQLen;


    private int fHead = -1;
    private int fTail = -1;
    private int bHead = 0;
    private int bTail = 0;
    private int frontQSize = 0;
    private int backQSize = 0;

    public Deque(int lenght) {
        if (lenght % 2 == 0) {
            frontQLen = (lenght / 2) - 1;
            backQLen = (lenght / 2);
        } else {
            frontQLen = (lenght / 2) - 1;
            backQLen = (lenght - frontQLen);
        }
        this.array = new int[lenght];
        this.bTail = lenght;
        this.bHead = lenght;
    }

    public static void main(String[] args) {
        Deque deque = new Deque(10);
        deque.enqueFront(1);
        deque.enqueFront(2);
        deque.enqueFront(3);
        deque.enqueFront(4);
        deque.enqueFront(5);

        deque.enqueBack(10);
        deque.enqueBack(9);
        deque.enqueBack(8);
        deque.enqueBack(7);
        deque.enqueBack(6);
        deque.print();

        deque.dequeueFront();
        deque.dequeueFront();
        deque.dequeueFront();
        deque.dequeueFront();
        deque.dequeueFront();

        deque.print();

        deque.dequeueBack();
        deque.dequeueBack();
        deque.dequeueBack();
        deque.dequeueBack();
        deque.dequeueBack();

        deque.print();

        deque.enqueFront(1);
        deque.enqueFront(2);
        deque.enqueFront(3);
        deque.enqueFront(4);
        deque.enqueFront(5);

        deque.enqueBack(10);
        deque.enqueBack(9);
        deque.enqueBack(8);
        deque.enqueBack(7);
        deque.enqueBack(6);

        deque.print();

        deque.enqueFront(21);

    }

    public void enqueFront(int x) {
        if (fTail < frontQLen) {
            fTail++;
        } else {
            if ((frontQSize + 1) < frontQLen) {
                fTail = 0;
            } else {
                throw new RuntimeException("Array full : can't insertBST");
            }
        }
        frontQSize++;
        array[fTail] = x;
    }

    public int dequeueFront() {
        if (fHead < frontQLen) {
            fHead++;
        } else {
            if (frontQSize < frontQLen) {
                fHead = 0;
            } else {
                throw new RuntimeException("Underflow : no-elements present");
            }
        }
        frontQSize--;
        int ret = array[fHead];
        array[fHead] = -1;
        return ret;
    }

    public void enqueBack(int x) {
        if (bTail > backQLen) {
            bTail--;
        } else {
            if ((backQSize + 1) < backQLen) {
                bTail = array.length - 1;
            } else {
                throw new RuntimeException("Array full : can't insertBST");
            }
        }
        backQSize++;
        array[bTail] = x;
    }

    public int dequeueBack() {
        if (bHead > backQLen) {
            bHead--;
        } else {
            if (backQSize < backQLen) {
                bHead = array.length - 1;
            } else {
                throw new RuntimeException("Underflow : no-elements present");
            }
        }
        backQSize--;
        int ret = array[bHead];
        array[bHead] = -1;
        return ret;
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.printf("\n fHead : %s, fTail : %s \n", fHead, fTail);
        System.out.printf("bHead : %s, bTail : %s \n", bHead, bTail);
    }

}