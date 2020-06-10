package com.nisum.poc.JUNIT5Tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsTest {
    @DisplayName("Run this if `assumeTrue` condition is true, else aborting this test")
    @Test
    void testOnlyOnDevEnvElseAbort() {
        assumeTrue("DEV".equals(System.getenv("APP_MODE")));
        assertEquals(2, 1 + 1);
    }

    @DisplayName("Run this if `assumeTrue` condition is true, else aborting this test (Custom Message)")
    @Test
    void testOnlyOnDevEnvElseAbortWithCustomMsg() {
        assumeTrue("DEV".equals(System.getenv("APP_MODE")), () -> "Aborting test: not on developer environment");
        assertEquals(2, 1 + 1);
    }

    @Test
    void testAssumingThat() {

        assertEquals(2, 1 + 1);

        assumingThat("DEV".equals(System.getenv("APP_MODE")),
                () -> {
                    assertEquals(2, 1 + 1);
                });
        assertEquals(2, 1 + 1);

    }

}

