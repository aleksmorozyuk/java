// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.string;

/**
 * @author aleks.
 */
public class StringMatching {
    public static boolean matchUsingNaive(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        for (int i = 0; i < (textLength - patternLength); i++) {
            int j = 0;
            while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == patternLength) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        final String pattern = "search algorithm";
        final String text = "Naive is a linear search algorithm that checks if a pattern";

        System.out.println("Pattern: " + pattern);
        System.out.println("Text:" + text);
        System.out.println("Pattern Matched? " + StringMatching.matchUsingNaive(text, pattern));
        System.out.println("Pattern Matched? " + StringMatching.matchUsingNaive(text, pattern));
    }
}
