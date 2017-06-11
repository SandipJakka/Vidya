package com.nomura.sandeep.chronicle.clrs.Chapter4;

/**
 * Created by sandeep on 12/13/2016.
 */
public class EuclidsGCD {

    public static void main(String[] args) {
        EuclidsGCD g = new EuclidsGCD();

        System.out.println("==> " + g.gcd(48, 18));
        System.out.println("==> " + g.gcd(7, 17));
        System.out.println("==> " + g.gcd(16, 18));
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, (a % b));
    }

}
