// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.dfs;

import com.skiena.solutions.search.unweighted.common.Graph;
import com.skiena.solutions.search.unweighted.common.GraphHelper;
import com.skiena.solutions.search.unweighted.common.Vertex;

import java.util.Stack;

/**
 * @author aleks.
 */
public class TopologicalSort extends DeapthFirstSearch {

    private Stack<Vertex> _vertexStack;

    public TopologicalSort(Graph graph) {
        super(graph);
        this._vertexStack = new Stack<>();
    }

    protected void processVertexLate(Vertex vertex) {
        super.processVertexLate(vertex);
        this._vertexStack.push(vertex);
    }

    private void printTopologicalSort() {
        System.out.print("\n[");
        while(!this._vertexStack.isEmpty()) {
            System.out.print(this._vertexStack.pop().getVertexID());
            if(!this._vertexStack.isEmpty() && this._vertexStack.peek() != null) {
                System.out.print(",");
            }
        }
        System.out.print("]\n");
    }

    private void topologicalSort() {
        for(int index = 0; index < this.graph.getVertices().size(); index++) {
            if(!this.discovered[index]) {
                this.deapthFirstSearch(this.graph.getVertices().get(index));
            }
        }

        this.printTopologicalSort();
    }

    protected void processEdge(Vertex start, Vertex end) {

        super.processEdge(start, end);

        final Edge edge = this.edgeClassification(start.getVertexID(), end.getVertexID());
        if(edge == DeapthFirstSearch.Edge.BACK) {
            System.out.println("WARNING: directed cycle found; not a DAG.");
        }
    }

    public static void main(String[] args) {
        new TopologicalSort(GraphHelper.createDirecredGraph()).topologicalSort();
    }
}
