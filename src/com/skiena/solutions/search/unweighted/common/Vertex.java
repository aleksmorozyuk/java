// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author aleks.
 */
public class Vertex {

    private int _vertexID;
    private List<Vertex> _descendants;

    public Vertex(int vertexID, int descendants) {
        this._vertexID = vertexID;
        this._descendants = new ArrayList<>(descendants);
    }

    public int getVertexID() {
        return this._vertexID;
    }

    public List<Vertex> getDescendants() {
        if(!this._descendants.isEmpty()) {
            return this._descendants;
        } else {
            return Collections.emptyList();
        }
    }

    public void addDescendant(Vertex descendant) {
        this._descendants.add(descendant);
    }
}
