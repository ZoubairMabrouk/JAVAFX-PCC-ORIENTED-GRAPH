package factory_shape;

import javafx.scene.input.MouseEvent;

public class LineShapeBuilder implements LineBuilder{
    private double startX, startY, endX, endY;
    private boolean ready = false;

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

    @Override
    public void handlePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        ready = false;

    }

    @Override
    public void handleReleased(MouseEvent event) {
        endX = event.getX();
        endY = event.getY();
        ready = true;

    }

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public Shape build() {
        return new  Line(startX,startY,endX,endY);
    }
}
