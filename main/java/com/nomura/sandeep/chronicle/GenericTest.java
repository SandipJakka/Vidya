package com.nomura.sandeep.chronicle;


import java.util.ArrayList;
import java.util.List;

public class GenericTest {

    public static void main(String[] args) {
        GenericTest test = new GenericTest();
        List<Double> dl = new ArrayList<Double>();
        List<Integer> il = new ArrayList<Integer>();
        List<Object> ol = new ArrayList<Object>();
        test.print(dl);
        test.print(il);
       // test.print2(dl);
        test.print2(il);
        test.print2(ol);
     ///   test.print(ol);
    }

    public void  print(List<? extends  Number> list){
        System.out.println("list = [" + list + "]");
    }

    public void  print2(List<? super   Integer> list){
        System.out.println("list = [" + list + "]");
    }

}



