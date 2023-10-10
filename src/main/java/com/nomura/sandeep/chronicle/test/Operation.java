package com.nomura.sandeep.chronicle.test;

// Use enums when u need a set of constants whose value u know at compile time.

import java.util.EnumMap;
import java.util.Map;

public enum Operation {
    // Example where each enum is displaying a different behavior.
    // Interesting Pattern.

    PLUS {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },
    SUBSTRACT {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    // Declare the method as abstract as each enum will provide a different implementation
    // of their own.
    abstract double apply(double x, double y);


    public static void main(String[] args) {
        System.out.println(PLUS.apply(2.0, 3.0));
        System.out.println(SUBSTRACT.apply(2.0, 3.0));
        System.out.println(MULTIPLY.apply(2.0, 3.0));
        System.out.println(DIVIDE.apply(2.0, 3.0));

        Map<Operation, String> map = new EnumMap<>(Operation.class);

        map.put(PLUS, "+");
    }
}
