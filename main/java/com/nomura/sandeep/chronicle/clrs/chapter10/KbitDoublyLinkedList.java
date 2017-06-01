package com.nomura.sandeep.chronicle.clrs.chapter10;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Implement doubly linked lists using only one pointer value x.np per item instead of the usual ( next and prev )
 * Assume that all pointer values can be interpreted as k-bit integers , and define x.np to x.np = ( x.next XOR x.prev),
 * the k-bit "exclusive or" of x.next and x.prev ( The value NIL is represented by 0 ). Be sure to describe the information
 * you need to access the head of the list. Show how to implement SEARCH, INSERT and DELETE on such list. Also,
 * show how to implement REVERSE on such a list on O(1) time.
 * <p>
 * Memory efficient Doubly linked list :
 * <p>
 * http://www.geeksforgeeks.org/xor-linked-list-a-memory-efficient-doubly-linked-list-set-1/
 *
 *
 * CAN NOT BE IMPLEMENTED IN JAVA....
 */
public class KbitDoublyLinkedList {

    private Node<Integer> head = null;
    private Node<Integer> tail = null;

    private static long xor(Node<Integer> previous, Node<Integer> next) {
        long left = 0;
        long right = 0;
        if (previous != null) {
            left = getObjectAddess(previous.hashCode());
        }
        if (next != null) {
            right = getObjectAddess(next.hashCode());
        }
        return left ^ right;
    }

    public static long getObjectAddess(Object o) {
        Object[] objects = {o};
        Unsafe unsafe = getUnsafe();
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        return unsafe.getLong(objects, baseOffset);
    }

    @SuppressWarnings("restriction")
    private static Unsafe getUnsafe() {
        try {

            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            return (Unsafe) singleoneInstanceField.get(null);

        } /*catch (IllegalArgumentException e) {
            throw createExceptionForObtainingUnsafe(e);
        } catch (SecurityException e) {
            throw createExceptionForObtainingUnsafe(e);
        } catch (NoSuchFieldException e) {
            throw createExceptionForObtainingUnsafe(e);
        } catch (IllegalAccessException e) {
            throw createExceptionForObtainingUnsafe(e);
        }*/ catch (IllegalArgumentException | SecurityException | NoSuchFieldException | IllegalAccessException exp) {
            exp.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        KbitDoublyLinkedList mem = new KbitDoublyLinkedList();
        int[] ints = new int[]{10, 100, 200};
//        int[] ints = new int[]{10, 100, 200, 7, 14, 28};
        Node<Integer> head = null;
        for (int i : ints) {
            mem.insert(i);
        }
        mem.print();
    }

    public void print() {
        Node<Integer> tmp = head;
        Node<Integer> prev = null;
        while (tmp != tail) {
            System.out.print(tmp.data);
            System.out.print("-->");
            prev = tmp;
            tmp.npx = xor(null, prev);
        }
    }

    public void insert(int d) {
        Node<Integer> node = new Node<>();
        node.data = d;
        if (head == null) {
            head = node;
            tail = node;
            node.npx = xor(null, null);
        } else {
            head.npx = xor(head, null);
            node.npx = xor(null, head);
        }
        System.out.println(d + " , d ===>" + node.npx);
        head = node;
    }

    static class Node<T> {
        T data;
        long npx; // Contains the XOR of address(previous) and address(next)

        @Override
        public String toString() {
            return "C: " + data;
        }

    }


}