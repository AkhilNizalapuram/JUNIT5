package com.nisum.poc.JUNIT5Tests;

import com.nisum.poc.Calculator.calculator;
import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

    @RunWith(JUnitPlatform.class)
    public class BeforeAllAnnotations {

        @DisplayName("Add operation test")
        @RepeatedTest(5)
        void addNumber(TestInfo testInfo, RepetitionInfo repetitionInfo) {
            System.out.println("Running test -> " + repetitionInfo.getCurrentRepetition());
            Assertions.assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
        }

        @BeforeAll
        public static void init(){
            System.out.println("Before All init() method called");
        }
    }

