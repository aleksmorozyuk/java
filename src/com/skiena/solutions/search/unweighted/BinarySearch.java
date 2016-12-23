// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted;

import java.util.Arrays;

/**
 * @author aleks.
 */
public class BinarySearch {

    public static Integer search(int[] input, int value) {
        return search(input, value, 0, input.length);
    }

    private static Integer search(int[] input, int value, int start, int end) {
        if (start >= end) {
            return null;
        }

        if ((end - start) <= 1) {
            if (value == input[start]) {
                return start;
            } else {
                return null;
            }
        }

        int middle = start + ((end - start) / 2);
        if (value == input[middle]) {
            return middle;
        } else if (value < input[middle]) {
            return search(input, value, start, middle);
        } else {
            return search(input, value, middle + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] input = {1,32,90,12,11,6,3,0};
        System.out.println("Un-sorted: " + Arrays.toString(input));
        System.out.println("Index of found: " + BinarySearch.search(input, 90));
        Arrays.sort(input);
        System.out.println("Sorted: " + Arrays.toString(input));
        System.out.println("Index of found: " + BinarySearch.search(input, 90));
    }
}
