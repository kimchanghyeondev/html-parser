package com.example.demo.util;

public class AsciiUtil {

    public static boolean isEngUpper(int ascii) {
        return ascii >= 65 && ascii <= 90;
    }

    public static boolean isEngLower(int ascii) {
        return ascii >= 97 && ascii <= 122;
    }

    public static boolean isNum(int ascii) {
        return ascii >= 48 && ascii <= 57;
    }

    public static boolean isBracket(int ascii) {
        return isLeftBracket(ascii) || isRightBracket(ascii);
    }

    public static boolean isLeftBracket(int ascii) {
        return ascii == 60;
    }

    public static boolean isRightBracket(int ascii) {
        return ascii == 62;
    }

    public static boolean isEngOrNumber(int ascii) {
        return isEngUpper(ascii) || isEngLower(ascii) || isNum(ascii);
    }

    public static boolean isEngOrNumberOrBracket(int ascii) {
        return isEngOrNumber(ascii) || isBracket(ascii);
    }
}
