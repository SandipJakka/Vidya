package com.nomura.sandeep.chronicle.hackerrank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CricketMatchX {
    public static Set<Node> batsmen;
    public static Set<Node> fielders;

    public static void main(String[] args) {
       // Scanner scanner = new Scanner(System.in);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(new File("C:\\Users\\sandeep\\IdeaProjects\\Chronicle\\src\\main\\java\\com\\nomura\\sandeep\\chronicle\\hackerrank\\CricketX")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int noOfBatsment = scanner.nextInt();
        int noOfFielders = scanner.nextInt();
        batsmen = new TreeSet<>();
        fielders = new TreeSet<>();

        while (noOfBatsment > 0) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            batsmen.add(new Node(from, to));
            noOfBatsment--;
        }

        while (noOfFielders > 0) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            fielders.add(new Node(from, to));
            noOfFielders--;
        }
        scanner.close();
        System.out.println(totalFielderStrenght());
    }

    private static int totalFielderStrenght() {
        int totalStrenght = 0;
        //int fielderct = 0;
        for (Node fielder : fielders) {
            for (Node bat : batsmen) {
                //|| (bat.to==fielder.to) || (bat.to==fielder.from)  || (bat.from == fielder.to) || (bat.from==fielder.from)
                if ((bat.from >= fielder.from && bat.from <= fielder.to) || (bat.to >= fielder.from && bat.to <= fielder.to) ){
                    totalStrenght++;
                }
            }
       }
        return totalStrenght;
    }

    public static class Node implements Comparable{
        final int from;
        final int to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Object o) {
            if ( o instanceof Node){
              Node obj = (Node) o;
                if (this.from < obj.from && this.to < obj.to){
                    return -1;
                }else if (this.from == obj.to && this.to == obj.to){
                    return 0;
                }else{
                    return 1;
                }

            }
            return -1;
        }
        @Override
        public String toString() {
            return this.from+","+this.to;
        }
    }


}
