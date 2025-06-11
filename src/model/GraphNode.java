package model;

public class GraphNode {
    private String id;
    private double x, y;
    private String type;

    public String getType() {
        return type;
    }

    public GraphNode(String id, double x, double y, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type=type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return id;
    }
}
