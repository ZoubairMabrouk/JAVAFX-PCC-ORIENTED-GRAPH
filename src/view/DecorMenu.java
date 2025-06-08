package view;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class DecorMenu extends VBox {
    private final CheckBox fill = new CheckBox("Remplir");
    private final CheckBox border = new CheckBox("Bordure");
    private final CheckBox shadow = new CheckBox("Ombre");

    //private final List<StyleSelectionObserver> observers = new ArrayList<>();

    public DecorMenu() {
        getChildren().addAll(fill, border, shadow);
        fill.setOnAction(e -> notifyObservers());
        border.setOnAction(e -> notifyObservers());
        shadow.setOnAction(e -> notifyObservers());
        setSpacing(10);
        setStyle("-fx-padding: 10;");
    }

    public void addObserver() {

    }

    private void notifyObservers() {
        /*for (StyleSelectionObserver o : observers) {
            o.onStyleChanged(fill.isSelected(), border.isSelected(), shadow.isSelected());
        }*/
    }
}
