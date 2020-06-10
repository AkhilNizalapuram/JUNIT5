package com.nisum.poc.JUNIT5Tests;

import com.nisum.poc.Calculator.calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    calculator calc;

    @BeforeAll
    static void start() {
        System.out.println("inside @BeforeAll");
    }

    @BeforeEach
    void init() {
        System.out.println("inside @BeforeEach");
        calc = new calculator();
    }

    @Test
    void additionTest() {
        System.out.println("inside additionTest");
        assertAll(
                () -> assertEquals(2, calc.add(1,1), "Doesn't add two positive numbers properly"),
                () -> assertEquals(0, calc.add(-1,1), "Doesn't add a negative and a positive number properly"),
                () -> assertNotNull(calc, "The calc variable should be initialized")
        );
    }

    @Test
    void divisionTest() {
        System.out.println("inside divisionTest");
        assertThrows(ArithmeticException.class, () -> calc.divide(2,0));
    }

    @AfterEach
    void afterEach() {
        System.out.println("inside @AfterEach");
    }

    @AfterAll
    static void close() {
        System.out.println("inside @AfterAll");
    }
}

