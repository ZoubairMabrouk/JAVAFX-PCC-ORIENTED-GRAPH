package observer_shape;

import command.Command;
import command.CommandInvoker;
import command.DrawCommand;
import decorator_shape.decorator_implementation.BorderDecorator;
import decorator_shape.decorator_implementation.ColorDecorator;
import factory_shape.FactoryShape;
import factory_shape.LineBuilder;
import factory_shape.LineShapeBuilder;
import factory_shape.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import model.Graph;
import model.GraphEdge;
import model.GraphNode;
import model.ShapeEntity;
import strategy_logger.LoggerMethod;

import java.util.ArrayList;
import java.util.List;

public class DrawingShape extends StackPane implements ShapeSelectionObserver {

        private final Canvas canvas;
        private final GraphicsContext gc;
        private final CommandInvoker invoker = new CommandInvoker();
        private String selectedShapeType = null;


    private LineBuilder lineBuilder = null;
    private List<GraphNode> graphNodes = new ArrayList<>();
    private List<GraphEdge> graphEdges = new ArrayList<>();

    public DrawingShape(double width, double height, LoggerMethod loggerMethod) {
            canvas = new Canvas(width, height);
            gc = canvas.getGraphicsContext2D();
            getChildren().add(canvas);
            setStyle("-fx-border-color: black");
            canvas.setOnMousePressed(e ->{
                if (lineBuilder != null){
                    lineBuilder.handlePressed(e);
                }else {
                    if (selectedShapeType != null) {
                        Shape shape = FactoryShape.createShape(selectedShapeType);
                        shape = new ColorDecorator(shape);
                        shape = new BorderDecorator(shape);
                        Command cmd = new DrawCommand(shape, gc, e.getX(), e.getY());
                        invoker.execute(cmd);
                        loggerMethod.logger(new ShapeEntity(selectedShapeType,e.getX(),e.getY(),0,0));
                        graphNodes.add(new GraphNode("1",e.getX(),e.getY()));

                    }
                }
            });
            canvas.setOnMouseReleased(e->{
                if (lineBuilder!=null){
                    lineBuilder.handleReleased(e);
                    if (lineBuilder.isReady()){
                        Shape shape = lineBuilder.build();
                        Command cmd = new DrawCommand(shape, gc,0,0);
                        invoker.execute(cmd);
                        loggerMethod.logger(new ShapeEntity(selectedShapeType,e.getX(),e.getY(),0,0));
                        graphEdges.add(new GraphEdge(graphNodes.getFirst(),graphNodes.getLast(),1));
                    }
                }
            });
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
}
