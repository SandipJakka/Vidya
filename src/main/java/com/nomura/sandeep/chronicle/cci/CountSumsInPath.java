package com.nomura.sandeep.chronicle.cci;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class CountSumsInPath {


    static class Node<T> {
        private final T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.format("[ %s ]", data);
        }
    }


    public long count(Node<Integer> root, int target) {
        List<Integer> sums = new ArrayList<>();
        return helper(root, target, sums);
    }

    private long helper(Node<Integer> root, int target, List<Integer> sums) {
        if (root == null) {
            return 0;
        }
        sums = add(sums, root.data);
        sums.add(root.data);

        return count(sums, target)
                + helper(root.left, target, sums)
                + helper(root.right, target, sums);

    }

    private long count(List<Integer> sums, int target) {
        return sums.stream()
                .filter(e -> (e == target))
                .count();
    }

    private List<Integer> add(List<Integer> sums, Integer data) {
        return sums.stream()
                .map(e -> e + data)
                .collect(toList());
    }


    public static void main(String[] args) {
        Node<Integer> root = new Node<>(10);
        Node<Integer> five = new Node<>(5);
        Node<Integer> oneThree = new Node<>(3);
        Node<Integer> twoThree = new Node<>(3);
        Node<Integer> minusTwo = new Node<>(-2);
        Node<Integer> one = new Node<>(1);
        Node<Integer> minusThree = new Node<>(-3);
        Node<Integer> eleven = new Node<>(11);
        Node<Integer> two = new Node<>(2);


        oneThree.left = twoThree;
        oneThree.right = minusTwo;
        five.left = oneThree;
        two.right = one;
        five.right = two;
        minusThree.right = eleven;
        root.left = five;
        root.right = minusThree;


        CountSumsInPath countSumsInPath = new CountSumsInPath();
        System.out.printf(" %d ", countSumsInPath.count(root, 8));

    }

}