// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.sort;

import java.util.Arrays;

/**
 * @author aleks.
 */
public class QuickSort {

    public static void quicksort(int[] input) {
        sort(input, 0, input.length - 1);
    }

    private static void sort(int[] input, int low, int high) {
        int i = low, j = high;
        int pivot = input[low + (high - low) / 2];

        while (i <= j) { // Re-arrange input around pivot element

            while (input[i] < pivot) {
                i++;
            }

            while (input[j] > pivot) {
                j--;
            }

            if (i <= j) {
                swap(input, i, j);
                i++;
                j--;
            }
        }

        if (low < j) { // Sort one side of input
            sort(input, low, j);
        }

        if (i < high) { // Sort another side of input
            sort(input, i, high);
        }
    }

    private static void swap(int[] input, int i, int j) {
        System.out.println("In-progress: " + Arrays.toString(input));
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args) {
        int[] input = {1,32,12,11,90,6,3,0};

        System.out.println("Un-sorted: " + Arrays.toString(input));
        QuickSort.quicksort(input);
        System.out.println("Sorted:" + Arrays.toString(input));
    }
}
