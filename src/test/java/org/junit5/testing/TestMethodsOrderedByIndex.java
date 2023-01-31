package org.junit5.testing;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMethodsOrderedByIndex {
    MathUtils mathUtilsClient = new MathUtils();

    @Test
    @Order(2)
    void testDivideOrder2() {
        int result = mathUtilsClient.divide(3,1);
        assertEquals(3, result, () -> "The answer is supposed to be 3");
    }

    @Test
    @Order(1)
    void testSubtractOrder1() {
        int result = mathUtilsClient.subtract(4,1);
        assertEquals(3, result, () -> "The answer is supposed to be 3");
    }

    @Test
    @Order(4)
    void testMultiplyOrder4() {
        int result = mathUtilsClient.multiply(3,1);
        assertEquals(3, result, () -> "The answer is supposed to be 3");
    }

    @Test
    @Order(3)
    void testAddOrder3() {
        int result = mathUtilsClient.add(2,1);
        assertEquals(3, result, () -> "The answer is supposed to be 3");
    }
}
