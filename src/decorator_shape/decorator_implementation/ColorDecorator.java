package decorator_shape.decorator_implementation;

import decorator_shape.ShapeDecorator;
import factory_shape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorDecorator extends ShapeDecorator {
    public ColorDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y){
        gc.setStroke(Color.RED);
        super.draw(gc,x,y);
    }
}
