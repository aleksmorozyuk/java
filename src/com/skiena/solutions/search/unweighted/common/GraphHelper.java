// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.common;

/**
 * @author aleks.
 */
public class GraphHelper {
    final public static int SEVEN = 7;
    final public static int EIGHT = 8;
    public static Graph createDirecredGraph() {
        Graph graph = new Graph(true);
        Vertex[] vertices = new Vertex[SEVEN];
        vertices[0] = new Vertex(0, 2);
        vertices[1] = new Vertex(1, 2);
        vertices[2] = new Vertex(2, 2);
        vertices[3] = new Vertex(3, 0);
        vertices[4] = new Vertex(4, 1);
        vertices[5] = new Vertex(5, 1);
        vertices[6] = new Vertex(6, 2);

        vertices[0].addDescendant(vertices[1]); // Node 0 -> Node 1
        vertices[0].addDescendant(vertices[2]); // Node 0 -> Node 2

        vertices[1].addDescendant(vertices[2]); // Node 1 -> Node 2
        vertices[1].addDescendant(vertices[3]); // Node 1 -> Node 3

        vertices[2].addDescendant(vertices[4]); // Node 2 -> Node 4
        vertices[2].addDescendant(vertices[5]); // Node 2 -> Node 5

        // Node 3 has no children; it has all incoming vertices from getOtherVertexID nodes

        vertices[4].addDescendant(vertices[3]); // Node 4 -> Node 3
        vertices[5].addDescendant(vertices[4]); // Node 5 -> Node 4
        vertices[6].addDescendant(vertices[0]); // Node 6 -> Node 0
        vertices[6].addDescendant(vertices[5]); // Node 6 -> Node 5

        for (int index = 0; index < vertices.length; index++) {
            graph.addVertex(vertices[index]);
        }

        return graph;
    }

    public static Graph createUndirectedGraphWithCycles() {
        Graph graph = new Graph(false);
        Vertex[] vertices = new Vertex[SEVEN];
        vertices[0] = new Vertex(0, 3);
        vertices[1] = new Vertex(1, 3);
        vertices[2] = new Vertex(2, 2);
        vertices[3] = new Vertex(3, 2);
        vertices[4] = new Vertex(4, 3);
        vertices[5] = new Vertex(5, 1);

        vertices[0].addDescendant(vertices[1]); // Node 0 -> Node 1
        vertices[0].addDescendant(vertices[4]); // Node 0 -> Node 4
        vertices[0].addDescendant(vertices[5]); // Node 0 -> Node 5

        vertices[1].addDescendant(vertices[0]); // Node 1 -> Node 0
        vertices[1].addDescendant(vertices[4]); // Node 1 -> Node 4
        vertices[1].addDescendant(vertices[2]); // Node 1 -> Node 2

        vertices[2].addDescendant(vertices[1]); // Node 2 -> Node 1
        vertices[2].addDescendant(vertices[3]); // Node 2 -> Node 3
        vertices[3].addDescendant(vertices[2]); // Node 3 -> Node 2
        vertices[3].addDescendant(vertices[4]); // Node 3 -> Node 4
        vertices[4].addDescendant(vertices[0]); // Node 4 -> Node 0
        vertices[4].addDescendant(vertices[1]); // Node 4 -> Node 1
        vertices[4].addDescendant(vertices[3]); // Node 4 -> Node 3
        vertices[5].addDescendant(vertices[0]); // Node 5 -> Node 0

        for (int index = 0; index < vertices.length; index++) {
            graph.addVertex(vertices[index]);
        }

        return graph;
    }

