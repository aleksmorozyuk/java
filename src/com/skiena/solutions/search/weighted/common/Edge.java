// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.weighted.common;

/**
 * @author aleks.
 */
public class Edge implements Comparable<Edge> {

    private final double _weight;
    private final int _vertexOne;
    private final int _vertexTwo;

    public Edge(int vertexOne, int vertexTwo, double weight) {
        this._weight = weight;
        this._vertexOne = vertexOne;
        this._vertexTwo = vertexTwo;
    }

    public double getWeight() {
        return this._weight;
    }

    public int getVertexOne() {
        return this._vertexOne;
    }

    public int getVertexTwo() {
        return this._vertexTwo;
    }

    public int getOtherVertexID(int vertex) {
        if (vertex == _vertexOne) {
            return _vertexTwo;
        } else if (vertex == _vertexTwo) {
            return _vertexOne;
        } else {
            return -1;
        }
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this._weight, that._weight);
    }

    public String toString() {
        return String.format("%d-%d %.5f", _vertexOne, _vertexTwo, _weight);
    }
}