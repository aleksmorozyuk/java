package com.mcdowell.solutions.linkedlists;

public class LinkedListNode<T extends Object> {
	private T nodeData = null;
	private LinkedListNode<T> nextNode = null;

	public LinkedListNode(T nodeData) {
		this.nodeData = nodeData;
	}

	public T getNodeData() {
		return this.nodeData;
	}

	public LinkedListNode<T> getNextNode() {
		return this.nextNode;
	}

	public void setNextNode(LinkedListNode<T> newNode) {
		this.nextNode = newNode;
	}

	public void append(T nodeData) {
		LinkedListNode<T> curNode = this;
		final LinkedListNode<T> newNode = new LinkedListNode<T>(nodeData);
		while (curNode.getNextNode() != null) {
			curNode = curNode.getNextNode();
		}
		curNode.setNextNode(newNode);
	}
}
