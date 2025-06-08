package model;

public class ShapeEntity {
    private String type;
    private double x, y, x2, y2;
    public ShapeEntity(String type, double x, double y, double x2, double y2) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }
}
