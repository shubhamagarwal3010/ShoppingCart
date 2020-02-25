package com.ecommerce.shopping.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArithmeticRoundingTests {

    @Test
    void testArithmeticRounding() {
        assertEquals(0.57, ArithmeticRounding.round(0.567, 2));
        assertEquals(0.56, ArithmeticRounding.round(0.564, 2));
        assertEquals(0.57, ArithmeticRounding.round(0.565, 2));
    }
}
