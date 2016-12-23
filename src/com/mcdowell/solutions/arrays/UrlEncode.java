package com.mcdowell.solutions.arrays;

public class UrlEncode {

	private static String extraSpaces(String string) {
		if (string.length() <= 0) {
			return "";
		}

		String spaceCount = "";
		for (int index = 0; index < string.length(); index++) {
			if (string.charAt(index) == ' ') {
				spaceCount += "  ";
			}
		}
		return spaceCount;
	}

	private static String urlEncode(String string) {
		if (string.length() <= 0) {
			return "";
		}

		final String spaceCount = extraSpaces(string);
		int newStringSize = string.length() + spaceCount.length();
		final char[] newString = new char[newStringSize];

		for (int index = string.length() - 1; index >= 0; index--) {
			if (string.charAt(index) == ' ') {
				newString[newStringSize - 1] = '0';
				newString[newStringSize - 2] = '2';
				newString[newStringSize - 3] = '%';
				newStringSize -= 3;
			} else {
				newString[newStringSize - 1] = string.charAt(index);
				newStringSize -= 1;
			}
		}

		return new String(newString);
	}

	public static void main(String[] args) {
		final String string = "Mr John Smith ";
		System.out.println("STRING: " + string + "->" + urlEncode(string));
	}
}
