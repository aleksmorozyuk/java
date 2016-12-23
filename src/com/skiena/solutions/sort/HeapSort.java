// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.sort;

import java.util.Arrays;

/**
 * @author aleks.
 */
public class HeapSort {

    class MinHeap {
        private int size;
        private int[] nodes;
        private int capacity;

        public MinHeap(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            this.nodes = new int[this.capacity + 1];
        }

        public int getSize() {
            return this.size;
        }

        public int findMin() {
            if(this.size < 1) {
                throw new RuntimeException("Heap is empty.");
            }
            return this.nodes[1];
        }

        public void insert(int value) {
            if(this.getSize() > this.capacity) {
                throw new RuntimeException("Heap overflow occurred.");
            }

            this.size = this.size + 1;
            this.nodes[this.size] = value;
            this.percolateUP();
        }

        public int deleteMin() {
            if(this.getSize() < 1) {
                throw new RuntimeException("Heap is empty.");
            }

            int min = this.findMin();
            this.nodes[1] = this.nodes[this.size];
            this.size = this.size - 1;
            this.percolateDOWN();
            return min;
        }

        private void percolateUP() {
            int index = this.getSize();
            while(index > 1) {
                int parent = index / 2;
                if(this.nodes[index] >= this.nodes[parent]) {
                    break;
                }
                swap(index, parent);
                index = parent;
            }
        }

        private void percolateDOWN() {
            int index = 1;
            while(true) {
                int child = index * 2;
                if(child > this.getSize()) {
                    break;
                }

                if(child + 1 <= this.getSize()) {
                    child = findMin(child, child + 1);
                }

                if(this.nodes[index] <= this.nodes[child]) {
                    break;
                }

                swap(index, child);
                index = child;
            }
        }

        private void swap(int i, int j) {
            this.nodes[i] = this.nodes[i] + this.nodes[j];
            this.nodes[j] = this.nodes[i] - this.nodes[j];
            this.nodes[i] = this.nodes[i] - this.nodes[j];
        }

        private int findMin(int left, int right) {
            if(this.nodes[left] <= this.nodes[right]) {
                return left;
            } else {
                return right;
            }
        }
    }

    public void sort(int[] input) {
        final MinHeap heap = new MinHeap(input.length);
        for (int index = 0; index < input.length; index++) {
            heap.insert(input[index]);
        }

        for (int index = 0; index < input.length; index++) {
            input[index] = heap.deleteMin();
        }
    }

    public static void main(String[] args) {
        int[] input = {1,32,12,11,90,6,3,0};

        System.out.println("Un-sorted: " + Arrays.toString(input));
        new HeapSort().sort(input);
        System.out.println("Sorted:" + Arrays.toString(input));
    }
}
