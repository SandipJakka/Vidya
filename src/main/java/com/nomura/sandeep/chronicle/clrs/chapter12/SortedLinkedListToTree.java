package com.nomura.sandeep.chronicle.clrs.chapter12;

public class SortedLinkedListToTree {
    private LNode head, tail;

    /**
     * Returns the head pointer to the sorted linked list.
     *
     * @return
     */
    public LNode insert(int data) {
        LNode node = new LNode();
        node.data = data;
        if (head == null) {
            head = node;
            return head;
        } else {
            LNode tmp = head;
            while (tmp != null) {
                // When only 1 element is present
                if (tmp.next == null && tmp.prev == null) {
                    if (data < tmp.data) {
                        node.next = tmp;
                        head = node;

                    } else {
                        //    tmp.next =
                    }
                    return head;
                }
            }
            return head;
        }
    }

    public static class LNode {
        int data;
        LNode prev;
        LNode next;
    }

}
