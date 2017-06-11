package com.nomura.sandeep.chronicle;

public class MyLinkedBlockingDeque<T> {

    public MyLinkedBlockingDeque(int max) {
        this.max = max;
    }

    public static class Node<T> {
        private Node previous;
        private final T data;
        private Node next;

        public Node(Node previous, T data, Node next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

    }

    private Node<T> head;
    private Node<T> tail;

    private final int max;
    private int currentSize = 0;

    public synchronized void addFront(T element) throws InterruptedException {
        while (currentSize == max) {
            wait();
        }
        currentSize++;
        Node<T> nd = new Node<>(null, element, null);
        if (head == null) {  /** no elements */
            head = nd;
            tail = nd;
        } else {
            head.previous = nd;
            nd.next = head;
            head = nd;
        }

        if (currentSize == 0) {
            notifyAll();
        }
    }


    public synchronized void addLast(T element) throws InterruptedException {
        while (currentSize == max) {
            wait();
        }
        currentSize++;
        Node<T> nd = new Node<>(null, element, null);
        if (head == null) {  /** no elements */
            head = nd;
            tail = nd;
        } else {
            tail.next = nd;
            nd.previous = tail;
            tail = nd;
        }
        if (currentSize == 0) {
            notifyAll();
        }
    }

    public synchronized T removeAtFront() throws InterruptedException {
        T data = null;
        while (currentSize == 0) {
            wait();
        }
        data = head.getData();
        if (currentSize == 1) { /** Only 1*/
            head = null;
            tail = null;
        } else {
            Node<T> toDelete = head;
            head = head.next;
            head.previous = null;
            toDelete = null; /** GC */
        }
        currentSize--;
        if (currentSize == max) {
            notifyAll();
        }
        return data;
    }

    public synchronized T removeAtEnd() throws InterruptedException {
        T data = null;
        while (currentSize == 0) {
            wait();
        }
        data = head.getData();
        if (currentSize == 1) { /** Only 1*/
            head = null;
            tail = null;
        } else {
            Node<T> toDelete = tail;
            tail = tail.previous;
            tail.next = null;
            toDelete = null; /** GC */
        }
        currentSize--;
        if (currentSize == max) {
            notifyAll();
        }
        return data;
    }


    public void print() {
        Node<T> tmpHead = head;
        while (tmpHead != null) {
            System.out.println("Element :" + tmpHead.getData());
            tmpHead = tmpHead.next;
        }
    }


    public static void main(String[] args) {
        MyLinkedBlockingDeque<String> q = new MyLinkedBlockingDeque<>(10);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    q.addLast("a");
                    q.addLast("great");
                    q.addLast("country");
                    q.addFront("is");
                    q.addFront("India");


                    q.print();

                    System.out.println("=================================");

                    q.removeAtEnd();
                    q.removeAtFront();

                    q.print();

                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
        });

        th.start();
    }

}
