package com.mcdowell.solutions.linkedlists;

public class SumOfIntegersLists {

	public static class PartialSum {
		private int carry = 0;
		private LinkedListNode<Integer> sum = null;

		public PartialSum(int carry, LinkedListNode<Integer> sum) {
			this.sum = sum;
			this.carry = carry;
		}

		public int getCarry() {
			return this.carry;
		}

		public LinkedListNode<Integer> getSum() {
			return this.sum;
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

	private static LinkedListNode<Integer> addListsForward(LinkedListNode<Integer> list1,
			LinkedListNode<Integer> list2) {

		// Get lengths of both lists
		int length1 = getLinkedListSize(list1);
		int length2 = getLinkedListSize(list2);

		// Pad lists if different in lengths
		if (length1 < length2) {
			list1 = padList(list1, length2 - length1);
		} else {
			list2 = padList(list2, length1 - length2);
		}

		// Compute partial sum of padded lists
		PartialSum partialSum = addListForwardHelper(list1, list2);

		if (partialSum.getCarry() == 0) { // There is no carry, return the sum
			return partialSum.getSum();
		} else {
			// There is a carry that should be inserted in front
			return insertBefore(partialSum.getCarry(), partialSum.getSum());
		}
	}

	private static PartialSum addListForwardHelper(LinkedListNode<Integer> list1, LinkedListNode<Integer> list2) {

		if (list1 == null || list2 == null) {
			return new PartialSum(0, null);
		}

		// We need to recurse all the way back to the end of lists to start
		// summing the numbers
		PartialSum partialSum = addListForwardHelper(list1.getNextNode(), list2.getNextNode());

		// Compute the value of adding two current numbers plus any carry over
		int value = partialSum.getCarry() + list1.getNodeData() + list2.getNodeData();

		// Compute the final result taking into account the carry over number
		LinkedListNode<Integer> result = insertBefore(value % 10, partialSum.getSum());

		return new PartialSum(value / 10, result);
	}

	private static LinkedListNode<Integer> padList(LinkedListNode<Integer> list, int padding) {
		LinkedListNode<Integer> head = list;
		for (int index = 0; index < padding; index++) {
			insertBefore(0, head);
		}
		return head;
	}

	private static LinkedListNode<Integer> insertBefore(int data, LinkedListNode<Integer> list) {
		LinkedListNode<Integer> node = new LinkedListNode<Integer>(data);
		if (list != null) {
			node.setNextNode(list);
		}
		return node;
	}

	private static LinkedListNode<Integer> addListsReversed(int carry, LinkedListNode<Integer> list1,
			LinkedListNode<Integer> list2) {
		if (list1 == null && list2 == null && carry == 0) {
			return null;
		}

		// Compute the value of adding two current numbers plus any carry over 
		int value = carry;
		if (list1 != null) {
			value += list1.getNodeData();
		}

		if (list2 != null) {
			value += list2.getNodeData();
		}

		// Compute the final result taking into account the carry over number
		LinkedListNode<Integer> result = new LinkedListNode<Integer>(value % 10);

		// If one of two lists is not empty recurse to add next two integers
		if (list1 != null || list2 != null) {
			LinkedListNode<Integer> more = addListsReversed(value >= 10 ? 1 : 0,
					list1 == null ? null : list1.getNextNode(), list2 == null ? null : list2.getNextNode());
			result.setNextNode(more);
		}

		return result;
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

	private static void testAddListForward() {
		final int[] number1 = { 6, 1, 7 }, number2 = { 2, 9, 5 };
		LinkedListNode<Integer> list1 = new LinkedListNode<Integer>(number1[0]);
		LinkedListNode<Integer> list2 = new LinkedListNode<Integer>(number2[0]);
		for (int index = 1; index < number1.length; index++) {
			list1.append(number1[index]);
			list2.append(number2[index]);
		}

		printLintedList(list1);
		printLintedList(list2);

		printLintedList(addListsForward(list1, list2));
	}

	private static void testAddListReversed() {
		final int[] number1 = { 7, 1, 6 }, number2 = { 5, 9, 2 };
		LinkedListNode<Integer> list1 = new LinkedListNode<Integer>(number1[0]);
		LinkedListNode<Integer> list2 = new LinkedListNode<Integer>(number2[0]);
		for (int index = 1; index < number1.length; index++) {
			list1.append(number1[index]);
			list2.append(number2[index]);
		}

		printLintedList(list1);
		printLintedList(list2);

		printLintedList(addListsReversed(0, list1, list2));
	}

	public static void main(String[] args) {
		testAddListForward();
		testAddListReversed();
	}
}
