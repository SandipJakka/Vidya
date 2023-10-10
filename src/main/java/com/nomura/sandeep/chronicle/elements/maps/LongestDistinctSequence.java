package com.nomura.sandeep.chronicle.elements.maps;

import com.google.common.collect.Maps;

import java.util.Map;

public class LongestDistinctSequence<T> {

    public int lengthOfLongestDistinctSubSequence(T[] array) {
        int max = 0;
        Map<T, Integer> valueToLastAppearanceIndexMap = Maps.newHashMap();
        int startIndex = 0, endIndex = 0;
        for (int i = 0; i < array.length; i++) {
            T value = array[i];
            if (valueToLastAppearanceIndexMap.containsKey(value)) {
                startIndex = valueToLastAppearanceIndexMap.get(value) + 1;
            }
            valueToLastAppearanceIndexMap.put(value, i);
            endIndex = i;
            max = Math.max(max, (endIndex - startIndex) + 1);
        }
        return max;
    }


    public static void main(String[] args) {
        String[] arr = new String[]{"f", "s", "f", "e", "t", "w", "e", "n", "w", "e"};
        String[] arr2 = new String[]{"f", "s", "e", "t", "w", "z", "x", "y", "1"};
        String[] arr3 = new String[]{"f", "f", "f", "f"};
        String[] arr4 = new String[]{"f", "f", "f", "f", "1"};

        LongestDistinctSequence<String> obj = new LongestDistinctSequence<>();

        System.out.println(obj.lengthOfLongestDistinctSubSequence(arr));
        System.out.println(obj.lengthOfLongestDistinctSubSequence(arr2));
        System.out.println(obj.lengthOfLongestDistinctSubSequence(arr3));
        System.out.println(obj.lengthOfLongestDistinctSubSequence(arr4));
    }
}
