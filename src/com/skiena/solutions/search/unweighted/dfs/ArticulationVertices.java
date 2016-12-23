// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.dfs;

import com.skiena.solutions.search.unweighted.common.Graph;
import com.skiena.solutions.search.unweighted.common.GraphHelper;
import com.skiena.solutions.search.unweighted.common.Vertex;

/**
 * @author aleks.
 */
public class ArticulationVertices extends DeapthFirstSearch {
    private int[] treeOutOfDegree;      /* DFS tree out degree of vertex */
    private int[] reachableAncestor;    /* Earliest reachable ancestor of vertex */

    public ArticulationVertices(Graph graph) {
        super(graph);
        this.treeOutOfDegree = new int[graph.getVertices().size()];
        this.reachableAncestor = new int[graph.getVertices().size()];
    }

    protected void processEdge(Vertex start, Vertex end) {

        super.processEdge(start, end);

        final Edge edge = this.edgeClassification(start.getVertexID(), end.getVertexID());
        if(edge == DeapthFirstSearch.Edge.TREE) {
            this.treeOutOfDegree[start.getVertexID()] = this.treeOutOfDegree[start.getVertexID()] + 1;
        }

        if(edge == DeapthFirstSearch.Edge.BACK && this.parent[start.getVertexID()] != end.getVertexID()) {
            if(this.entryTimes[end.getVertexID()] < this.entryTimes[reachableAncestor[start.getVertexID()]]) {
                this.reachableAncestor[start.getVertexID()] = end.getVertexID();
            }
        }
    }

    protected void processVertexLate(Vertex vertex) {

        super.processVertexLate(vertex);

        if(this.parent[vertex.getVertexID()] < 0) {
            if(this.treeOutOfDegree[vertex.getVertexID()] > 1) {
                System.out.println(" root articulation vertex: " + vertex.getVertexID());
                return;
            }
        }

        boolean root = (this.parent[this.parent[vertex.getVertexID()]] < 0);
        if(this.reachableAncestor[vertex.getVertexID()] == this.parent[vertex.getVertexID()] && !root) {
            System.out.println(" parent articulation vertex: " + this.parent[vertex.getVertexID()]);
        }

        if(this.reachableAncestor[vertex.getVertexID()] == vertex.getVertexID()) {
            System.out.println(" bridge articulation vertex (parent): " + this.parent[vertex.getVertexID()]);

            if(this.treeOutOfDegree[vertex.getVertexID()] > 0) {
                System.out.println(" bridge articulation vertex (vertex): " + vertex.getVertexID());
            }
        }

        int timeOfVertex = this.entryTimes[this.reachableAncestor[vertex.getVertexID()]];
        int timeOfParentOfVertex = this.entryTimes[this.reachableAncestor[this.parent[vertex.getVertexID()]]];

        if(timeOfVertex < timeOfParentOfVertex) {
            this.reachableAncestor[this.parent[vertex.getVertexID()]] = this.reachableAncestor[vertex.getVertexID()];
        }
    }

    protected void processVertexEarly(Vertex vertex) {
        super.processVertexEarly(vertex);
        this.reachableAncestor[vertex.getVertexID()] = vertex.getVertexID();
    }

    protected boolean processVertexToChildEdge(Vertex vertex, Vertex child) {

        // Base version: process node-child vertex before DFS child node
        if (this.handleUndiscoveredChild(vertex, child)) {
            return true;
        }

        // If child hasn't been processed yet, process node-child vertex again
        if(!this.processed[child.getVertexID()] || this.graph.getDirected()) {
            // PROCESS: the node-child vertex
            this.processEdge(vertex, child);
        }

        return false;
    }

    public static void main(String[] args) {
        final Graph graph = GraphHelper.createUndirectedGraphWithCycles();
        new ArticulationVertices(graph).deapthFirstSearch(graph.getVertices().get(0));
    }
}
