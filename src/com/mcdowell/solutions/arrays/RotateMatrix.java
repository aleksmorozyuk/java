package com.mcdowell.solutions.arrays;

public class RotateMatrix {
	private static void rotateMatrix(int[][] matrix, int numColumns) {
		for (int layer = 0; layer < (numColumns / 2); layer++) {
			int first = layer;
			int last = (numColumns - 1) - layer;
			for (int index = first; index < last; index++) {
				int offset = index - first;

				// Save the top
				int top = matrix[first][index];
				// Rotate left -> top
				matrix[first][index] = matrix[last - offset][first];
				// Rotate bottom -> left
				matrix[last - offset][first] = matrix[last][last - offset];
				// Rotate right -> bottom
				matrix[last][last - offset] = matrix[index][last];
				// Rotate top -> right
				matrix[index][last] = top;
			}
		}
	}

	private static void printMatrix(int[][] matrix, int numColumns) {
		for (int row = 0; row < numColumns; row++) {
			for (int col = 0; col < numColumns; col++) {
				System.out.print(matrix[row][col] + "|");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		final int numColumns = 4;
		int[][] matrix = new int[numColumns][numColumns];
		for (int row = 0; row < numColumns; row++) {
			for (int col = 0; col < numColumns; col++) {
				matrix[row][col] = col;
			}
		}

		System.out.println("Original Matrix:");
		printMatrix(matrix, numColumns);

		System.out.println("Rotated One Time:");
		rotateMatrix(matrix, numColumns);
		printMatrix(matrix, numColumns);

		System.out.println("Rotated Two Times:");
		rotateMatrix(matrix, numColumns);
		printMatrix(matrix, numColumns);

		System.out.println("Rotated Three Times:");
		rotateMatrix(matrix, numColumns);
		printMatrix(matrix, numColumns);

		System.out.println("Rotated Four Times:");
		rotateMatrix(matrix, numColumns);
		printMatrix(matrix, numColumns);
	}
}
