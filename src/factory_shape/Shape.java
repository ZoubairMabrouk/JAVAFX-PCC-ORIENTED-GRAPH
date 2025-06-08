package factory_shape;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
    void draw(GraphicsContext gc, double x, double y);
}
