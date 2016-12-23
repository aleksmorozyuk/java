package com.mcdowell.solutions.linkedlists;

import java.util.Stack;

public class IsPalindrome {

	public static class RecurseResult {
		private boolean result;
		private LinkedListNode<Integer> node;

		public RecurseResult(boolean result, LinkedListNode<Integer> node) {
			this.node = node;
			this.result = result;
		}

		public boolean getResult() {
			return this.result;
		}

		public LinkedListNode<Integer> getNode() {
			return this.node;
		}
	}

	public static class HeadAndTail {
		private LinkedListNode<Integer> head;
		private LinkedListNode<Integer> tail;

		public HeadAndTail(LinkedListNode<Integer> head, LinkedListNode<Integer> tail) {
			this.head = head;
			this.tail = tail;
		}

		public LinkedListNode<Integer> getHead() {
			return this.head;
		}

		public LinkedListNode<Integer> getTail() {
			return this.tail;
		}
	}

	private static int getLinkedListSize(LinkedListNode<Integer> linkedList) {
		int linkedListSize = 0;
		LinkedListNode<Integer> node = linkedList;
		while (node != null) {
			linkedListSize += 1;
			node = node.getNextNode();
		}
		return linkedListSize;
	}

	private static boolean isEquals(LinkedListNode<Integer> one, LinkedListNode<Integer> two) {
		LinkedListNode<Integer> list1 = one;
		LinkedListNode<Integer> list2 = two;

		while (list1 != null && list2 != null) {
			if (list1.getNodeData() != list2.getNodeData()) {
				return false;
			}
			list1 = list1.getNextNode();
			list2 = list2.getNextNode();
		}

		return list1 == null && list2 == null;
	}

	private static HeadAndTail reverse(LinkedListNode<Integer> head) {
		if (head == null) {
			return null;
		}

		HeadAndTail headTail = reverse(head.getNextNode());
		LinkedListNode<Integer> clonedHead = new LinkedListNode<Integer>(head.getNodeData());

		if (headTail == null) {
			return new HeadAndTail(clonedHead, clonedHead);
		}

		headTail.getTail().setNextNode(clonedHead);
		return new HeadAndTail(headTail.getHead(), clonedHead);
	}

	private static boolean isPalindromeByRecursive(LinkedListNode<Integer> head) {
		return isPalindromeByRecursive(getLinkedListSize(head), head).getResult();
	}

	private static RecurseResult isPalindromeByRecursive(int length, LinkedListNode<Integer> head) {
		if (head == null || length <= 0) { // Empty list is always a Palindrome
			return new RecurseResult(true, head);
		} else if (length == 1) { // Has odd # of elements, move next
			return new RecurseResult(true, head.getNextNode());
		}

		RecurseResult recurseResult = isPalindromeByRecursive(length - 2, head.getNextNode());
		if (!recurseResult.getResult() || recurseResult.getNode() == null) {
			return recurseResult;
		}

		boolean result = (head.getNodeData() == recurseResult.getNode().getNodeData());

		return new RecurseResult(result, recurseResult.getNode().getNextNode());
	}

	private static boolean isPalindromeByIterativeWithStack(LinkedListNode<Integer> head) {
		int linkedListSize = getLinkedListSize(head);
		final Stack<Integer> stack = new Stack<Integer>();
		for (int index = 0; index < linkedListSize / 2; index++) {
			stack.push(head.getNodeData());
			head = head.getNextNode();
		}

		// Has odd # of elements, skip the middle element
		if (linkedListSize % 2 > 0) {
			head = head.getNextNode();
		}

		while (head != null) {
			if (stack.isEmpty()) {
				return false;
			} else if (!stack.pop().equals(head.getNodeData())) {
				return false;
			}
			head = head.getNextNode();
		}

		return stack.isEmpty();
	}

	private static boolean isPalindromeByReverseAndCompare(LinkedListNode<Integer> head) {
		// First, reverse a give list
		HeadAndTail reversed = reverse(head);
		// Second, compare given and reversed lists
		return isEquals(head, reversed.getHead());
	}

	private static boolean isPalindromeByIterativeWithPointers(LinkedListNode<Integer> head) {
		LinkedListNode<Integer> fast = head;
		LinkedListNode<Integer> slow = head;
		Stack<Integer> stack = new Stack<Integer>();
		while (fast != null && fast.getNextNode() != null) {
			stack.push(slow.getNodeData());

			slow = slow.getNextNode();
			fast = fast.getNextNode().getNextNode();
		}

		// Has odd # of elements, skip the middle element
		if (fast != null) {
			slow = slow.getNextNode();
		}

		while (slow != null) {
			if (!stack.pop().equals(slow.getNodeData())) {
				return false;
			}
			slow = slow.getNextNode();
		}

		return true;
	}

	private static void printLintedList(LinkedListNode<?> linkedList) {
		final StringBuilder sb = new StringBuilder();
		LinkedListNode<?> node = linkedList;
		while (node != null) {
			sb.append(node.getNodeData());
			node = node.getNextNode();
		}
		System.out.println(sb.toString());
	}

	private static LinkedListNode<Integer> getLinkedList(final int[] numbers) {
		LinkedListNode<Integer> linkedList = new LinkedListNode<Integer>(numbers[0]);
		for (int index = 1; index < numbers.length; index++) {
			linkedList.append(numbers[index]);
		}
		return linkedList;
	}

	public static void main(String[] args) {
		final int[] palindromes = { 0, 1, 2, 2, 2, 2, 1, 0 };
		final int[] nonpalindromes = { 0, 1, 2, 2, 5, 2, 1, 0 };

		printLintedList(getLinkedList(palindromes));
		System.out.println("Is the list a palindrome? Iteratively (Size is Known) -> "
				+ isPalindromeByIterativeWithStack(getLinkedList(palindromes)));
		System.out.println("Is the list a palindrome? Iteratively (Size is Unknown) -> "
				+ isPalindromeByIterativeWithPointers(getLinkedList(palindromes)));
		System.out.println("Is the list a palindrome? Reverse & Compare -> "
				+ isPalindromeByReverseAndCompare(getLinkedList(palindromes)));
		System.out.println(
				"Is the list a palindrome? Recursively -> " + isPalindromeByRecursive(getLinkedList(palindromes)));

		printLintedList(getLinkedList(nonpalindromes));
		System.out.println("Is the list a palindrome? Iteratively (Size is Known) -> "
				+ isPalindromeByIterativeWithStack(getLinkedList(nonpalindromes)));
		System.out.println("Is the list a palindrome? Iteratively (Size is Unknown) -> "
				+ isPalindromeByIterativeWithPointers(getLinkedList(nonpalindromes)));
		System.out.println("Is the list a palindrome? Reverse & Compare -> "
				+ isPalindromeByReverseAndCompare(getLinkedList(nonpalindromes)));
		System.out.println(
				"Is the list a palindrome? Recursively -> " + isPalindromeByRecursive(getLinkedList(nonpalindromes)));
	}
}
