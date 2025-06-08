package view;

import javafx.scene.control.*;

public class AppMenu extends MenuBar {

    public AppMenu() {
        Menu menuFile = new Menu("Fichier");
        menuFile.getItems().addAll(
                new MenuItem("Nouveau"),
                new MenuItem("Ouvrir"),
                new MenuItem("Enregistrer"),
                new SeparatorMenuItem(),
                new MenuItem("Quitter")
        );

        Menu menuLogger = new Menu("Logger");
        menuLogger.getItems().addAll(
                new LoggerCheckMethod()
        );

        getMenus().addAll(menuFile, menuLogger);
    }
}
