package factory_shape;

import javafx.scene.canvas.GraphicsContext;

public class Circle implements Shape{
    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        double radius = 40;
        gc.strokeOval(x - radius / 2, y - radius / 2, radius, radius);
    }
}
