// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.weighted.shortestpath;

import com.skiena.solutions.search.weighted.common.Edge;
import com.skiena.solutions.search.weighted.common.Graph;
import com.skiena.solutions.search.weighted.common.GraphHelper;

import static com.skiena.solutions.search.weighted.common.GraphHelper.printShortestPathLengths;
import static com.skiena.solutions.search.weighted.common.GraphHelper.printShortestPathTrees;

/**
 * @author aleks.
 */
public class Dijkstra {

    /*
     * Time Complexity of the Dijkstra Algorithm is O(V^2). If the input graph is represented using adjacency list,
     * it can be reduced to O(E*log(V)) with the help of Binary Heap or Priority Queue data structures.
     */
    public static void shortestPath(final Graph graph, final int startVertexID) {
        final int[] parent = new int[graph.getVertexCount()];
        final double[] distance = new double[graph.getVertexCount()];
        final boolean[] intree = new boolean[graph.getVertexCount()];

        // STEP 1: Initialize distances from source to all other vertices as INFINITE
        for(int index = 0; index < graph.getVertexCount(); index++) {
            parent[index] = -1;
            intree[index] = false;
            distance[index] = Double.MAX_VALUE;
        }

        distance[startVertexID] = 0.0;
        int currentVertexID = startVertexID;

        // STEP 2: Find shortest path for all vertices by finding the minimum weight edge
        while(!intree[currentVertexID]) {
            intree[currentVertexID] = true;

            // Look at vertex's edges and update distance to a vertex that would
            // give the shortest distance from current vertex: distance to current
            // vertex + edgeWeight --> distance[currentVertexID] + edgeWeight.
            // In Prim's algorithm for Minimum Spanning Tree, we are interested in
            // a minimum weight edge regardless of distance to current vertex.
            for(Edge edge : graph.getEdges(currentVertexID)) {
                double edgeWeight = edge.getWeight();
                int otherVertexID = edge.getOtherVertexID(currentVertexID);
                if(distance[otherVertexID] > (distance[currentVertexID] + edgeWeight)) {
                    distance[otherVertexID] = distance[currentVertexID] + edgeWeight;
                    parent[otherVertexID] = currentVertexID;
                }
            }

            currentVertexID = 1;
            double minimumDistance = Double.MAX_VALUE;

            // For vertices that are not in the tree, we want to move to the
            // one having smallest distance from the current vertex that we are at.
            for(int index = 0; index < graph.getVertexCount(); index++) {
                if(!intree[index] && minimumDistance > distance[index]) {
                    minimumDistance = distance[index];
                    currentVertexID = index;
                }
            }
        }

        System.out.println("Shortest Path (Trees): ");
        printShortestPathTrees(graph, startVertexID, parent);
        System.out.println();

        System.out.println("Shortest Path (Lengths): ");
        printShortestPathLengths(graph, startVertexID, distance);
        System.out.println();
    }

    public static void main(String[] args) {
        Dijkstra.shortestPath(GraphHelper.createWeigtedGraph(GraphHelper.SEVEN, true), 0);
    }
}
