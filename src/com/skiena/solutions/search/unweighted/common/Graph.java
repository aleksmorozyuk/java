// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author aleks.
 */
public class Graph {

    private boolean _directed;
    private List<Vertex> _vertices;

    public Graph(boolean directed) {
        this._directed = directed;
        this._vertices = new ArrayList<>();
    }

    public boolean getDirected() {
        return this._directed;
    }

    public void addVertex(Vertex vertex) {
        this._vertices.add(vertex);
    }

    public List<Vertex> getVertices() {
        if(!this._vertices.isEmpty()) {
            return this._vertices;
        } else {
            return Collections.emptyList();
        }
    }
}