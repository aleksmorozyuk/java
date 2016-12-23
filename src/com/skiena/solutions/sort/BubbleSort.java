// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.sort;

import java.util.Arrays;

/**
 * @author aleks.
 */
public class BubbleSort {

    public static int[] sort(int[] input) {
        for(int i = 0; i < input.length - 1; i++) {
            for(int j=i+1; j < input.length; j++) {
                if(input[i] > input[j]) {
                    swap(input, i, j);
                }
            }
        }
        return input;
    }

    private static void swap(int[] input, int i, int j) {
        System.out.println("In-progress: " + Arrays.toString(input));
        input[i] = input[i] + input[j];
        input[j] = input[i] - input[j];
        input[i] = input[i] - input[j];
    }

    public static void main(String[] args) {
        int[] input = {1,32,12,11,90,6,3,0};

        System.out.println("Un-sorted: " + Arrays.toString(input));
        System.out.println("Sorted:" + Arrays.toString(BubbleSort.sort(input)));
    }
}
