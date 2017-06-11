package com.nomura.sandeep.chronicle;

public class RandonNumberGen {
    public static  int rand( int lower, int higher){
        return  lower + (int)(Math.random() * (higher - lower + 1));
    }

    public static void main(String[] args) {
        for (int i =0 ; i < 100 ; i++){
            System.out.println("==>" + rand(1,5));
        }
    }
}
