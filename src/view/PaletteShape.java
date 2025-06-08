package view;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import java.util.function.Consumer;

public class PaletteShape extends VBox {

    public PaletteShape(Consumer<String> shapeSelectedCallback) {
        setSpacing(10);
        ToggleGroup group = new ToggleGroup();

        for (String shape : new String[]{"Rectangle", "Cercle", "Ligne"}) {
            RadioButton btn = new RadioButton(shape);
            btn.setToggleGroup(group);
            btn.setOnAction(e -> shapeSelectedCallback.accept(shape.toLowerCase()));
            getChildren().add(btn);
        }

        setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
    }
}
