package observer_shape;

import command.Command;
import command.CommandInvoker;
import command.DrawCommand;
import dao.GraphDAO;
import dao.GraphEdgeDAO;
import dao.GraphNodeDAO;
import decorator_shape.decorator_implementation.BorderDecorator;
import decorator_shape.decorator_implementation.ColorDecorator;
import facade.GraphManager;
import factory_shape.FactoryShape;
import factory_shape.LineBuilder;
import factory_shape.LineShapeBuilder;
import factory_shape.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;

import model.GraphEdge;
import model.GraphNode;
import model.ShapeEntity;
import strategy_logger.LoggerMethod;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DrawingShape extends StackPane implements ShapeSelectionObserver {
    private GraphManager graphManager;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final CommandInvoker invoker = new CommandInvoker();
    private String selectedShapeType = null;
    private GraphNode startNode = null;
    private GraphNode endNode = null;
    private LoggerMethod currentLogger;
    private LineBuilder lineBuilder = null;
    private List<GraphNode> graphNodes = new ArrayList<>();
    private List<GraphEdge> graphEdges = new ArrayList<>();
    private final GraphEdgeDAO edgeDAO = new GraphEdgeDAO();
    private final GraphNodeDAO nodeDAO = new GraphNodeDAO();
    private final GraphDAO graphDAO = new GraphDAO();
    private int currentGraphId =-1;


    public DrawingShape(double width, double height, LoggerMethod loggerMethod) {
            canvas = new Canvas(width, height);
            gc = canvas.getGraphicsContext2D();
            getChildren().add(canvas);
            setStyle("-fx-border-color: black");
            canvas.setOnMousePressed(e ->{
                if (lineBuilder != null){
                    lineBuilder.handlePressed(e);
                    GraphNode clickedNode = findClosestNode(e.getX(), e.getY());
                    if (clickedNode != null) {
                        if (startNode == null) {
                            startNode = clickedNode;
                        } else {
                            endNode = clickedNode;
                        }}
                }else {
                    if (selectedShapeType != null) {
                        TextInputDialog nameDialog = new TextInputDialog("Noeud_" + (graphNodes.size() + 1));
                        nameDialog.setTitle("Nom du nœud");
                        nameDialog.setHeaderText("Entrez un nom pour ce nœud :");
                        nameDialog.showAndWait().ifPresent(nodeName -> {
                            GraphNode gn = new GraphNode("1",nodeName, e.getX(), e.getY(), selectedShapeType);
                            drawNode(gn);
                            try {
                                nodeDAO.save(gn, graphManager.getCurrentGraphId());
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                        if (currentLogger != null) {
                            currentLogger.logger(new ShapeEntity(selectedShapeType, e.getX(), e.getY(), 0, 0));
                        }else{
                            loggerMethod.logger(new ShapeEntity(selectedShapeType,e.getX(),e.getY(),0,0));

                        }

                    }
                }
            });
            canvas.setOnMouseReleased(e->{
                if (lineBuilder!=null){
                    lineBuilder.handleReleased(e);
                    endNode = findClosestNode(e.getX(), e.getY());
                    if (lineBuilder.isReady() && startNode != null && endNode != null && !startNode.equals(endNode)){
                        Shape shape = lineBuilder.build();
                        Command cmd = new DrawCommand(shape, gc,0,0);
                        invoker.execute(cmd);
                        if(currentLogger == null){
                            loggerMethod.logger(new ShapeEntity(selectedShapeType,e.getX(),e.getY(),0,0));

                        }else {
                            currentLogger.logger(new ShapeEntity(selectedShapeType, e.getX(), e.getY(), 0, 0));
                        }graphEdges.add(new GraphEdge(startNode,endNode,1));
                        try {
                            edgeDAO.save(new GraphEdge(startNode,endNode,1),graphManager.getCurrentGraphId());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        }
    public void setGraphManager(GraphManager graphManager) {
        this.graphManager = graphManager;
    }

    @Override
    public void onSelectedShape(String type) {

        this.selectedShapeType = type;
        if (type.equals("ligne")){
            this.lineBuilder = new LineShapeBuilder();
        }else {
            this.lineBuilder = null;
        }
    }


    public void loadGraph(int graphId) {

        this.currentGraphId = graphId;
        graphNodes.clear();
        graphEdges.clear();

        List<GraphNode> nodes = nodeDAO.getByGraphId(graphId);
        Map<String, GraphNode> nodeMap = new java.util.HashMap<>();
        for (GraphNode node : nodes) {
            nodeMap.put(node.getId(), node);
        }

        List<GraphEdge> edges = edgeDAO.getByGraphId(graphId, nodeMap);

        for (GraphNode node : nodes) {
            graphNodes.add(node);
            drawNode(node);
        }
        for (GraphEdge edge : edges){
            graphEdges.add(edge);
            drawedge(edge);
        }
    }

    private void drawedge(GraphEdge edge) {
        GraphNode from = edge.getFrom();
        GraphNode to = edge.getTo();

        if (from != null && to != null) {
            gc.strokeLine(from.getX(), from.getY(), to.getX(), to.getY());
        }
    }

    public void drawNode(GraphNode node) {
        Shape shape = FactoryShape.createShape(node.getType());
        shape = new ColorDecorator(shape);
        shape = new BorderDecorator(shape);
        Command cmd = new DrawCommand(shape, gc, node.getX(), node.getY());
        invoker.execute(cmd);
        graphNodes.add(node);
    }
    private GraphNode findClosestNode(double x, double y) {
        final double THRESHOLD = 15.0;
        for (GraphNode node : graphNodes) {
            double dx = node.getX() - x;
            double dy = node.getY() - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            System.out.println(distance+" "+x+" "+y);
            if (distance <= THRESHOLD) {

                return node;
            }
        }
        System.out.println("Aucun nœud proche trouvé à (" + x + "," + y + ")");
        return null;
    }


    public void setLoggerMethod(LoggerMethod currentLogger) {
        this.currentLogger = currentLogger;
    }

    public GraphNode findNodeByName(String name) {
        return graphNodes.stream().filter(n -> n.getId().equals(name)).findFirst().orElse(null);
    }

    public List<GraphNode> getGraphNodes() {
        return graphNodes;
    }

    public List<GraphEdge> getGraphEdges() {
        return graphEdges;
    }
}
