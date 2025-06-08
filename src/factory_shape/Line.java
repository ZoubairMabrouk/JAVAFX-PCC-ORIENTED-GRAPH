package factory_shape;

import javafx.scene.canvas.GraphicsContext;

public class Line implements Shape{
    private final double startX, startY, endX, endY;

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public Line(double startX1, double startY1, double endX1, double endY1) {
        this.startX = startX1;
        this.startY = startY1;
        this.endX = endX1;
        this.endY = endY1;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.strokeLine(startX,startY,endX,endY);

    }
}
