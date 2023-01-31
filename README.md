### Unit Test Should Follow The "FIRST" Principle
 - Fast - Unit tests run fast 
 - Independent - Unit tests are independent ( don't rely on other test of dependencies )
   - Dependency Injections helps with this`
 - Repeatable - Unit tests are repeatable ( should always return the same result )
 - Self-Validating - Unit test validates itself, should not have to do any more test 
 - Thorough & Timely - Covers edge cases ( null check, invalid parameters, etc )
___

### Testing Pyramid - Top Down 
 - End-To-End Testing / UI Testing ( testing software functionality from beginning to end, runs very slow )
 - Integration Testing ( application code is test w/o mocking database or http connections, runs slower )
 - Unit Tests ( small isolated code with fake/mocked dependencies, runs fast )
___

### JUnit

**JUnit5** = JUnit Platform + JUnit Jupiter + JUnit Vintage  
**JUnit Platform** - The JUnit Platform serves as a foundation for launching testing frameworks on the JVM    
**JUnit Jupiter** - Is the combination of the new programming model and extension model for writing tests and extensions in JUnit5    
**JUnit Vintage** - TestEngine for running JUnit3 and JUnit4 based tests on the platform   

#### Dependencies - You Can Add All Of Them Separately Or Use The Aggregator

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>

         <!-- or you can just use the aggregator dependency -->
         <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter -->
      <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter</artifactId>
          <version>5.9.2</version>
          <scope>test</scope>
      </dependency>
    </dependencies>
___


### JUnit Api's

#### BeforeAll
    // method has to be static, this is needed so that it can run before the class is initialized
    @BeforeAll
    static void beforeAllPrompt() {
        System.out.println("TEST RUNS ARE STARTING NOW!!!    :)");
    }

#### BeforeEach
    // Make sure that you declare the client as a field in the class and initialize it in the setup with the @BeforeEach
    private MathUtils mathUtilsClient;

    @BeforeEach
    void setUp() {
        // initialize the client in the BeforeEach annotation so that it can be used by the other test methods
        mathUtilsClient = new MathUtils();
    }

#### AfterEach
    // executes after each test method finishes
    @AfterEach
    void tearDown() {
        System.out.println("AfterEach Tearing Down!!!");
    }

#### AfterAll
    @AfterAll
    void cleanUp() {
        // runs after all test methods have completed
        System.out.println("AfterAll Clean Up!!!");
    }

#### Test
    @Test
    void testAdd() {
        // indicates that this method is a test method
        assertEquals(5, mathUtilsClient.add(2,3));
    }

### AssertAll and Lambda's For Efficient Message Assert Message
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

### Nested and EnabledOnOs
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
        void testMultiplyPositve() {
            assertEquals(6, mathUtilsClient.multiply(2,3));
        }
    }

### DisplayName
    // DisplayName method changes the name of the test in the test output
    @Test
    @DisplayName("Testing the MathUtils divide method")
    void testDivide() {
        assertThrows(ArithmeticException.class, () -> mathUtilsClient.divide(1,0), "Divide by 0 error should be thrown");
        assertEquals(2, mathUtilsClient.divide(2,1));
    }

### Disabled
    @Test
    @Disabled
    @DisplayName("TDD method, should not run")
    void testDisabled() {
        fail("This test should be disabled");
    }

### Arrange Act Assert (AAA) Technique / Given When Then (GWT)
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

### Running Test In Order By Name
```
@TestMethodOrder(MethodOrderer.MethodName.class)  
public class TestMethodOrdered {

}
```

### Running Test In Order By Index
```
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  
public class TestMethodOrdered {

}
```
