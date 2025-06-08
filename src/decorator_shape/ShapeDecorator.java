package decorator_shape;

import factory_shape.Shape;
import javafx.scene.canvas.GraphicsContext;

public abstract class ShapeDecorator implements Shape {
    protected Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        shape.draw(gc, x, y);
    }
}
