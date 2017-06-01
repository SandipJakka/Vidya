package com.nomura.sandeep.chronicle;


import java.util.ArrayList;
import java.util.List;

public class GetAllSubSets {

    public List<List<Integer>> get(List<Integer> set){
        List<List<Integer>> allSubSets = new ArrayList<List<Integer>>();

        int max = 1 << set.size() ;

        for (int i=0 ; i < max ; i ++){
            List<Integer> subset = new ArrayList<Integer>();
            int index = 0;
            int k = i;
            while ( k > 0){
               // if ( (k & 1) > 0 ){ // Only when k is even.
                if ( (k%2)!=0){
                    subset.add(set.get(index));
                }
               // k >>=1;   // k/2^1
                k=k/2;
                index++ ;
            }
            allSubSets.add(subset);
        }
        return allSubSets ;
    }

    public static void main(String[] args) {
        GetAllSubSets subSets = new GetAllSubSets();
        List<Integer> set = new ArrayList<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        List<List<Integer>> allSubSets = subSets.get(set);
        System.out.println("allSubSets = " + allSubSets);
    }

}
