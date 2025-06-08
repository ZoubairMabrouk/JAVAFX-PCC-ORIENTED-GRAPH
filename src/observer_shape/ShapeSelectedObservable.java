package observer_shape;

public interface ShapeSelectedObservable {
    void addObserver(ShapeSelectionObserver observer);
    void removeObserver(ShapeSelectionObserver observer);
    void notifyObservers(String shapeType);
}
