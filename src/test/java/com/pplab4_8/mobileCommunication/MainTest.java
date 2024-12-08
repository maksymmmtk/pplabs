package com.pplab4_8.mobileCommunication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MainTest {
    private InputStream inputStream;

    @BeforeEach
    public void setUp() {
        String testInput = "1\nkyivstar\n1234\n0\n";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
    }

    @Test
    public void testExecuteApp() {
        Main.executeApp(inputStream);
    }
}