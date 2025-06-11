package model;

public class GraphNode {
    private String id;
    private String nom;
    private double x, y;
    private String type;

    public String getType() {
        return type;
    }

    public GraphNode(String id,String nom, double x, double y, String type) {
        this.id = id;
        this.nom = nom;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return id;
    }
}
