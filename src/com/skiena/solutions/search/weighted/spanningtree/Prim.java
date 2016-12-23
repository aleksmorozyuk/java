// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.weighted.spanningtree;

import com.skiena.solutions.search.weighted.common.Edge;
import com.skiena.solutions.search.weighted.common.Graph;
import com.skiena.solutions.search.weighted.common.GraphHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aleks.
 */
public class Prim {

    /*
     * Time Complexity of the Prim's Algorithm is O(V^2). If the input graph is represented using adjacency list,
     * then the time complexity of Prim’s algorithm can be reduced to O(E*log(V)) with the help of Binary Heap or
     * Priority Queue data structures.
     */
    public static void minimumSpanningTree(final Graph graph, final int startVertexID) {
        final int[] parent = new int[graph.getVertexCount()];
        final double[] distance = new double[graph.getVertexCount()];
        final boolean[] intree = new boolean[graph.getVertexCount()];

        // Initialize parent, in-tree & distance arrays
        for(int index = 0; index < graph.getVertexCount(); index++) {
            parent[index] = -1;
            intree[index] = false;
            distance[index] = Double.MAX_VALUE;
        }

        distance[startVertexID] = 0.0;
        int currentVertexID = startVertexID;

        while(!intree[currentVertexID]) {
            intree[currentVertexID] = true;

            // For vertex's edges that have other vertex not yet included in the tree,
            // update edges' distances & parents of vertices that are not in the tree.
            // If graph used Priority Queue instead of List to keep track of vertex edges
            // then we wouldn't need the loop through all of them and would simply used the
            // one with smallest weight provided by Priority Queue data structure.
            for(Edge edge : graph.getEdges(currentVertexID)) {
                double edgeWeight = edge.getWeight();
                int otherVertexID = edge.getOtherVertexID(currentVertexID);
                if(distance[otherVertexID] > edgeWeight && !intree[otherVertexID]) {
                    distance[otherVertexID] = edgeWeight;
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

        System.out.print("Minimum Spanning Tree (Traversal): ");
        printMinimumSpanningTreeTraversal(parent, new ArrayList<Integer>(){{this.add(-1);}});
        System.out.println();

        System.out.print("Minimum Spanning Tree (Distances): ");
        double totalDistance = printMinimumSpanningTreeWeights(distance);
        System.out.println(" = " + totalDistance);
    }

    private static double printMinimumSpanningTreeWeights(double[] distance) {
        double totalDistance = 0.0;
        for(int index = 0; index < distance.length; index++) {
            if(distance[index] > 0.0) {
                totalDistance += distance[index];
                System.out.print(distance[index]);
                if (index < distance.length - 1 && distance[index+1] > 0.0) {
                    System.out.print(" + ");
                }
            }
        }
        return totalDistance;
    }

    private static List<Integer> printMinimumSpanningTreeTraversal(int[] parent, List<Integer> parentVertices) {
        final List<Integer> childVertices = new ArrayList<>();
        for(int index = 0; index < parent.length; index++) {
            if(parentVertices.contains(parent[index])) {
                childVertices.add(index);
                if(parent[index] > -1) {
                    System.out.print("(" + parent[index] + "-" + index + ")");
                }
            }
        }

        if(!childVertices.isEmpty()) {
            printMinimumSpanningTreeTraversal(parent, childVertices);
        }

        return childVertices;
    }

    public static void main(String[] args) {
        Prim.minimumSpanningTree(GraphHelper.createWeigtedGraph(GraphHelper.SEVEN, false), 0);
        Prim.minimumSpanningTree(GraphHelper.createWeigtedGraph(GraphHelper.SEVEN, false), 6);
    }
}
