// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.weighted.spanningtree;

import com.skiena.solutions.search.weighted.common.Edge;
import com.skiena.solutions.search.weighted.common.Graph;
import com.skiena.solutions.search.weighted.common.GraphHelper;
import com.skiena.solutions.search.weighted.common.UnionFind;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author aleks.
 */
public class Kruskal {

    private static void printMinimumSpanningTreeTraversal(final List<Edge> finalMST) {
        for(Edge edge : finalMST) {
            System.out.print("(" + edge.getVertexOne() + "-" + edge.getVertexTwo() + ")");
        }
    }

    /*
     * Time Complexity: O(E*log(E)) or O(E*log(V)). Sorting of edges takes O(ELogE) time. After sorting,
     * we iterate through all edges and apply find-union algorithm. The find and union operations
     * can take at most O(log(V)) time. So overall complexity is O(E*log(E) + E*log(V)) time. The value of
     * E can be at most O(V^2), so O(log(V)) are O(log(E)) same. Therefore, overall time complexity is
     * O(E*log(E)) or O(E*log(V)).
     */
    public static void minimumSpanningTree(final Graph graph) {

        final List<Edge> finalMST = new LinkedList<>();

        // Add graph's edges to a priority queue based on their weights.
        // We could have also sorted the edges based their weights in O(E*log(E)).
        final Queue<Edge> priorityQueue = new PriorityQueue<>(graph.getAllEdges());

        final UnionFind unionFind = new UnionFind(graph.getVertexCount());
        while(!priorityQueue.isEmpty() && finalMST.size() < (graph.getVertexCount() - 1)) {
            Edge edge = priorityQueue.remove();

            // If two vertices are NOT part of the same component, combine them
            if(!unionFind.areConnected(edge.getVertexOne(), edge.getVertexTwo())) {
                unionFind.doUnion(edge.getVertexOne(), edge.getVertexTwo());
                finalMST.add(edge); // Add the edge to a list of minimum spanning tree
            }
        }

        System.out.print("Minimum Spanning Tree (Traversal): ");
        printMinimumSpanningTreeTraversal(finalMST);
        System.out.println();

        System.out.print("Minimum Spanning Tree (Distances): ");
        double totalDistance = printMinimumSpanningTreeWeights(finalMST);
        System.out.println(" = " + totalDistance);
    }

    private static double printMinimumSpanningTreeWeights(final List<Edge> finalMST) {
        double totalDistance = 0.0;
        for(int index = 0; index < finalMST.size(); index++) {
            totalDistance += (finalMST.get(index)).getWeight();
            System.out.print((finalMST.get(index)).getWeight());

            if (index < finalMST.size() - 1) {
               System.out.print(" + ");
            }
        }
        return totalDistance;
    }

    public static void main(String[] args) {
        Kruskal.minimumSpanningTree(GraphHelper.createWeigtedGraph(GraphHelper.SEVEN, false));
    }
}
