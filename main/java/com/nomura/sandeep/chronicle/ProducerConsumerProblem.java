package com.nomura.sandeep.chronicle;


import java.util.Random;

public class ProducerConsumerProblem {
    private static final class IntBuffer {
        private int index = 0;
        private int[] buffer = new int[8];

        public synchronized void add(int ele) throws InterruptedException {
            while (index == buffer.length - 1) {
                wait();
            }
            System.out.println("Added ele = [" + ele + "]");
            buffer[++index] = ele;
            if (buffer.length == 0) {
                notifyAll();
            }
        }

        public synchronized int remove() {
            while (index <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int ret = buffer[index];
            System.out.println("Removed :" + ret);
            --index;
            notifyAll();
            return ret;
        }
    }

    final class Prd extends Thread {
        private final IntBuffer buf;

        Prd(IntBuffer buf) {
            this.buf = buf;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                Random random = new Random();
                try {
                    buf.add(random.nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    final class Con extends Thread {

        private final IntBuffer buf;

        Con(IntBuffer buf) {
            this.buf = buf;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                //    Random random = new Random();
                buf.remove();
            }
        }
    }

    ;

    public void fun() {
        IntBuffer buf = new IntBuffer();
        Prd prd = new Prd(buf);
        Con con = new Con(buf);
        prd.start();
        con.start();
    }

    public static void main(String[] args) {
        new ProducerConsumerProblem().fun();
    }
}
