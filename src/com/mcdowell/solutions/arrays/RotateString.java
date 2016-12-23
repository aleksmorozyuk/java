package com.mcdowell.solutions.arrays;

public class RotateString {

	private static boolean isRotation(String stringOne, String stringTwo) {
		int length = stringOne.length();
		if (length == stringTwo.length() && length > 0) {
			String combined = stringOne + stringTwo;
			return isSubstring(combined, stringTwo);
		}
		return false;
	}

	private static boolean isSubstring(String combined, String stringTwo) {
		return combined.contains(stringTwo);
	}

	public static void main(String[] args) {
		final String stringOne = "waterbottle";
		final String stringTwo = "erbottlewat";

		System.out.println("Is string '" + stringTwo + "' a rotation of string '" + stringOne + "'? "
				+ isRotation(stringOne, stringTwo));
	}
}
