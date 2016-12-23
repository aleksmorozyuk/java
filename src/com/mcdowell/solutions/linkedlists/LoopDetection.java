package com.mcdowell.solutions.linkedlists;

public class LoopDetection {

	private static LinkedListNode<Integer> findLoopStart(LinkedListNode<Integer> head) {
		LinkedListNode<Integer> slow = head;
		LinkedListNode<Integer> fast = head;

		// Find meeting point. It is LOOP_SIZE - k steps into the linked list.
		while (fast != null && fast.getNextNode() != null) {
			slow = slow.getNextNode();
			fast = fast.getNextNode().getNextNode();
			if (slow == fast) {
				break;
			}
		}

		// Error check - no meeting point, thus no loop exists.
		if (fast == null || fast.getNextNode() == null) {
			return null;
		}

		slow = head; // Move slow to the head, k steps from the loop start

		// Keep fast at meeting point, k steps from the loop start
		// Move both of them at the same time, they will meet @ loop start
		while (slow != fast) {
			slow = slow.getNextNode();
			fast = fast.getNextNode();
		}

		return fast;
	}

	private static LinkedListNode<Integer> getLinkedList(final int[] numbers) {
		LinkedListNode<Integer> linkedList = new LinkedListNode<Integer>(numbers[0]);
		for (int index = 1; index < numbers.length; index++) {
			linkedList.append(numbers[index]);
		}
		return linkedList;
	}

	public static void main(String[] args) {
		final int[] numbers = { 4, 6, 3, 1, 5, 9, 7, 2, 1 };
		LinkedListNode<Integer> linkedList = getLinkedList(numbers);

		// Make a circle @ middle node
		LinkedListNode<Integer> slow = linkedList;
		LinkedListNode<Integer> fast = linkedList;
		while (fast.getNextNode() != null) {
			slow = slow.getNextNode();
			fast = fast.getNextNode().getNextNode();
		}
		fast.setNextNode(slow);

		LinkedListNode<Integer> loopStartNode = findLoopStart(linkedList);

		System.out.println("Loop Starts @ # : " + ((loopStartNode == null) ? "none" : loopStartNode.getNodeData()));
	}
}
