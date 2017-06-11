package com.nomura.sandeep.chronicle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sandeep on 4/9/2016.
 */
public class NumberOfDiscIntersections {

    public static void main(String[] args) {
        NumberOfDiscIntersections nd = new NumberOfDiscIntersections();
        int[] A = new int[]{1, 5, 2, 1, 4, 0};
        System.out.println("===>" + nd.solution(A));
    }

    public int solution(int[] A) {
        if (A.length == 0 || A.length > Integer.MAX_VALUE) {
            return 0;
        }
        List<Circle> circles = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            circles.add(new Circle(i, A[i]));
        }
        circles.sort(new RangeCamparator());
        int counter = 0;
        for (int i = 0; i < circles.size(); i++) {
            for (int j = i + 1; j < circles.size(); j++) {
                if ((circles.get(j).mid >= circles.get(i).start && circles.get(j).mid <= circles.get(i).end)) {
                    counter++;
                    if (counter >= 1_000_000) {
                        return -1;
                    }
                }
            }
        }

        return counter;
    }

    class RangeCamparator implements Comparator<Circle> {
        @Override
        public int compare(Circle o1, Circle o2) {
            return o2.range - o1.range;
        }
    }

    class Circle {
        final int start;
        final int mid;
        final int end;
        final int range;

        public Circle(int index, int value) {
            mid = index;
            start = index - value;
            end = index + value;
            range = end - start;
        }
    }
}
