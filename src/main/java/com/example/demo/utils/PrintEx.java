package com.example.demo.utils;

public class PrintEx {
    public static void printTitle(String title) {
        System.out.println(printHeader(title));
        System.out.println(printContent(title));
        System.out.println(printFooter(title));
    }

    public static String printHeader(String title) {
        String out = "+---";

        for (int i = 0; i < title.length(); i++) {
            out = out + "-";
        }

        return out + "---+";
    }

    public static String printFooter(String title) {
        return printHeader(title);
    }

    public static String printContent(String title) {
        return "|   " + title + "   |";
    }
}
