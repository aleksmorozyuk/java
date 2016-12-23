// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.weighted.shortestpath;

import com.skiena.solutions.search.weighted.common.Edge;
import com.skiena.solutions.search.weighted.common.Graph;
import com.skiena.solutions.search.weighted.common.GraphHelper;

import java.util.LinkedList;
import java.util.Queue;

import static com.skiena.solutions.search.weighted.common.GraphHelper.printShortestPathLengths;
import static com.skiena.solutions.search.weighted.common.GraphHelper.printShortestPathTrees;

/**
 * @author aleks.
 */
public class BellmanFord {

    /*
     * Time complexity of the Bellman-Ford algorithm is O(V*E)
     */
    public static void shortestPath(final Graph graph, final int startVertexID) {
        final Queue<Integer> queue = new LinkedList<>();
        final int[] parent = new int[graph.getVertexCount()];
        final double[] distance = new double[graph.getVertexCount()];
        final boolean[] queued = new boolean[graph.getVertexCount()];

        // STEP 1: Initialize distances from source to all other vertices as INFINITE
        for(int index = 0; index < graph.getVertexCount(); index++) {
            parent[index] = -1;
            queued[index] = false;
            distance[index] = Double.MAX_VALUE;
        }

        distance[startVertexID] = 0.0;
        queue.add(startVertexID);
        queued[startVertexID] = true;

        // STEP 2: Relax all edges |V| - 1 times. A simple shortest path from source
        // to any other vertex can have at-most |V| - 1 edges
        while(!queue.isEmpty()) {
            Integer currentVertexID = queue.remove();
            queued[currentVertexID] = false;

            // Look at vertex's edges and update distance to a vertex that would
            // give the shortest distance from current vertex if added to the path:
            // distance[currentVertexID] + edgeWeight.
            for (Edge edge : graph.getEdges(currentVertexID)) {
                double edgeWeight = edge.getWeight();
                int otherVertexID = edge.getOtherVertexID(currentVertexID);
                if (distance[otherVertexID] > distance[currentVertexID] + edgeWeight) {
                    distance[otherVertexID] = distance[currentVertexID] + edgeWeight;
                    parent[otherVertexID] = currentVertexID;
                    if(!queued[otherVertexID]) {
                        queue.add(otherVertexID);
                    }
                }
            }
        }

        // STEP 3: check for negative-weight cycles. The above step guarantees shortest
        // distances if graph doesn't contain negative weight cycle. If we get a shorter
        // path, then there is a cycle.
        for(int vertexOne = 0; vertexOne < graph.getVertexCount(); vertexOne++) {
            for (Edge edge : graph.getEdges(vertexOne)) {
                if(distance[edge.getVertexTwo()] > (distance[vertexOne] + edge.getWeight())) {
                    System.out.println("Graph contains a negative-weight cycle: from '" +
                            vertexOne + "' to '" + edge.getVertexTwo() + "'");
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
        BellmanFord.shortestPath(GraphHelper.createWeigtedGraph(GraphHelper.SEVEN, true), 0);
    }
}
