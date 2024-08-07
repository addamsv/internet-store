package com.example.demo.unitTest;

import com.example.demo.utils.PrintEx;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PrintEx (Console) Utils TEST")
public class PrintExTest {
    @Test
    @DisplayName("Header Test")
    public void printHeaderTest() {
        String value = PrintEx.printHeader("ok");
        Assertions.assertEquals("+--------+", value);
    }

    @Test
    @DisplayName("Footer Test")
    public void printFooterTest() {
        String value = PrintEx.printFooter("ok");
        Assertions.assertEquals("+--------+", value);
    }

    @Test
    @DisplayName("Content Test")
    public void printContentTest() {
        String value = PrintEx.printContent("ok");
        Assertions.assertEquals("|   ok   |", value);
    }
}
