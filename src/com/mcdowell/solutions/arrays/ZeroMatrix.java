package com.mcdowell.solutions.arrays;

public class ZeroMatrix {

	private static void nullifyRow(int[][] matrix, int row) {
		for (int index = 0; index < matrix.length; index++) {
			matrix[row][index] = 0;
		}
	}

	private static void nullifyCol(int[][] matrix, int col) {
		for (int index = 0; index < matrix[0].length; index++) {
			matrix[index][col] = 0;
		}
	}

	private static void zeroMatrixWithArrays(int[][] matrix) {
		boolean[] row = new boolean[matrix.length];
		boolean[] col = new boolean[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					row[i] = true;
					col[j] = true;
				}
			}
		}

		// Nullify Rows
		for (int index = 0; index < row.length; index++) {
			if (row[index]) {
				nullifyRow(matrix, index);
			}
		}

		// Nullify Columns
		for (int index = 0; index < col.length; index++) {
			if (col[index]) {
				nullifyCol(matrix, index);
			}
		}
	}

	private static void zeroMatrixWithBitVector(int[][] matrix) {

		boolean rowHasZero = false;
		boolean colHasZero = false;

		// Check if first row has zeros
		for (int j = 0; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0) {
				rowHasZero = true;
				break;
			}
		}

		// Check if first column has zeros
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				colHasZero = true;
				break;
			}
		}

		// Check if rest of matrix has zeros
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		// Nullify Rows based on values in the first column
		for (int index = 1; index < matrix.length; index++) {
			if (matrix[index][0] == 0) {
				nullifyRow(matrix, index);
			}
		}

		// Nullify Columns based on values in the first row
		for (int index = 1; index < matrix[0].length; index++) {
			if (matrix[0][index] == 0) {
				nullifyCol(matrix, index);
			}
		}

		// Nullify first row
		if (rowHasZero) {
			nullifyRow(matrix, 0);
		}

		// Nullify first column
		if (colHasZero) {
			nullifyCol(matrix, 0);
		}
	}

	private static void printMatrix(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				System.out.print(matrix[row][col] + "|");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		final int size = 8;
		int[][] matrix = new int[size][size];
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (row / 2 == 0 && col / 2 == 0) {
					matrix[row][col] = 0;
				} else {
					matrix[row][col] = col + 1;
				}
			}
		}

		System.out.println("Original Matrix For Array:");
		printMatrix(matrix);

		System.out.println("Zeroed With Array One Time:");
		zeroMatrixWithArrays(matrix);
		printMatrix(matrix);

		System.out.println("Zeroed With Array Two Times:");
		zeroMatrixWithArrays(matrix);
		printMatrix(matrix);

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (row / 2 == 0 && col / 2 == 0) {
					matrix[row][col] = 0;
				} else {
					matrix[row][col] = col + 1;
				}
			}
		}

		System.out.println("Original Matrix For Bit Vector:");
		printMatrix(matrix);

		System.out.println("Zeroed With Bit Vector One Time:");
		zeroMatrixWithBitVector(matrix);
		printMatrix(matrix);

		System.out.println("Zeroed With Bit Vector Two Times:");
		zeroMatrixWithBitVector(matrix);
		printMatrix(matrix);
	}
}
