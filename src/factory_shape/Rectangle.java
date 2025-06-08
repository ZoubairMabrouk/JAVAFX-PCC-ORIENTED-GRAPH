package factory_shape;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle implements Shape{
    @Override
    public void draw(GraphicsContext gc, double x, double y) {
            gc.strokeRect(x,y,10,10);
    }
}
