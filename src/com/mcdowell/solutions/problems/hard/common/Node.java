// Copyright (c) 2016 Boomi, Inc.
package com.mcdowell.solutions.problems.hard.common;

/**
 * @author aleks.
 */
public class Node {
    private int _data;
    private Node _left;
    private Node _right;

    public Node(int data, Node left, Node right) {
        this._data = data;
        this._left = left;
        this._right = right;
    }

    public int getData() {
        return this._data;
    }

    public void setData(int data) {
        this._data = data;
    }

    public Node getLeft() {
        return this._left;
    }

    public void setLeft(Node left) {
        this._left = left;
    }

    public Node getRight() {
        return this._right;
    }

    public void setRight(Node right) {
        this._right = right;
    }
}
