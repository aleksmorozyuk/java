package com.mcdowell.solutions.arrays;

import java.util.Arrays;

public class IsPermutation {

	private static boolean isPermutationBySort(String stringOne, String stringTwo) {
		if (stringOne.length() != stringTwo.length()) {
			return false;
		}

		char[] stringOneChar = stringOne.toCharArray();
		char[] stringTwoChar = stringTwo.toCharArray();
		Arrays.sort(stringOneChar);
		Arrays.sort(stringTwoChar);

		return (new String(stringOneChar)).equals(new String(stringTwoChar));
	}

	private static boolean isPermutationByCount(String stringOne, String stringTwo) {
		if (stringOne.length() != stringTwo.length()) {
			return false;
		}

		final int[] char_set = new int[128];
		for (int index = 0; index < stringOne.length(); index++) {
			char_set[stringOne.charAt(index)] += 1;
		}

		for (int index = 0; index < stringTwo.length(); index++) {
			char_set[stringTwo.charAt(index)] -= 1;
			if (char_set[stringTwo.charAt(index)] < 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		final String stringOne = "gat";
		final String stringTwo = "tag";

		if (isPermutationBySort(stringOne, stringTwo)) {
			System.out.println("SORT: String " + stringOne + " is permutation of " + stringTwo);
		} else {
			System.out.println("SORT: String " + stringOne + " is NOT permutation of " + stringTwo);
		}

		if (isPermutationByCount(stringOne, stringTwo)) {
			System.out.println("COUNT: String " + stringOne + " is permutation of " + stringTwo);
		} else {
			System.out.println("COUNT: String " + stringOne + " is NOT permutation of " + stringTwo);
		}
	}
}
