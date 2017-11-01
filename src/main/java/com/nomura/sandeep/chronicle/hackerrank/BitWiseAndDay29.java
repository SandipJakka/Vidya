package com.nomura.sandeep.chronicle.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sandeep on 7/7/2016.
 */
public class BitWiseAndDay29 {
    public static void main(String[] args) {
        List<Holder> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            list.add(new Holder(n, k));
        }
        for (Holder holder : list) {
            System.out.println(solve(holder.n, holder.k));
        }
    }

    private static int solve(int n, int k) {
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if ((i & j) < k) {
                    max = Math.max(max, (i & j));
                }
            }
        }
        return max;
    }

    public static class Holder {
        final int n;
        final int k;

        public Holder(int n, int k) {
            this.n = n;
            this.k = k;
        }
    }
}
