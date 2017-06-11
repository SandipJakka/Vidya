package com.nomura.sandeep.chronicle;

import java.util.Stack;

/**
 *
 */
public class BracesCheck {
    public static void main(String[] args) {
        BracesCheck br = new BracesCheck();
        //String str = "{{{{{(((({{{{}}}}))))}}}}}";
        //String str = "{}()(((()))){(((({}))))}";
        String str = "{[()()]}";
        //System.out.println(str + " ===> " + br.check(str));

        System.out.println(str + " ===> " + br.isParenthesisMatch(str));
    }

    public static int isParenthesisMatch(String str) {
        if (null == str || str.trim().length() % 2 != 0) {
            return 0;
        }
        if ("".equals(str)) {
            return 1;
        }

        Stack<Character> stack = new Stack<Character>();

        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if (c == ')')
                if (stack.empty())
                    return 0;
                else if (stack.peek() == '(')
                    stack.pop();
                else
                    return 0;
            else if (c == '}')
                if (stack.empty())
                    return 0;
                else if (stack.peek() == '{')
                    stack.pop();
                else
                    return 0;
            else if (c == ']')
                if (stack.empty())
                    return 0;
                else if (stack.peek() == '[')
                    stack.pop();
                else
                    return 0;
        }
        return stack.empty() ? 1 : 0;
    }

    public boolean check(String str) {
        boolean pass = false;
        if (str == null || ("".equals(str.trim())) || str.length() < 2) {
            return pass;
        }
        if (!(str.contains("{") || str.contains("(") || str.contains(")") || str.contains("}"))) {
            return pass;
        }
        Stack<Character> stack = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('(' == c) {
                stack.push(c);
            } else if ('{' == c) {
                stack2.push(c);
            } else if (')' == c) {
                if (stack.empty() || '(' != stack.pop().charValue()) {
                    return false;
                }
            } else if ('}' == c) {
                if (stack2.empty() || '{' != stack2.pop().charValue()) {
                    return false;
                }
            }
        }
        if (stack.size() == 0 && stack2.size() == 0) {
            pass = true;
        }
        return pass;
    }

}
