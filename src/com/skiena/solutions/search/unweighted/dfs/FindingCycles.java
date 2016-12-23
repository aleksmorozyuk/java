// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.dfs;

import com.skiena.solutions.search.unweighted.common.Graph;
import com.skiena.solutions.search.unweighted.common.GraphHelper;
import com.skiena.solutions.search.unweighted.common.Vertex;

/**
 * @author aleks.
 */
public class FindingCycles extends DeapthFirstSearch {
    public FindingCycles(Graph graph) {
        super(graph);
    }

    private void findPath(int start, int end) {
        if(start == end || (end == -1)) {
            System.out.println("'" + start + "'");
        } else {
            this.findPath(start, this.parent[end]);
            System.out.println("'" + end + "'");
        }
    }

    protected void processEdge(Vertex start, Vertex end) {

        super.processEdge(start, end);

        if(this.discovered[end.getVertexID()]
                && this.parent[start.getVertexID()] != end.getVertexID()) {  /* found back edge! */
            System.out.println("cycle found from '" + end.getVertexID() + "' to '" + start.getVertexID() + "'.");
            this.findPath(end.getVertexID(), start.getVertexID());
        }
    }

    protected boolean processVertexToChildEdge(Vertex vertex, Vertex child) {

        // Don't process node-child vertex before DFS child node
        if (!this.discovered[child.getVertexID()]) {
            this.discovered[child.getVertexID()] = true;
            this.parent[child.getVertexID()] = vertex.getVertexID();

            // Process child node; complete processing the current node later
            this.deapthFirstSearch(child);

            if(this.finish) {
                return true;
            }
        }

        // If child hasn't been processed yet, process node-child vertex
        if(!this.processed[child.getVertexID()] || graph.getDirected()) {
            // PROCESS: the node-child vertex
            this.processEdge(vertex, child);
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println("\nTraversing an un-directed graph without cycles\n");

        final Graph graphNoCycles = GraphHelper.createUndirectedGraphWithoutCycles();
        new FindingCycles(graphNoCycles).deapthFirstSearch(graphNoCycles.getVertices().get(0));

        System.out.println("\nTraversing an un-directed graph with cycles\n");

        final Graph graphWithCycles = GraphHelper.createUndirectedGraphWithCycles();
        new FindingCycles(graphWithCycles).deapthFirstSearch(graphWithCycles.getVertices().get(0));
    }
}
