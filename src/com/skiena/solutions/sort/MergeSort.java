// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.sort;

import java.util.Arrays;

/**
 * @author aleks.
 */
public class MergeSort {

    public static void mergesort(int[] input) {
        sort(input, 0, input.length - 1);
    }

    private static void sort(int[] input, int start, int end) {
        if(start < end) {
            int middle = start + ((end - start)/2);
            sort(input, start, middle);
            sort(input, middle + 1, end);
            merge(input, start, middle, end);
        }
    }

    private static void merge(int[] input, int start, int middle, int end) {
        int[] temp = new int[input.length];
        for(int index = start; index <= end; index++) {
            temp[index] = input[index];
        }

        int i = start, j = middle + 1, k = start;
        while(i <= middle && j <= end) {
            if(temp[i] < temp[j]) {
                input[k++] = temp[i++];
            } else {
                input[k++] = temp[j++];
            }
        }

        while(i <= middle) {
            input[k++] = temp[i++];
        }
    }

    public static void main(String[] args) {
        int[] input = {1,32,12,11,90,6,3,0};

        System.out.println("Un-sorted: " + Arrays.toString(input));
        MergeSort.mergesort(input);
        System.out.println("Sorted:" + Arrays.toString(input));
    }
}
