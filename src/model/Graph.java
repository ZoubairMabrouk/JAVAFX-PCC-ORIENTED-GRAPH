package model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<GraphNode> nodes = new ArrayList<>();
    private final List<GraphEdge> edges = new ArrayList<>();

    public void addNode(GraphNode node) {
        nodes.add(node);
    }

    public void addEdge(GraphEdge edge) {
        edges.add(edge);
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }
}
