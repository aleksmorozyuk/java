// Copyright (c) 2016 Boomi, Inc.
package com.skiena.solutions.search.unweighted.bfs;

import com.skiena.solutions.search.unweighted.common.Vertex;
import com.skiena.solutions.search.unweighted.common.Graph;
import com.skiena.solutions.search.unweighted.common.GraphHelper;

/**
 * @author aleks.
 */
public class TwoColoring extends BreadFirstSearch {

    final private Color[] color;
    private enum Color {
        UNCOLORED(0), WHITE(1), BLACK(2);
        Color(int color) {
            this.color = color;
        }

        private int color;
        public int getColor() {
            return this.color;
        }
    }

    public TwoColoring(Graph graph) {
        super(graph);
        this.color = new Color[graph.getVertices().size()];
    }

    private Color complement(Color color) {
        if(color.equals(Color.WHITE)) {
            return Color.BLACK;
        } else if(color.equals(Color.BLACK)) {
            return Color.WHITE;
        }
        return Color.UNCOLORED;
    }

    public void twoColor() {
        for(int index = 0; index < graph.getVertices().size(); index++) {
            this.color[index] = Color.UNCOLORED;
        }

        for(int index = 0; index < graph.getVertices().size(); index++) {
            if(!this.discovered[index]) {
                this.color[index] = Color.WHITE;
                this.breadFirstSearch(graph.getVertices().get(index));
            }
        }
    }

    protected void processEdge(Vertex start, Vertex end) {
        if(this.color[start.getVertexID()] == this.color[end.getVertexID()]) {
            System.out.println("WARNING: not bipartite due to (" + start.getVertexID() + "," + end
            .getVertexID() + ")");
        }

        this.color[end.getVertexID()] = this.complement(this.color[start.getVertexID()]);
    }

    public static void main(String[] args) {
        new TwoColoring(GraphHelper.createUndirectedGraphWithCycles()).twoColor();
    }
}
