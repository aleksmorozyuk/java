// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.dfs;

import com.skiena.solutions.search.unweighted.common.Graph;
import com.skiena.solutions.search.unweighted.common.Vertex;

import java.util.List;

/**
 * @author aleks.
 */
public abstract class DeapthFirstSearch {
    protected int time;
    protected boolean finish;
    final protected Graph graph;
    final protected int[] parent;
    final protected int[] exitTimes;
    final protected int[] entryTimes;
    final protected boolean[] processed;
    final protected boolean[] discovered;

    protected enum Edge {
        UNCLASSIFIED(-1), TREE(0), BACK(1), FORWARD(2), CROSS(3);
        Edge(int edgeID) {
            this.edgeID = edgeID;
        }

        private int edgeID;
        public int getEdgeID() {
            return this.edgeID;
        }
    }

    protected DeapthFirstSearch(Graph graph) {
        this.time = 0;
        this.graph = graph;
        this.finish = false;

        List<Vertex> vertices = this.graph.getVertices();
        this.parent = new int[vertices.size()];
        this.exitTimes = new int[vertices.size()];
        this.entryTimes = new int[vertices.size()];
        this.processed = new boolean[vertices.size()];
        this.discovered = new boolean[vertices.size()];

        this.initializeSearch(vertices.size());
    }

    private void initializeSearch(int verticesCount) {
        for(int index = 0; index < verticesCount; index++) {
            this.parent[index] = -1;
            this.processed[index] = false;
            this.discovered[index] = false;
        }
    }

    protected Edge edgeClassification(int start, int end) {
        if (this.parent[end] == start) {
            return DeapthFirstSearch.Edge.TREE;
        }

        if (!this.processed[end] && this.discovered[end]) {
            return DeapthFirstSearch.Edge.BACK;
        }

        if (this.processed[end] && (this.entryTimes[end] > this.entryTimes[start])) {
            return DeapthFirstSearch.Edge.FORWARD;
        }

        if (this.processed[end] && (this.entryTimes[end] < this.entryTimes[start])) {
            return DeapthFirstSearch.Edge.CROSS;
        }

        System.out.println("Warning: unclassified edge (" + start + "," + end + ")");

        return DeapthFirstSearch.Edge.UNCLASSIFIED;
    }

    protected void processVertexLate(Vertex vertex) {
        System.out.println("processed vertex (end) '" + vertex.getVertexID() + "'.");
    }

    protected void processVertexEarly(Vertex vertex) {
        System.out.println("processed vertex (start) '" + vertex.getVertexID() + "'.");
    }

    protected void processEdge(Vertex start, Vertex end) {
        System.out.println("processed edge from '" + start.getVertexID() + "' to '" + end.getVertexID() + "'.");
    }

    protected void deapthFirstSearch(final Vertex vertex) {

        if(vertex == null) {
            return;
        }

        if(this.finish) {
            return;
        }

        // Mark the current node as discovered
        this.discovered[vertex.getVertexID()] = true;
        this.entryTimes[vertex.getVertexID()] = this.time++;

        // PRE-PROCESS: the current node
        this.processVertexEarly(vertex);

        for(Vertex child : vertex.getDescendants()) {
            if (this.processVertexToChildEdge(vertex, child)) {
                return;
            }
        }

        // POST-PROCESS: the current node
        this.processVertexLate(vertex);

        this.exitTimes[vertex.getVertexID()] = this.time++;

        // Mark the current node as processed
        this.processed[vertex.getVertexID()] = true;
    }

    protected boolean processVertexToChildEdge(Vertex vertex, Vertex child) {
        if (this.handleUndiscoveredChild(vertex, child)) {
            return true;
        }
        return false;
    }

    protected boolean handleUndiscoveredChild(Vertex vertex, Vertex child) {
        if (!this.discovered[child.getVertexID()]) {
            this.discovered[child.getVertexID()] = true;
            this.parent[child.getVertexID()] = vertex.getVertexID();

            // PROCESS: the node-child vertex
            this.processEdge(vertex, child);

            // Process child node; complete processing the current node later
            this.deapthFirstSearch(child);

            if(this.finish) {
                return true;
            }
        }
        return false;
    }
}
