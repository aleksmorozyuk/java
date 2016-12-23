// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.weighted.common;

/**
 * @author aleks.
 */
public class UnionFind {

    private int[] _parent;
    private int _componentCount;
    private byte[] _subtreeRank;

    public UnionFind(int componentCount) {
        this._componentCount = componentCount;
        this._parent = new int[this._componentCount];
        this._subtreeRank = new byte[this._componentCount];

        for (int index = 0; index < this._componentCount; index++) {
            this._parent[index] = index;
            this._subtreeRank[index] = 0;
        }
    }

    public boolean areConnected(int vertexOne, int vertexTwo) {
        return this.doFind(vertexOne) == this.doFind(vertexTwo);
    }

    public int doFind(int vertex) {
        // Find the root of a given vertex by walking up parents
        while (vertex != this._parent[vertex]) {
            this._parent[vertex] = this._parent[this._parent[vertex]];
            vertex = this._parent[vertex];
        }
        return vertex;
    }

    public void doUnion(int vertexOne, int vertexTwo) {
        // Find roots of the two given vertices
        final int rootOne = this.doFind(vertexOne);
        final int rootTwo = this.doFind(vertexTwo);

        // If roots match, the two vertices are part of same component.
        if (rootOne == rootTwo) {
            return;
        }

        // At this point, the two vertices are NOT part of same component. In order to
        // minimize the height of our tree, we want the root of smaller rank vertex to
        // point to the root of larger rank vertex; smaller ranked subtree becomes subtree
        // of larger ranked subtree; Merging in the smaller tree leaves the height unchanged
        // on the larger set of vertices.
        if(this._subtreeRank[rootOne] < this._subtreeRank[rootTwo]) {
            this._parent[rootOne] = rootTwo;
        } else if(this._subtreeRank[rootOne] > this._subtreeRank[rootTwo]){
            this._parent[rootTwo] = rootOne;
        } else {
            this._subtreeRank[rootOne]++;
            this._parent[rootTwo] = rootOne;
        }
        this._componentCount--;
    }
}
