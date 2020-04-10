package com.ecommerce.shopping.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArithmeticRounding {

    public static double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        return new BigDecimal(value.toString()).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
}
