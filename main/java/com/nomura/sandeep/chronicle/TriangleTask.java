package com.nomura.sandeep.chronicle;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by sandeep on 4/9/2016.
 */
public class TriangleTask {
    public static void main(String[] args) {
        TriangleTask tt = new TriangleTask();
        int[] A = new int[]{10, 50, 1, 5};
        System.out.println("===>" + tt.solution(A));
    }

    public int solution(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        List<Integer> list = IntStream.of(A).boxed().collect(Collectors.toList());
        list.sort(((o1, o2) -> o1.compareTo(o2)));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) + list.get(i + 1) > list.get(i + 2)) {
                return 1;
            }
        }
        return 0;
    }
}
