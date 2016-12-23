package com.mcdowell.solutions.arrays;

public class Division {
	
	public static void main(String[] argv) {
		try {
			long start1 = System.currentTimeMillis();
			int value1 = division_naive(5000001, 2);
			long end1 = System.currentTimeMillis();
			System.out.println(value1 + " - " + (end1 - start1));

			System.out.println("---------------");

			long start2 = System.currentTimeMillis();
			int value2 = division_optimized(5000001, 2);
			long end2 = System.currentTimeMillis();
			System.out.println(value2 + " - " + (end2 - start2));
		} catch (Throwable t) {
			System.err.println(t.getStackTrace());
		}
	}

	private static int division_naive(int numerator, int denominator) throws Exception {
		if (denominator == 0) {
			throw new Exception("ERROR: Division by Zero!!!");
		} else {
			int remainder = 0;
			int abs_num = Math.abs(numerator);
			int abs_den = Math.abs(denominator);
			while (abs_num >= abs_den) {
				abs_num -= abs_den;
				remainder++;
			}
			return negative(numerator, denominator, remainder);
		}
	}

	private static int division_optimized(int numerator, int denominator) throws Exception {
		if (denominator == 0) {
			throw new Exception("ERROR: Division by Zero!!!");
		} else {
			int remainder = 0;
			int bits = 0;
			int abs_num = Math.abs(numerator);
			int abs_den = Math.abs(denominator);
			while (abs_num >= abs_den) {
				bits = 0;
				// double ads_den by shift the bit left
				while (abs_num >= abs_den << bits) {
					abs_num -= abs_den << bits;
					remainder += 1 << bits;
					bits++;
				}
			}

			return negative(numerator, denominator, remainder);
		}
	}

	private static int negative(int numerator, int denominator, int remainder) {
		return ((numerator < 0) ^ (denominator < 0)) ? -remainder : remainder;
	}
}
