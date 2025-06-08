package decorator_shape.decorator_implementation;

import decorator_shape.ShapeDecorator;
import factory_shape.Shape;
import javafx.scene.canvas.GraphicsContext;

public class BorderDecorator extends ShapeDecorator {
    public BorderDecorator(Shape shape) {
        super(shape);
    }
    @Override
    public void draw(GraphicsContext gc, double x, double y){
        gc.setLineWidth(2);
        super.draw(gc,x,y);
    }
}
