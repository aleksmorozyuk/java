// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.dfs;

import com.skiena.solutions.search.unweighted.common.Graph;
import com.skiena.solutions.search.unweighted.common.GraphHelper;
import com.skiena.solutions.search.unweighted.common.Vertex;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author aleks.
 */
public class ConnectedComponents extends DeapthFirstSearch {

    private int componentsFound;
    private Stack<Vertex> _vertexStack;
    private int[] scc; /* strong component number for each vertex */
    private int[] low; /* oldest vertex surely in component of v */

    public ConnectedComponents(Graph graph) {
        super(graph);
        this.componentsFound = 0;
        this._vertexStack = new Stack<>();
        this.low = new int[this.graph.getVertices().size()];
        this.scc = new int[this.graph.getVertices().size()];
    }

    private void stronglyConnectedComponents() {
        for(int index = 0; index < this.graph.getVertices().size(); index++) {
            this.scc[index] = -1;
            this.low[index] = index;
        }

        for(int index = 0; index < this.graph.getVertices().size(); index++) {
            if(!this.discovered[index]) {
                this.deapthFirstSearch(this.graph.getVertices().get(index));
            }
        }

        final Set<Integer> componentVertices = new HashSet<Integer>(){{
            for(int index = 0; index < low.length; index++) {
                this.add(low[index]);
            }
        }};

        System.out.println((this.componentsFound == 1 ? "\nStrongly Connected Graph --> " : "\nWeakly Connected Graph --> ")
                + "Strongly Connected Components: " + this.componentsFound + " Oldest Vertices: " + componentVertices.toString());
    }

    protected void processEdge(Vertex start, Vertex end) {

        super.processEdge(start, end);

        Edge edge = this.edgeClassification(start.getVertexID(), end.getVertexID());

        if(edge.equals(DeapthFirstSearch.Edge.BACK)) {
            if(this.entryTimes[end.getVertexID()] < this.entryTimes[this.low[start.getVertexID()]]) {
                this.low[start.getVertexID()] = end.getVertexID();
            }
        }

        if(edge.equals(DeapthFirstSearch.Edge.CROSS)) {
            if(this.scc[end.getVertexID()] == -1) {  /* component not yet assigned */
                if(this.entryTimes[end.getVertexID()] < this.entryTimes[this.low[start.getVertexID()]]) {
                    this.low[start.getVertexID()] = end.getVertexID();
                }
            }
        }
    }

    protected void processVertexLate(Vertex vertex) {

        super.processVertexLate(vertex);

        // Edge (parent[v],v) cuts off scc
        if(this.low[vertex.getVertexID()] == vertex.getVertexID()) {
            this.popComponent(vertex);
        }

        if(this.parent[vertex.getVertexID()] != -1 // root node doesn't have a parent so ignore it...
                && this.entryTimes[this.low[vertex.getVertexID()]] < this.entryTimes[this.low[this.parent[vertex.getVertexID()]]]) {
            this.low[this.parent[vertex.getVertexID()]] = this.low[vertex.getVertexID()];
        }
    }

    protected void processVertexEarly(Vertex vertex) {
        super.processVertexEarly(vertex);
        this._vertexStack.push(vertex);
    }

    private void popComponent(Vertex vertex) {
        Vertex fromStack = null;
        this.componentsFound = this.componentsFound + 1;
        this.scc[vertex.getVertexID()] = this.componentsFound;

        while(null != (fromStack = this._vertexStack.pop())
                && fromStack.getVertexID() != vertex.getVertexID()) {
            this.scc[fromStack.getVertexID()] = this.componentsFound;
        }
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
        new ConnectedComponents(GraphHelper.createStronglyConnectedGraph()).stronglyConnectedComponents();
    }
}
