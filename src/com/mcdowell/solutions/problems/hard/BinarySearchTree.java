// Copyright (c) 2016 Boomi, Inc.
package com.mcdowell.solutions.problems.hard;

import com.mcdowell.solutions.problems.hard.common.Node;

/**
 * @author aleks.
 */
public class BinarySearchTree {

    static class NodePair {
        private Node _head;
        private Node _tail;
        public NodePair(Node head, Node tail) {
            this._head = head;
            this._tail = tail;
        }

        public Node getHead() {
            return this._head;
        }

        public void setHead(Node head) {
            this._head = head;
        }

        public Node getTail() {
            return this._tail;
        }

        public void setTail(Node tail) {
            this._tail = tail;
        }
    }

    public static void printDoublyLinkedList(Node root) {
        while(root != null) {
            System.out.print(root.getData() + " ");
            root = root.getRight(); // Left --> previous; Right --> Next
        }
    }

    /*
     *  There can be different ways in which a tree can be converted into a doubly linked list.
     *  Based on traversal order, order of nodes in resultant doubly linked would be different.
     *  For example, post-order traversal would result doubly linked list with roots added last.
     */
    public static void convertByPostOrderTraversal(Node node, NodePair pair) {
        if(node == null) {
            return;
        }

        // Recurse down to the left-most node of the tree
        convertByPostOrderTraversal(node.getLeft(), pair);

        // Recurse the right sub-tree of the current node
        convertByPostOrderTraversal(node.getRight(), pair);

        if(pair.getHead() == null) {
            // Head of DLL is the root node of the tree
            pair.setHead(node);
        }

        if(pair.getTail() != null) {
            node.setLeft(null);
            node.setRight(null);
            // Set tail's right pointer to current node
            pair.getTail().setRight(node);
        }

        // Set current node as tail of DLL
        pair.setTail(node);
    }

    /*
     *  There can be different ways in which a tree can be converted into a doubly linked list.
     *  Based on traversal order, order of nodes in resultant doubly linked would be different.
     *  For example, pre-order traversal would result doubly linked list with roots added first.
     */
    public static void convertByPreOrderTraversal(Node node, NodePair pair) {
        if(node == null) {
            return;
        }

        if(pair.getHead() == null) {
            // Head of DLL is the root node of the tree
            pair.setHead(node);
        }

        Node temporary = node.getRight();
        if(pair.getTail() != null) {
            // Set tail's right pointer to current node
            pair.getTail().setRight(node);
        }

        // Set current node as tail of DLL
        pair.setTail(node);

        // Recurse down to the left-most node of the tree
        convertByPreOrderTraversal(node.getLeft(), pair);

        // Recurse the right sub-tree of the current node
        convertByPreOrderTraversal(temporary, pair);
    }

    /*
     *  There can be different ways in which a tree can be converted into a doubly linked list.
     *  Based on traversal order, order of nodes in resultant doubly linked would be different.
     *  For example, in-order traversal would result doubly linked list be in sorted order.
     */
    public static void convertByInOrderTraversal(Node node, NodePair pair) {
        if(node == null) {
            return;
        }

        // Recurse down to the left-most node of the tree
        convertByInOrderTraversal(node.getLeft(), pair);

        if(pair.getHead() == null) {
            // Head of DLL is the left-most node of the tree
            pair.setHead(node);
        } else {
            // Set left pointer to current tail node
            node.setLeft(pair.getTail());
            // Set tail's right pointer to current node
            pair.getTail().setRight(node);
        }

        // Set current node as tail of DLL
        pair.setTail(node);

        // Recurse the right sub-tree of the current node
        convertByInOrderTraversal(node.getRight(), pair);
    }

    public static Node createBinarySearchTree() {
        return new Node(10,
                // Left sub-tree of the root
                new Node(6, new Node(3, null, null), new Node(9, null, null)),
                // Right sub-tree of the root
                new Node(15, new Node(11, null, null), new Node(17, null, null)));
    }

    public static void main(String[] args) {

        NodePair preOrder = new NodePair(null, null);
        convertByPreOrderTraversal(createBinarySearchTree(), preOrder);

        System.out.print("Doubly Linked List (Pre-Order): ");
        printDoublyLinkedList(preOrder.getHead());
        System.out.println();

        NodePair inOrder = new NodePair(null, null);
        convertByInOrderTraversal(createBinarySearchTree(), inOrder);

        System.out.print("Doubly Linked List (In-Order): ");
        printDoublyLinkedList(inOrder.getHead());
        System.out.println();

        NodePair postOrder = new NodePair(null, null);
        convertByPostOrderTraversal(createBinarySearchTree(), postOrder);

        System.out.print("Doubly Linked List (Post-Order): ");
        printDoublyLinkedList(postOrder.getHead());
        System.out.println();
    }
}
