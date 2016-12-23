package com.mcdowell.solutions.linkedlists;

public class ListsIntersection {

	public static class Result {
		private int size;
		private LinkedListNode<Integer> tail;

		public Result(int size, LinkedListNode<Integer> tail) {
			this.size = size;
			this.tail = tail;
		}

		public int getSize() {
			return this.size;
		}

		public LinkedListNode<Integer> getTail() {
			return this.tail;
		}
	}

	private static Result getTailAndSize(LinkedListNode<Integer> list) {
		if (list == null) {
			return null;
		}

		int size = 1;
		LinkedListNode<Integer> current = list;
		while (current.getNextNode() != null) {
			size += 1;
			current = current.getNextNode();
		}
		return new Result(size, current);
	}

	private static LinkedListNode<Integer> getKthNode(int kthNode, LinkedListNode<Integer> head) {
		LinkedListNode<Integer> current = head;
		while (kthNode > 0 && current != null) {
			current = current.getNextNode();
			kthNode -= 1;
		}
		return current;
	}

	private static LinkedListNode<Integer> findIntersection(LinkedListNode<Integer> list1,
			LinkedListNode<Integer> list2) {

		if (list1 == null || list2 == null) {
			return null;
		}

		// Get result containing tail node + size
		Result result1 = getTailAndSize(list1);
		Result result2 = getTailAndSize(list2);

		// If two list intersect, they must have same tail node
		if (result1.getTail() != result2.getTail()) {
			return null;
		}

		// Figure out what list is longer and what list is shorter
		LinkedListNode<Integer> longer = result1.getSize() < result2.getSize() ? list2 : list1;
		LinkedListNode<Integer> shorter = result1.getSize() < result2.getSize() ? list1 : list2;

		// Move longer list pointer to the node matching shorter list size
		longer = getKthNode(Math.abs(result1.getSize() - result2.getSize()), longer);

		// Move both pointer till they meet
		while (shorter != longer) {
			longer = longer.getNextNode();
			shorter = shorter.getNextNode();
		}

		return longer;
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
		final int[] listOne = { 4, 6 };
		final int[] listTwo = { 3, 1, 5, 9, 7, 2, 1 };

		LinkedListNode<Integer> linkedList1 = getLinkedList(listOne);
		LinkedListNode<Integer> linkedList2 = getLinkedList(listTwo);

		// Create an intersection at node with value 7
		LinkedListNode<Integer> pointer1 = linkedList1;
		LinkedListNode<Integer> pointer2 = linkedList2;
		for (int index = 0; index < listTwo.length; index++) {
			if (index > 3 && pointer1.getNextNode() == null) {
				pointer1.setNextNode(pointer2);
			}

			pointer2 = pointer2.getNextNode();
			if (pointer1.getNextNode() != null) {
				pointer1 = pointer1.getNextNode();
			}
		}

		printLintedList(linkedList1);
		printLintedList(linkedList2);
		System.out.println("Intersection is @ # : " + findIntersection(linkedList1, linkedList2).getNodeData());
	}
}
