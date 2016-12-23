package com.mcdowell.solutions.linkedlists;

public class ListPartition {

	private static LinkedListNode<Integer> createPartitionStable(int value, LinkedListNode<Integer> head) {
		LinkedListNode<Integer> lessThanEnd = null, lessThanStart = null;
		LinkedListNode<Integer> greaterThanEnd = null, greaterThanStart = null;

		while (head != null) {
			LinkedListNode<Integer> next = head.getNextNode();
			head.setNextNode(null); // Remove next from pointer to the head

			// Split a given list into two smaller once, one less than value
			// another one greater than value
			if (head.getNodeData() < value) {
				if (lessThanStart == null) {
					lessThanStart = head;
					lessThanEnd = lessThanStart;
				} else {
					lessThanEnd.setNextNode(head);
					lessThanEnd = head;
				}
			} else {
				if (greaterThanStart == null) {
					greaterThanStart = head;
					greaterThanEnd = greaterThanStart;
				} else {
					greaterThanEnd.setNextNode(head);
					greaterThanEnd = head;
				}
			}
			head = next; // Move the header pointer to the next node of the list
		}

		if (lessThanStart == null) { // value matched smallest value in the list
			return greaterThanStart;
		}

		lessThanEnd.setNextNode(greaterThanStart); // Merge two lists together
		return lessThanStart;
	}

	private static LinkedListNode<Integer> createPartitionUnstable(int value, LinkedListNode<Integer> node) {
		LinkedListNode<Integer> head = node;
		LinkedListNode<Integer> tail = node;

		while (node != null) {
			LinkedListNode<Integer> next = node.getNextNode();
			if (node.getNodeData() < value) { // Put the element at the head
				node.setNextNode(head);
				head = node;
			} else {	// Put the element at the tail
				tail.setNextNode(node);
				tail = node;
			}
			node = next; // Move the header pointer to the next node of the list
		}

		tail.setNextNode(null); // Make sure the tail's next node is null
		return head;
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

	public static void main(String[] args) {
		final int[] numbers = { 3, 5, 8, 5, 10, 2, 1 };
		LinkedListNode<Integer> linkedList = new LinkedListNode<Integer>(numbers[0]);
		for (int index = 1; index < numbers.length; index++) {
			linkedList.append(numbers[index]);
		}
		printLintedList(linkedList);
		printLintedList(createPartitionStable(5, linkedList));
		printLintedList(createPartitionUnstable(5, linkedList));
	}
}
