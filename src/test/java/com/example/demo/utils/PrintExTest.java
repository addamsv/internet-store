package com.example.demo.utils;

import org.junit.jupiter.api.*;

@DisplayName("PrintExTest (Console Utils)")
public class PrintExTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Header Test")
    public void printHeaderTest() {
        String value = PrintEx.printHeader("ok");
        Assertions.assertEquals("╭────────╮", value);
    }

    @Test
    @DisplayName("Footer Test")
    public void printFooterTest() {
        String value = PrintEx.printFooter("ok");
        Assertions.assertEquals("╰────────╯", value);
    }

    @Test
    @DisplayName("Content Test")
    public void printContentTest() {
        String value = PrintEx.printContent("ok");
        Assertions.assertEquals("│   ok   │", value);
    }
}