    public static Graph createUndirectedGraphWithoutCycles() {
        Graph graph = new Graph(false);
        Vertex[] vertices = new Vertex[SEVEN];
        vertices[0] = new Vertex(0, 2);
        vertices[1] = new Vertex(1, 2);
        vertices[2] = new Vertex(2, 1);
        vertices[3] = new Vertex(3, 0);
        vertices[4] = new Vertex(4, 0);
        vertices[5] = new Vertex(5, 0);

        vertices[0].addDescendant(vertices[1]); // Node 0 -> Node 1
        vertices[0].addDescendant(vertices[5]); // Node 0 -> Node 5

        vertices[1].addDescendant(vertices[4]); // Node 1 -> Node 4
        vertices[1].addDescendant(vertices[2]); // Node 1 -> Node 2

        vertices[2].addDescendant(vertices[3]); // Node 2 -> Node 3

        for (int index = 0; index < vertices.length; index++) {
            graph.addVertex(vertices[index]);
        }

        return graph;
    }

    public static Graph createWeaklyConnectedGraph() {
        Graph graph = new Graph(false);
        Vertex[] vertices = new Vertex[EIGHT];
        vertices[0] = new Vertex(0, 3);
        vertices[1] = new Vertex(1, 3);
        vertices[2] = new Vertex(2, 2);
        vertices[3] = new Vertex(3, 2);
        vertices[4] = new Vertex(4, 3);
        vertices[5] = new Vertex(5, 1);
        vertices[6] = new Vertex(6, 0);

        vertices[0].addDescendant(vertices[1]); // Node 0 -> Node 1
        vertices[0].addDescendant(vertices[4]); // Node 0 -> Node 4
        vertices[0].addDescendant(vertices[5]); // Node 0 -> Node 5

        vertices[1].addDescendant(vertices[0]); // Node 1 -> Node 0
        vertices[1].addDescendant(vertices[4]); // Node 1 -> Node 4
        vertices[1].addDescendant(vertices[2]); // Node 1 -> Node 2

        vertices[2].addDescendant(vertices[1]); // Node 2 -> Node 1
        vertices[2].addDescendant(vertices[3]); // Node 2 -> Node 3
        vertices[3].addDescendant(vertices[2]); // Node 3 -> Node 2
        vertices[3].addDescendant(vertices[4]); // Node 3 -> Node 4
        vertices[4].addDescendant(vertices[0]); // Node 4 -> Node 0
        vertices[4].addDescendant(vertices[1]); // Node 4 -> Node 1
        vertices[4].addDescendant(vertices[3]); // Node 4 -> Node 3
        vertices[5].addDescendant(vertices[0]); // Node 5 -> Node 0

        for (int index = 0; index < vertices.length; index++) {
            graph.addVertex(vertices[index]);
        }

        return graph;
    }

    public static Graph createStronglyConnectedGraph() {
        Graph graph = new Graph(true);
        Vertex[] vertices = new Vertex[EIGHT];
        vertices[0] = new Vertex(0, 1);
        vertices[1] = new Vertex(1, 3);
        vertices[2] = new Vertex(2, 1);
        vertices[3] = new Vertex(3, 3);
        vertices[4] = new Vertex(4, 1);
        vertices[5] = new Vertex(5, 1);
        vertices[6] = new Vertex(6, 1);
        vertices[7] = new Vertex(7, 1);

        vertices[0].addDescendant(vertices[1]); // Node 0 -> Node 1

        vertices[1].addDescendant(vertices[2]); // Node 1 -> Node 2
        vertices[1].addDescendant(vertices[3]); // Node 1 -> Node 3
        vertices[1].addDescendant(vertices[4]); // Node 1 -> Node 4

        vertices[2].addDescendant(vertices[0]); // Node 2 -> Node 0

        vertices[3].addDescendant(vertices[0]); // Node 3 -> Node 0
        vertices[3].addDescendant(vertices[5]); // Node 3 -> Node 5
        vertices[3].addDescendant(vertices[7]); // Node 3 -> Node 7

        vertices[4].addDescendant(vertices[5]); // Node 4 -> Node 5
        vertices[5].addDescendant(vertices[6]); // Node 5 -> Node 6

        vertices[6].addDescendant(vertices[4]); // Node 6 -> Node 4
        vertices[7].addDescendant(vertices[5]); // Node 7 -> Node 5

        for (int index = 0; index < vertices.length; index++) {
            graph.addVertex(vertices[index]);
        }

        return graph;
    }
}
