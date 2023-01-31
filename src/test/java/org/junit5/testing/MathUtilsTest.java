package org.junit5.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

// Forces the mathUtilsClient to only be created ONCE and not on the default Per_METHOD basis
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {
    // Make sure that you declare the client as a field in the class and initialize it in the setup with the @BeforeEach
    private MathUtils mathUtilsClient;

    // method has to be static, this is needed so that it can run before the class is initialized
    @BeforeAll
    static void beforeAllPrompt() {
        System.out.println("TEST RUNS ARE STARTING NOW!!!    :)");
    }

    @BeforeEach
    void setUp() {
        // initialize the client in the BeforeEach annotation so that it can be used by the other test methods
        mathUtilsClient = new MathUtils();
    }

    // executes after each test method finishes
    @AfterEach
    void tearDown() {
        System.out.println("AfterEach Tearing Down!!!");
    }

    @AfterAll
    void cleanUp() {
        // runs after all test methods have completed
        System.out.println("AfterAll Clean Up!!!");
    }

    @Test
    void testAdd() {
        assertEquals(5, mathUtilsClient.add(2,3));
    }

    @Test
    void testSubtract() {
        int expected_1 = -1;
        int expected0 = 0;
        int expected1 = 1;
        int expected2 = 2;

        // assertAll allows you to combine a collection of assets under Assert
        assertAll(
                // This is the inefficient way of adding a string to the assertEquals method, this string will be created
                // no matter if the test fails or not, no need having a O(n)^2 Time Complexity if it's not necessary
                () -> assertEquals(expected_1, mathUtilsClient.subtract(2,3), "The subtraction should have equaled: " + expected_1),

                // Use a lambda to make it more efficient and only execute IF THE ASSERTION FAILS
                // this is the efficient way same time complexity, but code doesn't get ran unless the test fails
                () -> assertEquals(expected0, mathUtilsClient.subtract(3,3), () -> "The subtraction should have equaled: " + expected0),
                () -> assertEquals(expected1, mathUtilsClient.subtract(4,3), () -> "The subtraction should have equaled: " + expected1),
                () -> assertEquals(expected2, mathUtilsClient.subtract(5,3), () -> "The subtraction should have equaled: " + expected2)
        );
    }

    // the nested annotation allows you to add a class that will hold specific test
    @Nested
    class TestMultiply {
        // @EnabledOnOs(OS.LINUX) will make the test only run if it is on Linux OS
        @Test
        void testMultiplyNegative() {
            int expected = -6;
            assertEquals(expected, mathUtilsClient.multiply(-2,3), "The product should be " + expected);
        }

        @Test
        void testMultiplyPositive() {
            assertEquals(6, mathUtilsClient.multiply(2,3));
        }
    }

    // DisplayName method changes the name of the test in the test output
    @Test
    @DisplayName("Testing the MathUtils divide method")
    void testDivide() {
        assertThrows(ArithmeticException.class, () -> mathUtilsClient.divide(1,0), "Divide by 0 error should be thrown");
        assertEquals(2, mathUtilsClient.divide(2,1));
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testDivide_divisionEqualsZero() {
        // Test usually follow the AAA method, Arrange, Act, Assert or the GWT Given, When, Then

        // Arrange or Given
        int dividend = 0;
        int divisor = 1;
        int expected = 0;

        // Act or When
        int result = mathUtilsClient.divide(dividend, divisor);

        // Assert or Then
        assertEquals(expected, result,() -> "This Should Be Equal To " + expected);
    }

    @Test
    @Disabled
    @DisplayName("TDD method, should not run")
    void testDisabled() {
        fail("This test should be disabled");
    }



}