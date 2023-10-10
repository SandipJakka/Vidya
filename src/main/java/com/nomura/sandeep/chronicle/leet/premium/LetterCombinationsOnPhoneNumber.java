package com.nomura.sandeep.chronicle.leet.premium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * Constraints:
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsOnPhoneNumber {
    private final static Map<String, String> PHONE_NUMBER_TO_CHARS = new HashMap<>();

    public LetterCombinationsOnPhoneNumber() {
        PHONE_NUMBER_TO_CHARS.put("2", "abc");
        PHONE_NUMBER_TO_CHARS.put("3", "def");
        PHONE_NUMBER_TO_CHARS.put("4", "ghi");
        PHONE_NUMBER_TO_CHARS.put("5", "jkl");
        PHONE_NUMBER_TO_CHARS.put("6", "mno");
        PHONE_NUMBER_TO_CHARS.put("7", "pqrs");
        PHONE_NUMBER_TO_CHARS.put("8", "tuv");
        PHONE_NUMBER_TO_CHARS.put("9", "wxyz");
    }


    public static void main(String[] args) {
        LetterCombinationsOnPhoneNumber letterCombinationsOnPhoneNumber = new LetterCombinationsOnPhoneNumber();
        //letterCombinationsOnPhoneNumber.letterCombinationsBruteforce("23");
        //letterCombinationsOnPhoneNumber.letterCombinationsBruteforce("2345");
        letterCombinationsOnPhoneNumber.letterCombinations("2345");
        //letterCombinationsOnPhoneNumber.letterCombinationsBruteforce("2345");
    }

    public List<String> letterCombinationsBruteforce(String digits) {
        if ("".equals(digits)) {
            return Collections.emptyList();
        }
        if (digits.length() == 1) {
            String s = PHONE_NUMBER_TO_CHARS.get(digits);
            return s.chars()
                    .mapToObj(c -> (char)c)
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            String s = Character.toString(digits.charAt(i));
            result = combination(result, PHONE_NUMBER_TO_CHARS.get(s));
        }
       // System.out.println(result);
        return result;
    }


    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }

    private List<String> combination(List<String> result, String phoneNumber) {
        List<String> phoneNumberArray = phoneNumber.chars()
                .mapToObj(c-> (char)c)
                .map(String::valueOf)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            return phoneNumberArray;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < phoneNumberArray.size(); j++) {
                res.add(result.get(i) + phoneNumberArray.get(j));
            }
        }
        return res;
    }
}
