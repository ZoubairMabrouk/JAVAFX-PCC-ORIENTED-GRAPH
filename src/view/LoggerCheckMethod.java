package view;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Menu;

public class LoggerCheckMethod extends Menu {
    public LoggerCheckMethod() {
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
    }
}
