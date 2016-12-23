package com.mcdowell.solutions.arrays;

public class IsPalindrome {

	private static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd = false;
		for (int index = 0; index < table.length; index++) {
			if (table[index] % 2 == 1) {
				if (foundOdd) {
					return false;
				}
				foundOdd = true;
			}
		}
		return true;
	}

	private static int getCharNumber(Character character) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int A = Character.getNumericValue('A');
		int Z = Character.getNumericValue('Z');
		int value = Character.getNumericValue(character);

		if (a <= value && value <= z) {
			return value - a;
		} else if (A <= value && value <= Z) {
			return value - A;
		}

		return -1;
	}

	private static int[] buildCharFrequencyTable(String string) {
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char character : string.toCharArray()) {
			int value = getCharNumber(character);
			if (value != -1) {
				table[value]++;
			}
		}
		return table;
	}

	private static boolean isPalindromeTableCount(String string) {

		if (string.length() < 0) {
			return false;
		}

		int[] table = buildCharFrequencyTable(string);
		return checkMaxOneOdd(table);
	}

	private static boolean isPalindromeRunningCount(String string) {

		if (string.length() < 0) {
			return false;
		}

		int countOdd = 0;
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char character : string.toCharArray()) {
			int value = getCharNumber(character);
			if (value != -1) {
				table[value]++;
				if (table[value] % 2 == 1) {
					countOdd += 1;
				} else {
					countOdd -= 1;
				}
			}
		}

		return countOdd <= 1;
	}

	private static int createBitVector(String string) {
		int bitVector = 0;
		for (char character : string.toCharArray()) {
			int value = getCharNumber(character);

			// Turn on/off bits representing characters
			bitVector = toggleBitVector(bitVector, value);
		}
		return bitVector;
	}

	private static boolean checkExactlyOneBitSet(int bitVector) {
		// Check if there is only one bit difference
		return (bitVector & (bitVector - 1)) == 0;
	}

	private static int toggleBitVector(int bitVector, int value) {
		if (value < 0) {
			return bitVector;
		}

		int mask = 1 << value; // Mask representing character value
		if ((bitVector & mask) == 0) {
			bitVector |= mask; // Bit is not set yet then turn it on
		} else {
			bitVector &= ~mask; // Bit is already set then turn if off
		}
		return bitVector;
	}

	private static boolean isPalindromeBitVector(String string) {

		if (string.length() < 0) {
			return false;
		}
		int bitVector = createBitVector(string);
		return bitVector == 0 || checkExactlyOneBitSet(bitVector);
	}

	private static void testIsPalindromeBitVector(String palindrome, String nonPalindrome) {
		System.out.println("Is string '" + palindrome + "' a palindrome? " + isPalindromeBitVector(palindrome));
		System.out.println("Is string '" + nonPalindrome + "' a palindrome? " + isPalindromeBitVector(nonPalindrome));
	}

	private static void testIsPalindromeTableCount(String palindrome, String nonPalindrome) {
		System.out.println("Is string '" + palindrome + "' a palindrome? " + isPalindromeTableCount(palindrome));
		System.out.println("Is string '" + nonPalindrome + "' a palindrome? " + isPalindromeTableCount(nonPalindrome));
	}

	private static void testIsPalindromeRunningCount(String palindrome, String nonPalindrome) {
		System.out.println("Is string '" + palindrome + "' a palindrome? " + isPalindromeRunningCount(palindrome));
		System.out
				.println("Is string '" + nonPalindrome + "' a palindrome? " + isPalindromeRunningCount(nonPalindrome));
	}

	public static void main(String[] args) {
		final String palindrome = "Tact Coa";
		final String nonPalindrome = "aleks";
		System.out.println("TESTING: IsPalindromeBitVector *************");
		testIsPalindromeBitVector(palindrome, nonPalindrome);
		System.out.println("TESTING: IsPalindromeTableCount *************");
		testIsPalindromeTableCount(palindrome, nonPalindrome);
		System.out.println("TESTING: IsPalindromeRunningCount *************");
		testIsPalindromeRunningCount(palindrome, nonPalindrome);
	}
}
