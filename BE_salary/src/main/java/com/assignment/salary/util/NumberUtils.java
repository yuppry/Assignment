package com.assignment.salary.util;

public class NumberUtils {

    public static boolean containsAnyNonNumericChars(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }

        String allowedChars = "0123456789";
        for (char c : input.toCharArray()) {
            if (allowedChars.indexOf(c) == -1) {
                return true; // Found an unwanted character
            }
        }
        return false;
    }

    //to remove comma and replace "k,K" to 000
    public static String regexReplaceString(String input) {
        return input.replace(",", "")
                .replaceAll("(?i)k", "000");
    }

}
