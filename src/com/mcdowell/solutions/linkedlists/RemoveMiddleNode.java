package com.mcdowell.solutions.linkedlists;

public class RemoveMiddleNode {

	private static int getLinkedListSize(LinkedListNode<Integer> linkedList) {
		int linkedListSize = 0;
		LinkedListNode<Integer> node = linkedList;
		while (node != null) {
			linkedListSize += 1;
			node = node.getNextNode();
		}
		return linkedListSize;
	}

	private static void removeNode(int nodeToRemove, LinkedListNode<Integer> linkedList) {
		final int linkedListSize = getLinkedListSize(linkedList);
		if (nodeToRemove == 0 || nodeToRemove == linkedListSize) {
			System.err.println("ERROR: Can't remove node with index " + nodeToRemove);
			return;
		}

		LinkedListNode<Integer> current = linkedList;
		for (int index = 0; index < nodeToRemove; index++) {
			current = current.getNextNode();
		}

		LinkedListNode<Integer> next = current.getNextNode();
		current.setNextNode(next.getNextNode());
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
		removeNode(2, linkedList);
		printLintedList(linkedList);
		removeNode(10, linkedList);
		printLintedList(linkedList);
	}
}
