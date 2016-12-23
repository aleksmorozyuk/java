package com.mcdowell.solutions.arrays;

public class OneWay {

	private static boolean oneEditAway(String stringOne, String stringTwo) {
		if (stringOne.length() == stringTwo.length()) {
			return oneEditReplace(stringOne, stringTwo);
		} else if (stringOne.length() - 1 < stringTwo.length()) {
			return oneEditInsert(stringOne, stringTwo);
		} else if (stringTwo.length() - 1 < stringTwo.length()) {
			return oneEditInsert(stringTwo, stringOne);
		}

		return false;
	}

	private static boolean oneEditInsert(String smaller, String larger) {
		int indexA = 0, indexB = 0;
		while (indexA < smaller.length() && indexB < larger.length()) {
			if (smaller.charAt(indexA) != larger.charAt(indexB)) {
				if (indexA != indexB) {
					return false;
				}
				indexB++; // Increment index of a larger string
			} else {
				indexA++;
				indexB++;
			}
		}
		return true;
	}

	private static boolean oneEditReplace(String stringOne, String stringTwo) {
		boolean foundDifference = false;
		for (int index = 0; index < stringOne.length(); index++) {
			if (stringOne.charAt(index) != stringTwo.charAt(index)) {
				if (foundDifference) {
					return false;
				}
				foundDifference = true;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		final String[] stringOne = { "pale", "pales", "pale", "pale" };
		final String[] stringTwo = { "ple", "pale", "bale", "bae" };

		for (int index = 0; index < stringOne.length; index++) {
			System.out.println("Is string '" + stringOne[index] + "' one edit away from string " + stringTwo[index]
					+ "? " + oneEditAway(stringOne[index], stringTwo[index]));
		}
	}
}
