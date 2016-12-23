// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.bfs;

import com.skiena.solutions.search.unweighted.common.Graph;
import com.skiena.solutions.search.unweighted.common.GraphHelper;
import com.skiena.solutions.search.unweighted.common.Vertex;

/**
 * @author aleks.
 */
public class FindingPaths extends BreadFirstSearch {

    public FindingPaths(Graph graph) {
        super(graph);
    }

    private void findPath(int start, int end) {
        if(start == end || (end == -1)) {
            System.out.print("'" + start + "'");
        } else {
            this.findPath(start, this.parent[end]);
            System.out.print("'" + end + "'");
        }
    }

    public void findPath(Vertex start) {

        // Run Bread First Search
        this.breadFirstSearch(start);

        // Find paths from root to every getOtherVertexID node of the graph
        int verticesCount = this.graph.getVertices().size();
        for(int index = 1; index < verticesCount - 1; index++) {
            Vertex end = this.graph.getVertices().get(index);

            System.out.print("\nPath from '" + start.getVertexID()
                    + "' to '" + end.getVertexID() + "' --> ");

            this.findPath(start.getVertexID(), end.getVertexID());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final Graph graph = GraphHelper.createUndirectedGraphWithCycles();
        new FindingPaths(graph).findPath(graph.getVertices().get(0));
    }
}
