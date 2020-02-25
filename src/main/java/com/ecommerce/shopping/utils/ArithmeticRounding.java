package com.ecommerce.shopping.utils;

public class ArithmeticRounding {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        return (double) Math.round(value * factor) / factor;
    }
}
