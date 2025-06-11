package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import model.GraphEdge;
import model.GraphNode;

import javax.swing.*;
import java.util.*;

public class GraphZone extends Canvas {


    private final List<GraphNode> nodes = new ArrayList<>();
    private final List<GraphEdge> edges = new ArrayList<>();
    private GraphNode selectedNode = null;

    public GraphZone() {
        super(600, 400);
        setOnMouseClicked(this::handleClick);
    }
    private void handleClick(MouseEvent e) {
        double x = e.getX(), y = e.getY();

        for (GraphNode node : nodes) {
            if (Math.hypot(x - node.getX(), y - node.getY()) < 20) {
                if (selectedNode == null) {
                    selectedNode = node;
                } else {
                    String input = JOptionPane.showInputDialog("Poids de l’arête :");
                    try {
                        double weight = Double.parseDouble(input);
                        edges.add(new GraphEdge(selectedNode, node, weight));
                    } catch (Exception ignored) {}
                    selectedNode = null;
                    redraw();
                }
                return;
            }
        }

        // Ajouter un nouveau nœud
        String id = "N" + (nodes.size() + 1);
        GraphNode newNode = new GraphNode(id, x, y,selectedNode.toString());
        nodes.add(newNode);
        redraw();
    }

    public void redraw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Dessiner les arêtes
        for (GraphEdge edge : edges) {
            GraphNode a = edge.getFrom(), b = edge.getTo();
            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
            double mx = (a.getX() + b.getX()) / 2;
            double my = (a.getY() + b.getY()) / 2;
            gc.fillText(String.valueOf(edge.getWeight()), mx, my);
        }

        // Dessiner les nœuds
        for (GraphNode node : nodes) {
            gc.fillOval(node.getX() - 10, node.getY() - 10, 20, 20);
            gc.fillText(node.getId(), node.getX() + 12, node.getY());
        }
    }
}
