package com.nomura.sandeep.chronicle;


import java.util.EnumSet;

public class EnumsetTest {
    enum Topping {Jalopenos, Pine, Mushroom, olive}

    EnumSet<Topping> enumSet = EnumSet.allOf(Topping.class);

    public void print() {
        System.out.printf("%s %n", enumSet);
    }


    public static void main(String[] args) {
        EnumsetTest e = new EnumsetTest();
        e.print();
    }
}
