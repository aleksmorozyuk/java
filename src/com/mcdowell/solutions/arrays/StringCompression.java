package com.mcdowell.solutions.arrays;

public class StringCompression {

	private static String compressBad(String string) {
		int countConsecutive = 0;
		String compressedString = "";
		for (int index = 0; index < string.length(); index++) {
			countConsecutive += 1;
			if (index + 1 >= string.length() || string.charAt(index) != string.charAt(index + 1)) {
				// Concatenation is bad, taking quadratic time to copy strings
				// over and over again
				compressedString += "" + string.charAt(index) + countConsecutive;
				countConsecutive = 0;
			}
		}
		return compressedString.length() < string.length() ? compressedString : string;
	}

	private static String compressBetter(String string) {
		int countConsecutive = 0;
		StringBuilder compressedString = new StringBuilder();
		for (int index = 0; index < string.length(); index++) {
			countConsecutive += 1;
			if (index + 1 >= string.length() || string.charAt(index) != string.charAt(index + 1)) {
				compressedString.append(string.charAt(index));
				compressedString.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressedString.length() < string.length() ? compressedString.toString() : string;
	}

	private static int countCompression(String string) {
		int compressedLenght = 0, countConsecutive = 0;

		for (int index = 0; index < string.length(); index++) {
			countConsecutive += 1;
			if (index + 1 >= string.length() || string.charAt(index) != string.charAt(index + 1)) {
				compressedLenght += 1 + String.valueOf(countConsecutive).length();
				countConsecutive = 0;
			}
		}
		return compressedLenght;
	}

	private static String compressBest(String string) {
		int compressedLength = countCompression(string);
		if (compressedLength >= string.length()) {
			return string;
		}

		int countConsecutive = 0;
		StringBuilder compressedString = new StringBuilder(compressedLength);
		for (int index = 0; index < string.length(); index++) {
			countConsecutive += 1;
			if (index + 1 >= string.length() || string.charAt(index) != string.charAt(index + 1)) {
				compressedString.append(string.charAt(index));
				compressedString.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressedString.length() < string.length() ? compressedString.toString() : string;
	}

	public static void main(String[] args) {
		final String string = "aabcccccaa";
		System.out.println("STRING: " + string + " BAD COMPRESSED: " + compressBad(string));
		System.out.println("STRING: " + string + " BEST COMPRESSED: " + compressBest(string));
		System.out.println("STRING: " + string + " BETTER COMPRESSED: " + compressBetter(string));
	}
}
