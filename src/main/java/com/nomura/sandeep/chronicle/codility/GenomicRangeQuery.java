package com.nomura.sandeep.chronicle.codility;

/**
 * Created by sandeep on 11/26/2016.
 */
public class GenomicRangeQuery {

    public static void main(String[] args) {
        String S = "CAGCCTA";
        int[] P = new int[]{2, 5, 0};
        int[] Q = new int[]{4, 5, 6};

        GenomicRangeQuery q = new GenomicRangeQuery();
        int[] r = q.solution1(S, P, Q);
        for (int i = 0; i < r.length; i++)
            System.out.print(" " + r[i]);

        System.out.println(" ");
        int[] r1 = q.solution1("A", new int[]{0}, new int[]{0});
        System.out.println("===> " + r1.length);
        for (int i = 0; i < r1.length; i++)
            System.out.print(" " + r1[i]);

    }


/*
    public int[] solution(String S, int[] P, int[] Q) {
        int[] min = new int[P.length];
        Map<Character, Integer> impactFactors = new HashMap<>();
        impactFactors.put('A', 1);
        impactFactors.put('C', 2);
        impactFactors.put('G', 3);
        impactFactors.put('T', 4);

        for (int i = 0; i < P.length; i++) {
            String subSequence = S.substring(P[i], Q[i] + 1);
            int minV = Integer.MAX_VALUE;
            for (int j = 0; j < subSequence.length(); j++) {
                minV = Math.min(minV, impactFactors.get(subSequence.charAt(j)));
            }
            min[i] = minV;
        }
        return min;
    }
*/

    public int[] solution1(String S, int[] P, int[] Q) {
        int sz = P.length > Q.length ? P.length : Q.length;
        int[] min = new int[sz];

        int[] A = new int[S.length()];
        int[] C = new int[S.length()];
        int[] G = new int[S.length()];

        char[] chr = S.toCharArray();
        int a = 0, g = 0, c = 0;
        for (int i = 0; i < chr.length; i++) {
            if (chr[i] == 'A') {
                a = a + 1;
            }
            if (chr[i] == 'C') {
                c = c + 1;
            }
            if (chr[i] == 'G') {
                g = g + 1;
            }
            A[i] = a;
            C[i] = c;
            G[i] = g;
        }
        for (int i = 0; i < sz; i++) {
            int from = P[i];
            int to = Q[i];
            int val;
            if (((from == 0) && (A[from] == 1 || A[to] > 0)) || ((from > 0) && (A[to] > A[from - 1]))) {
                val = 1;
            } else if (((from == 0) && (C[from] == 1 || C[to] > 0)) || ((from > 0) && (C[to] > C[from - 1]))) {
                val = 2;
            } else if (((from == 0) && (G[from] == 1 || G[to] > 0)) || ((from > 0) && (G[to] > G[from - 1]))) {
                val = 3;
            } else {
                val = 4;
            }
            min[i] = val;
        }

        return min;
    }

    public int[] solution(String S, int[] P, int[] Q) {
        int[] A = new int[S.length()];
        int[] C = new int[S.length()];
        int[] G = new int[S.length()];
        char[] CC = S.toCharArray();
        for (int c = 0; c < CC.length; c++) {
            if (CC[c] == ('A')) {
                fillPosition(A, C, G, c);
            } else if (CC[c] == ('C')) {
                fillPosition(C, A, G, c);
            } else if (CC[c] == ('G')) {
                fillPosition(G, C, A, c);
            } else {
                if (c > 0) {
                    C[c] = C[c - 1];
                    G[c] = G[c - 1];
                    A[c] = A[c - 1];
                }
            }
        }
        int sz = P.length > Q.length ? Q.length : P.length;
        int[] sol = new int[sz];
        for (int i = 0; i < sz; i++) {
            int start = P[i];
            int end = Q[i];
            if (isRange(A, start, end)) {
                sol[i] = 1;
            } else if (isRange(C, start, end)) {
                sol[i] = 2;
            } else if (isRange(G, start, end)) {
                sol[i] = 3;
            } else {
                sol[i] = 4;
            }
        }
        return sol;
    }

    private boolean isRange(int[] array, int start, int end) {
        return (start == 0 && (array[start] == 1 || array[end] > 0)) || (start > 0 && array[end] > array[start - 1]);
    }

    private void fillPosition(int[] ToFill, int[] toRepeat1, int[] toReapeat2, int position) {
        ToFill[position] = position == 0 ? ToFill[position] + 1 : ToFill[position - 1] + 1;
        if (position > 0) {
            toRepeat1[position] = toRepeat1[position - 1];
            toReapeat2[position] = toReapeat2[position - 1];
        }
    }
}