package view;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Menu;

import java.util.function.Consumer;

public class LoggerCheckMethod extends Menu {
    public LoggerCheckMethod(Consumer<String> onLoggerSelected) {
        super("Méthode");

        ToggleGroup group = new ToggleGroup();
        RadioMenuItem console = new RadioMenuItem("Console");
        RadioMenuItem file = new RadioMenuItem("Fichier");
        RadioMenuItem db = new RadioMenuItem("Base de données");

        console.setToggleGroup(group);
        file.setToggleGroup(group);
        db.setToggleGroup(group);
        console.setSelected(true);

        getItems().addAll(console, file, db);
        console.setOnAction(e -> onLoggerSelected.accept("Console"));
        file.setOnAction(e -> onLoggerSelected.accept("Fichier"));
        db.setOnAction(e -> onLoggerSelected.accept("Base de données"));
    }
}
