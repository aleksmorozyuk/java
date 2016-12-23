// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.weighted.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author aleks.
 */
public class Graph {

    private int _edgeCount;
    final private int _vertexCount;
    final private boolean _directed;
    final private Map<Integer, List<Edge>> _edges;

    public Graph(int vertexCount, boolean directed) {
        this._edgeCount = 0;
        this._directed = directed;
        this._vertexCount = vertexCount;
        this._edges = new HashMap<>(this._vertexCount);
    }

    public int getEdgeCount() {
        return this._edgeCount;
    }

    public int getVertexCount() {
        return this._vertexCount;
    }

    public boolean getDirected() {
        return this._directed;
    }

    public int getDegree(int vertexID) {
        if(!this._edges.containsKey(vertexID)) {
            return -1;
        }
        return this._edges.get(vertexID).size();
    }

    public List<Edge> getEdges(int vertexID) {
        if(!this._edges.containsKey(vertexID)) {
            return Collections.emptyList();
        }
        return this._edges.get(vertexID);
    }

    public List<Edge> getAllEdges() {
        final List<Edge> allEdges = new ArrayList<>();
        for(Map.Entry<Integer, List<Edge>> entry : this._edges.entrySet()) {
            allEdges.addAll(entry.getValue());
        }
        return allEdges;
    }

    public void addEdge(final Edge edge) {

        if(this._edges.containsKey(edge.getVertexOne())) {
            this._edges.get(edge.getVertexOne()).add(edge);
        } else {
            this._edges.put(edge.getVertexOne(), new ArrayList<Edge>(){{
                this.add(edge);
            }});
        }

        if(!this._directed) {
            if(this._edges.containsKey(edge.getVertexTwo())) {
                this._edges.get(edge.getVertexTwo()).add(edge);
            } else {
                this._edges.put(edge.getVertexTwo(), new ArrayList<Edge>(){{
                    this.add(edge);
                }});
            }
        }

        this._edgeCount = this._edgeCount + 1;
    }
}