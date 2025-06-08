package factory_shape;

public class FactoryShape {
    public static Shape createShape(String shape){
        return switch (shape.toLowerCase()){
            case "rectangle" -> new Rectangle();
            case "cercle" -> new Circle();
            case "ligne" -> new Line(0,0,0,0);
            default -> throw new IllegalStateException("Unexpected value: " + shape.toLowerCase());
        };
    }
}
