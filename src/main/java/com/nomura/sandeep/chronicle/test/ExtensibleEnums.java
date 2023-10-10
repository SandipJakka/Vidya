package com.nomura.sandeep.chronicle.test;

import java.io.Serializable;

public class ExtensibleEnums {

    interface Displayer {
        void show();
    }

    interface Operation {
        double apply(double x, double y);
    }

    enum BasicOperation implements Operation, Displayer {

        PLUS {
            @Override
            public void show() {

            }

            @Override
            public double apply(double x, double y) {
                return x + y;
            }
        },
        SUBSTRACT {
            @Override
            public void show() {

            }

            @Override
            public double apply(double x, double y) {
                return x - y;
            }
        },
        MULTIPLY {
            @Override
            public void show() {

            }

            @Override
            public double apply(double x, double y) {
                return x * y;
            }
        },
        DIVIDE {
            @Override
            public void show() {

            }

            @Override
            public double apply(double x, double y) {
                return x / y;
            }
        };

    }

    enum ExtendedOperation implements Operation, Displayer {
        MODULO {
            @Override
            public void show() {

            }

            @Override
            public double apply(double x, double y) {
                return x % y;
            }
        }
    }


    // This essentially says the T is of Enum<T> and Operation and Displayer..
    //very interesting syntax...
    public static <T extends Enum<T> & Operation & Displayer> void test(Class<T> type, double x, double y) {
        for (Operation op : type.getEnumConstants()) {
            System.out.printf("%f %s %f = %f %n", x, op, y, op.apply(x, y));
        }
    }

    public static void main(String[] args) {
        test(BasicOperation.class, 2.0, 5.0);
    }

    // interesting syntax...
    Runnable runnable = (Runnable & Serializable) () -> System.out.println("Serializable runnable ...");
}
