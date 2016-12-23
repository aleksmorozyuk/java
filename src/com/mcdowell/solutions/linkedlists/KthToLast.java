package com.mcdowell.solutions.linkedlists;

public class KthToLast {

	public static class Index {
		private int value;

		public Index(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public void incrementValue() {
			this.value += 1;
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

	private static Integer getKthToLastRecursiveNaive(int kthToFind, LinkedListNode<Integer> linkedList) {

		if (linkedList == null) {
			return 0;
		}

		int index = getKthToLastRecursiveNaive(kthToFind, linkedList.getNextNode()) + 1;

		if (index == kthToFind) {
			System.out.println(kthToFind + "th to find -> recursively naive: " + linkedList.getNodeData());
		}

		return index;
	}

	private static int getKthToLastRecursiveWithClass(int kthToFind, LinkedListNode<Integer> linkedList) {
		final Index elementValue = new Index(0);
		return getKthToLastRecursiveWithClass(kthToFind, elementValue, linkedList).getNodeData();
	}

	private static LinkedListNode<Integer> getKthToLastRecursiveWithClass(int kthToFind, Index index,
			LinkedListNode<Integer> linkedList) {

		if (linkedList == null) {
			return linkedList;
		}

		LinkedListNode<Integer> node = getKthToLastRecursiveWithClass(kthToFind, index, linkedList.getNextNode());
		index.incrementValue();
		if (index.getValue() == kthToFind) {
			return linkedList;
		}

		return node;
	}

	private static Integer getKthToLastNaive(int kthToFind, int linkedListSize, LinkedListNode<Integer> linkedList) {
		LinkedListNode<Integer> node = linkedList;
		for (int index = 0; index < (linkedListSize - kthToFind); index++) {
			node = node.getNextNode();
		}
		return node.getNodeData();
	}

	private static void printLintedList(LinkedListNode<?> linkedList) {
		final StringBuilder sb = new StringBuilder();
		LinkedListNode<?> node = linkedList.getNextNode();
		while (node != null) {
			sb.append(node.getNodeData());
			node = node.getNextNode();
		}
		System.out.println(sb.toString());
	}

	private static Integer getKthToLastIterative(int kthToFind, LinkedListNode<Integer> linkedList) {
		LinkedListNode<Integer> pointer1 = linkedList;
		LinkedListNode<Integer> pointer2 = linkedList;
		for (int index = 0; index < kthToFind; index++) {
			if (pointer1 == null) {
				return null;
			}

			pointer1 = pointer1.getNextNode();
		}

		while (pointer1 != null) {
			pointer1 = pointer1.getNextNode();
			pointer2 = pointer2.getNextNode();
		}

		return pointer2.getNodeData();
	}

	public static void main(String[] args) {
		final int kthToFind = 5;
		final int[] numbers = new int[10];
		final LinkedListNode<Integer> linkedList = new LinkedListNode<Integer>(0);
		for (int index = 0; index < numbers.length; index++) {
			linkedList.append(index + 1);
			if (index % 2 == 0) {
				linkedList.append(index + 1);
			}
		}
		printLintedList(linkedList);
		System.out.println(kthToFind + "th to find -> naive: "
				+ getKthToLastNaive(kthToFind, getLinkedListSize(linkedList), linkedList));
		System.out.println(kthToFind + "th to find -> iteratively: " + getKthToLastIterative(kthToFind, linkedList));
		getKthToLastRecursiveNaive(kthToFind, linkedList);
		System.out.println(kthToFind + "th to find -> recursively with class: "
				+ getKthToLastRecursiveWithClass(kthToFind, linkedList));
	}
}
