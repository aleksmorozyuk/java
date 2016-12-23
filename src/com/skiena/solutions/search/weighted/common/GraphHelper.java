// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.weighted.common;

/**
 * @author aleks.
 */
public final class GraphHelper {

    final public static int SEVEN = 7;

    private GraphHelper() { // Utility class

    }

    public static Graph createWeigtedGraph(int verticesCount, boolean directed) {
        final Graph graph = new Graph(verticesCount, directed);
        graph.addEdge(new Edge(0, 1, 7));
        graph.addEdge(new Edge(0, 4, 9));
        graph.addEdge(new Edge(0, 6, 5));
        graph.addEdge(new Edge(1, 2, 5));
        graph.addEdge(new Edge(1, 3, 2));
        graph.addEdge(new Edge(1, 4, 4));
        graph.addEdge(new Edge(2, 3, 2));
        graph.addEdge(new Edge(3, 4, 3));
        graph.addEdge(new Edge(3, 5, 7));
        graph.addEdge(new Edge(4, 5, 4));
        graph.addEdge(new Edge(4, 6, 7));
        graph.addEdge(new Edge(5, 6, 12));
        return graph;
    }

    public static void findPath(int start, int end, int[] parent) {
        if(start == end || (end == -1)) {
            System.out.print("'" + start + "'");
        } else {
            findPath(start, parent[end], parent);
            System.out.print("'" + end + "'");
        }
    }

    public static void printShortestPathTrees(final Graph graph, final int startVertexID, final int[] parent) {
        for(int index = 0; index < graph.getVertexCount(); index++) {
            if(startVertexID != index) {
                System.out.print(" From '" + startVertexID + "' to '" + index + "': ");
                findPath(startVertexID, index, parent);
                System.out.println();
            }
        }
    }

    public static void printShortestPathLengths(final Graph graph, final int startVertexID, double[] distance) {
        for(int index = 0; index < graph.getVertexCount(); index++) {
            if(startVertexID != index) {
                System.out.println(" From '" + startVertexID + "' to '" + index + "': " + distance[index]);
            }
        }
    }
}
