// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.bfs;

import com.skiena.solutions.search.unweighted.common.Vertex;
import com.skiena.solutions.search.unweighted.common.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author aleks.
 */
public abstract class BreadFirstSearch {
    final protected Graph graph;
    final protected int[] parent;
    final protected boolean[] processed;
    final protected boolean[] discovered;

    protected BreadFirstSearch(Graph graph) {
        this.graph = graph;

        List<Vertex> vertices = this.graph.getVertices();
        this.parent = new int[vertices.size()];
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

    protected void processVertexLate(Vertex vertex) {
        System.out.println("processed vertex (end) '" + vertex.getVertexID() + "'.");
    }

    protected void processVertexEarly(Vertex vertex) {
        System.out.println("processed vertex (start) '" + vertex.getVertexID() + "'.");
    }

    protected void processEdge(Vertex start, Vertex end) {
        System.out.println("processed edge from '" + start.getVertexID() + "' to '" + end.getVertexID() + "'.");
    }

    protected void breadFirstSearch(final Vertex vertex) {

        if(vertex == null) {
            return;
        }

        // Create a queue with a vertex node as a first node
        Queue<Vertex> queue = new LinkedList<Vertex>(){{
            this.add(vertex);
        }};

        // Mark the vertex node as discovered, 1st one to look at
        this.discovered[vertex.getVertexID()] = true;

        while(!queue.isEmpty()) {

            // Remove the current node to process
            Vertex current = queue.remove();

            // PRE-PROCESS: the current node
            this.processVertexEarly(current);

            // Mark the current node as processed
            this.processed[current.getVertexID()] = true;

            // Iterate over the current node children and traverse them first
            for(Vertex child : current.getDescendants()) {

                // If child hasn't been discovered yet, mark it as discovered.
                if (!this.discovered[child.getVertexID()]) {
                    queue.add(child);
                    this.discovered[child.getVertexID()] = true;
                    this.parent[child.getVertexID()] = current.getVertexID();
                }

                // If child hasn't been processed yet, process node-child vertex
                if(!this.processed[child.getVertexID()] || graph.getDirected()) {
                    // PROCESS: the node-child vertex
                    this.processEdge(current, child);
                }
            }

            // POST-PROCESS: the current node
            this.processVertexLate(current);
        }
    }
}
