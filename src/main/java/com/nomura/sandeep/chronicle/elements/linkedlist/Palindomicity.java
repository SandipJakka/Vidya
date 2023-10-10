package com.nomura.sandeep.chronicle.elements.linkedlist;


public class Palindomicity {
    static class Node {
        char data;
        Node next;

        Node(char d) {
            data = d;
            next = null;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }

    private Node head;
    private Node left;

    /* Push a node to linked list. Note that this function
      changes the head */
    public void push(char new_data) {
        /* Allocate the Node &
           Put in the data */
        Node new_node = new Node(new_data);

        /* link the old list off the new one */
        new_node.next = head;

        /* Move the head to point to new Node */
        head = new_node;
    }

    // A utility function to print a given linked list
    void printList(Node ptr) {
        while (ptr != null) {
            System.out.print(ptr.data + "->");
            ptr = ptr.next;
        }
        System.out.println("NULL");
    }

    boolean isPalindrome(Node head) {
        return isPalindromeUtil(head);
    }

    /*private boolean isPalindromeUtil(Node node) {
        System.out.println("isPalindromeUtil(" + node + ")");
        if (node == null || node.next == null) {
            return true;
        }
        Node left = node;
        Node right = node.next;
        boolean isP = isPalindromeUtil(right);
        if (!isP) {
            return false;
        }

        if (left.data != right.data) {
            System.out.println("Data not matching : left ..." + left.data + " , right ...." + right.data);
            return false;
        }
        return true;
    }*/

    boolean isPalindromeUtil(Node right) {
        System.out.println("isPalindromeUtil(" + right + ")");
        left = head;

        //stop recursion when right becomes NULL
        if (right == null)
            return true;

        //If sub-list is not palindrome then no need to
        //check for current left and right, return false
        boolean isp = isPalindromeUtil(right.next);
        if (isp == false)
            return false;

        //Check values at current left and right
        boolean isp1 = (right.data == left.data);

        //Move left to next node
        left = left.next;

        return isp1;
    }


    /* Driver program to test the above functions */
    public static void main(String[] args) {

        /* Start with the empty list */
        Palindomicity llist = new Palindomicity();

        //char str[] = {'a', 'b', 'a', 'c', 'a', 'b', 'a'};
        char str[] = {'a', 'b', 'a'};
        String string = new String(str);
        for (int i = 0; i < str.length; i++) {
            llist.push(str[i]);
            llist.printList(llist.head);
        }

        System.out.println(llist.isPalindrome(llist.head));

         /*   if (llist.isPalindrome(llist.head) != false) {
                System.out.println("Is Palindrome");
                System.out.println("");
            } else {
                System.out.println("Not Palindrome");
                System.out.println("");
            }*/
    }
}

