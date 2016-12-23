package com.mcdowell.solutions.arrays;

public class IsUnique {
	private static boolean isUniqueNaive(String string) {
		if (string.length() > 128) {
			return false;
		}

		boolean[] char_set = new boolean[128];
		for (int index = 0; index < string.length(); index++) {
			int value = string.charAt(index);
			if (char_set[value]) { // We've already seen the value
				return false;
			}
			char_set[value] = true;
		}
		return true;
	}

	private static boolean isUniqueImproved(String string) {
		if (string.length() > 128) {
			return false;
		}

		int checker = 0;
		for (int index = 0; index < string.length(); index++) {
			int value = string.charAt(index);
			if ((checker & (1 << value)) > 0) { // Bit is already set for the character value
				return false;
			}
			checker |= (1 << value); // Set bit corresponding to character value
		}
		return true;
	}

	private static void testIsUniqueNaive(String[] uniqueStrings, String[] nonuniqueStrings) {
		for (int index = 0; index < uniqueStrings.length; index++) {
			if (isUniqueNaive(uniqueStrings[index].toLowerCase())) {
				System.out.println("GOOD: " + uniqueStrings[index] + " is unique!");
			} else {
				System.err.println("BAD: " + uniqueStrings[index] + " must be unique!");
			}
		}

		for (int index = 0; index < nonuniqueStrings.length; index++) {
			if (!isUniqueNaive(nonuniqueStrings[index].toLowerCase())) {
				System.out.println("GOOD: " + nonuniqueStrings[index] + " is not unique!");
			} else {
				System.err.println("BAD: " + nonuniqueStrings[index] + " must not be unique!");
			}
		}
	}

	private static void testIsUniqueImproved(String[] uniqueStrings, String[] nonuniqueStrings) {
		for (int index = 0; index < uniqueStrings.length; index++) {
			if (isUniqueImproved(uniqueStrings[index].toLowerCase())) {
				System.out.println("GOOD: " + uniqueStrings[index] + " is unique!");
			} else {
				System.err.println("BAD: " + uniqueStrings[index] + " must be unique!");
			}
		}

		for (int index = 0; index < nonuniqueStrings.length; index++) {
			if (!isUniqueImproved(nonuniqueStrings[index].toLowerCase())) {
				System.out.println("GOOD: " + nonuniqueStrings[index] + " is not unique!");
			} else {
				System.err.println("BAD: " + nonuniqueStrings[index] + " must not be unique!");
			}
		}
	}

	public static void main(String[] args) {
		final String[] uniqueStrings = { "Aleks", "Home", "Minsk", "Belarus" };
		final String[] nonuniqueStrings = { "Aleksandr", "Morozyuk", "Shashki", "Gantsevichi" };

		System.out.println("START: Testing IsUniqueNaive");
		testIsUniqueNaive(uniqueStrings, nonuniqueStrings);
		System.out.println("END: Testing IsUniqueNaive");

		System.out.println("START: Testing IsUniqueImproved");
		testIsUniqueImproved(uniqueStrings, nonuniqueStrings);
		System.out.println("END: Testing IsUniqueNaive");
	}
}
