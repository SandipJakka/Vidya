package com.nomura.sandeep.chronicle.test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sandeep.jakka on 5/13/19.
 */
public class Swap {
    static class IntHolder {
        public int value = 0;
    }

    void swap(IntHolder a, IntHolder b) {
        // Although a and b are copies, they are copies *of a reference*.
        // That means they point at the same object as in the caller,
        // and changes made to the object will be visible in both places.
        int temp = a.value;
        a.value = b.value;
        b.value = temp;
    }


    void swap1(Integer a, Integer b) {
        int temp = a.intValue();
        a = b.intValue();
        b = temp;
    }

    public static void main(String[] args) {
        Swap s = new Swap();

        IntHolder a = new IntHolder();
        IntHolder b = new IntHolder();

        b.value = 10;
        a.value = 15;

        s.swap(a, b);

        System.out.println(a.value);
        System.out.println(b.value);

        s.swap(a, b);

        System.out.println(a.value);
        System.out.println(b.value);


        String[] arr = new String[]{"one", "two"};
        SupposedlyImmutable immutable = new SupposedlyImmutable(arr);
        immutable.print();
        arr = immutable.arr();
        arr = new String[]{"one", "two", "three"};
        immutable.print();


        System.out.println(LocalTime.now());
    }


    static class SupposedlyImmutable {
        private final List<String> list;
        private final String[] in;

        SupposedlyImmutable(String[] list) {
            this.list = Arrays.asList(list);
            this.in = list;
        }

        String[] arr() {
            return in;
        }

        void print() {
            System.out.println(list);
        }
    }
}
