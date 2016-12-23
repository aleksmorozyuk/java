// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.bfs;

import com.skiena.solutions.search.unweighted.common.Graph;
import com.skiena.solutions.search.unweighted.common.GraphHelper;

/**
 * @author aleks.
 */
public class ConnectedComponents extends BreadFirstSearch {

    public ConnectedComponents(Graph graph) {
        super(graph);
    }

    public void connectedComponents() {
        int componentCounter = 0;
        for (int index = 0; index < this.graph.getVertices().size() - 1; index++) {
            if(!this.discovered[index]) {
                componentCounter = componentCounter + 1;
                System.out.println("check component '"+componentCounter+"'");
                this.breadFirstSearch(this.graph.getVertices().get(index));
            }
        }

        System.out.println("\nComponents Found: " + componentCounter + " --> "
                + (componentCounter == 1 ? "Connected Graph\n" : "Disconnected Graph\n"));
    }

    public static void main(String[] args) {
        new ConnectedComponents(GraphHelper.createUndirectedGraphWithCycles()).connectedComponents();
        new ConnectedComponents(GraphHelper.createWeaklyConnectedGraph()).connectedComponents();
    }
}
