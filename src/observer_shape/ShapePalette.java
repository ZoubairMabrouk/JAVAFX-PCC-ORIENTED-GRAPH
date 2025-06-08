package observer_shape;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.*;

public class ShapePalette extends VBox implements ShapeSelectedObservable {
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final List<ShapeSelectionObserver> observers = new ArrayList<>();

    public ShapePalette() {
        setSpacing(10);
        setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
        setPrefWidth(150);

        createRadio("Rectangle");
        createRadio("Cercle");
        createRadio("Ligne");

        // Observer sélection
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selected = (RadioButton) newToggle;
                notifyObservers(selected.getText().toLowerCase());
            }
        });
    }

    private void createRadio(String label) {
        RadioButton btn = new RadioButton(label);
        btn.setToggleGroup(toggleGroup);
        getChildren().add(btn);
    }

    @Override
    public void addObserver(ShapeSelectionObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ShapeSelectionObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String shapeType) {
        for (ShapeSelectionObserver observer : observers) {
            observer.onSelectedShape(shapeType);
        }
    }
}