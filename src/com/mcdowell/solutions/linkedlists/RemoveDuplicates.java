package com.mcdowell.solutions.linkedlists;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {

	private static LinkedListNode<Integer> createLinkedList(final Integer[] integers) {
		LinkedListNode<Integer> newLinkedList = new LinkedListNode<Integer>(integers[0]);
		for (int index = 1; index < integers.length; index++) {
			newLinkedList.append(integers[index]);
		}
		return newLinkedList;
	}

	private static LinkedListNode<Integer> removeDuplicatesWithBuffer(LinkedListNode<Integer> current) {
		LinkedListNode<Integer> previous = null;
		final Set<Integer> uniqueIntegers = new HashSet<Integer>();
		while (current != null) {
			if (uniqueIntegers.contains(current.getNodeData())) {
				previous.setNextNode(current.getNextNode());
			} else {
				uniqueIntegers.add(current.getNodeData());
				previous = current.getNextNode();
			}
			current = current.getNextNode();
		}

		return createLinkedList(uniqueIntegers.toArray(new Integer[uniqueIntegers.size()]));
	}

	private static LinkedListNode<Integer> removeDuplicatesWithPointers(LinkedListNode<Integer> headOfList) {
		LinkedListNode<Integer> slowRunner = headOfList;
		final Set<Integer> uniqueIntegers = new HashSet<Integer>();
		while (slowRunner != null) {
			LinkedListNode<Integer> fastRunner = slowRunner;
			while (fastRunner.getNextNode() != null) {
				if (fastRunner.getNextNode().getNodeData() == slowRunner.getNodeData()) {
					fastRunner.setNextNode(fastRunner.getNextNode().getNextNode());
				} else {
					uniqueIntegers.add(fastRunner.getNodeData());
					fastRunner = fastRunner.getNextNode();
					if (fastRunner.getNextNode() == null) {
						uniqueIntegers.add(fastRunner.getNodeData());
					}
				}
			}
			slowRunner = slowRunner.getNextNode();
		}

		return createLinkedList(uniqueIntegers.toArray(new Integer[uniqueIntegers.size()]));
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
		final int[] numbers = new int[10];
		final LinkedListNode<Integer> linkedList = new LinkedListNode<Integer>(0);
		for (int index = 0; index < numbers.length; index++) {
			linkedList.append(index + 1);
			if (index % 2 == 0) {
				linkedList.append(index + 1);
			}
		}
		printLintedList(linkedList);
		printLintedList(removeDuplicatesWithBuffer(linkedList));
		printLintedList(removeDuplicatesWithPointers(linkedList));
	}
}
