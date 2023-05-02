package com.epde.testifyspring.JUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the Unit Test file for ForUnitTest.java
 * In this file I have used JUnit5 and some assertions' method. These are:
 * <ul>
 * <li><b>assertEquals</b>: Asserts that two objects are equal. In the code, this method is used to verify that the result of an operation is equal to the expected value.
 * <li><b>assertNotEquals</b>: Asserts that two objects are not equal. In the code, this method is used to verify that the result of an operation is not equal to the expected value.
 * <li><b>assertSame</b>: Asserts that two objects refer to the same object. In the code, this method is used to verify that two objects are the same instance.
 * <li><b>assertNotSame</b>: Asserts that two objects do not refer to the same object. In the code, this method is used to verify that two objects are not the same instance.
 * <li><b>assertNull</b>: Asserts that an object is null. In the code, this method is used to verify that the result of a method call with a null argument returns null.
 * <li><b>assertNotNull</b>: Asserts that an object is not null. In the code, this method is used to verify that the result of a method call with a non-null argument is not null.
 * <li><b>assertTrue</b>: Asserts that a boolean condition is true. In the code, this method is used to verify that a boolean condition is true.
 * <li><b>assertFalse</b>: Asserts that a boolean condition is false. In the code, this method is used to verify that a boolean condition is false.
 * <li><b>assertThrows</b>: Asserts that an exception of the specified type is thrown. In the code, this method is used to verify that a method call throws an expected exception.
 * <li><b>assertAll</b>: Groups multiple assertions together. In the code, this method is used to group multiple assertions related to a single method.
 * </ul>
 */

public class ForUnitTestTest {

    @Test
    void testAdd() {
        ForUnitTest forAddition = new ForUnitTest();

        int result = forAddition.add(2, 3);
        assertEquals(5, result);
        int result2 = forAddition.add(2, 5);
        assertNotEquals(5, result2);
    }

    @Test
    void testSubtract() {
        ForUnitTest forSubtract = new ForUnitTest();

        int result = forSubtract.subtract(5, 3);
        assertEquals(2, result);
    }

    @Test
    void testMultiply() {
        ForUnitTest forMultiply = new ForUnitTest();

        int result = forMultiply.multiply(2, 3);
        assertEquals(6, result);
    }

    @Test
    void testDivide() {
        ForUnitTest forDivision = new ForUnitTest();

        int result = forDivision.divide(6, 3);
        assertEquals(2, result);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> forDivision.divide(6, 0));
        assertEquals("Cannot divide by zero", exception.getMessage());

        try {
            forDivision.divide(10, 0);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // expected exception was thrown, test passes
        }
    }


    @Test
    void testEven() {
        assertTrue(ForUnitTest.isEven(2));
        assertTrue(ForUnitTest.isEven(-4));
        assertFalse(ForUnitTest.isEven(3));
        assertFalse(ForUnitTest.isEven(-7));
    }


    @Test
    void testToUpperCaseWithNull() {
        String result = ForUnitTest.toUpperCase(null);
        assertNull(result);
    }

    @Test
    void testToUpperCaseWithNonNull() {
        String result = ForUnitTest.toUpperCase("hello");
        assertNotNull(result);
    }

    @Test
    void testCreateObject() {
        Object obj1 = ForUnitTest.createObject();
        Object obj2 = ForUnitTest.createObject();

        // Test that obj1 and obj2 are not the same object
        assertNotSame(obj1, obj2);

        // Test that obj1 and obj1 are the same object
        assertSame(obj1, obj1);
    }

    @Test
    void testIsPrime() {
        assertAll("primeCheck.isPrime",
                () -> assertFalse(ForUnitTest.isPrime(-1)),
                () -> assertFalse(ForUnitTest.isPrime(0)),
                () -> assertFalse(ForUnitTest.isPrime(1)),
                () -> assertTrue(ForUnitTest.isPrime(2)),
                () -> assertTrue(ForUnitTest.isPrime(3)),
                () -> assertFalse(ForUnitTest.isPrime(4)),
                () -> assertTrue(ForUnitTest.isPrime(5)),
                () -> assertFalse(ForUnitTest.isPrime(6)),
                () -> assertTrue(ForUnitTest.isPrime(7)),
                () -> assertFalse(ForUnitTest.isPrime(8)),
                () -> assertFalse(ForUnitTest.isPrime(9)),
                () -> assertFalse(ForUnitTest.isPrime(10)));
    }

    @Test
    void givenTwoLists_whenAssertingIterables_thenEquals() {
        Iterable<String> firstList = new ArrayList<>(asList("Java", "Junit", "Test"));
        Iterable<String> secondList = new LinkedList<>(asList("Java", "Junit", "Test"));

        assertIterableEquals(firstList, secondList);
    }

    @Test
    void testBubbleSort() {
        int[] arr = {5, 1, 4, 2, 8};
        int[] expected = {1, 2, 4, 5, 8};
        int[] result = ForUnitTest.bubbleSort(arr);

        assertArrayEquals(expected, result);
    }

    // Parameterized Tests JUnit 5

    /*
    Parameterized tests are like other tests except that we add the @ParameterizedTest annotation
     */

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(ForUnitTest.isOdd(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
        assertTrue(ForUnitTest.isBlank(input));
    }

    @ParameterizedTest
    @NullSource
    void isBlank_ShouldReturnTrueForNullInputs(String input) {
        assertTrue(ForUnitTest.isBlank(input));
    }

    @ParameterizedTest
    @EmptySource
    void isBlank_ShouldReturnTrueForEmptyStrings_2(String input) {
        assertTrue(ForUnitTest.isBlank(input));
    }

    @ParameterizedTest
    @EnumSource(Month.class)
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }

    @ParameterizedTest
    @EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
    void fourMonths_AreEndingWithBer(Month month) {
        EnumSet<Month> months = EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        assertTrue(months.contains(month));
    }

    @ParameterizedTest
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVFile(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

}