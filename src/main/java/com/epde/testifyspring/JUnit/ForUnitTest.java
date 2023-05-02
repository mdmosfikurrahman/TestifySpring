package com.epde.testifyspring.JUnit;

public class ForUnitTest {

    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        } else {
            return str.toUpperCase();
        }
    }

    public static Object createObject() {
        return new Object();
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }



    // For Parameterized Tests JUnit 5

    /**
     * @param number to check number
     * @return number
     */
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

}
