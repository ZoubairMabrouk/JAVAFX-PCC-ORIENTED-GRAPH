package command;

import factory_shape.Shape;
import javafx.scene.canvas.GraphicsContext;

public class DrawCommand implements Command{
    private Shape shape;
    private GraphicsContext gc;
    private double x, y;

    public DrawCommand(Shape shape, GraphicsContext gc, double x, double y) {
        this.shape = shape;
        this.gc = gc;
        this.x = x;
        this.y = y;
    }
    @Override
    public void execute() {
        shape.draw(gc,x,y);
    }
}
