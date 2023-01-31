package org.junit5.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This is how you order test methods by their name
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestMethodOrderedByName {
    MathUtils mathUtilsClient = new MathUtils();

    @Test
    void testDivide() {
        int result = mathUtilsClient.divide(3,1);
        assertEquals(3, result, () -> "The answer is supposed to be 3");
    }

    @Test
    void testSubtract() {
        int result = mathUtilsClient.subtract(4,1);
        assertEquals(3, result, () -> "The answer is supposed to be 3");
    }

    @Test
    void testMultiply() {
        int result = mathUtilsClient.multiply(3,1);
        assertEquals(3, result, () -> "The answer is supposed to be 3");
    }

    @Test
    void testAdd() {
        int result = mathUtilsClient.add(2,1);
        assertEquals(3, result, () -> "The answer is supposed to be 3");
    }
}
