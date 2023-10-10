package com.nomura.sandeep.chronicle.elements.arrays;


import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;

public class FindFirstNonRepeatingCharacter {
    public static char findFirst(String string) {
        return string.chars().
                mapToObj(i -> Character.valueOf((char) i))
                .collect(Collectors.groupingBy(identity(), LinkedHashMap::new, counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst().get();
    }

    public static void main(String[] args) {
        System.out.printf("%s ", findFirst("addafjklmmm"));
    }
}
