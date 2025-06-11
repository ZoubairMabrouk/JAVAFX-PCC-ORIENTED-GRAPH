package view;

import facade.GraphManager;
import javafx.scene.control.*;
import observer_shape.DrawingShape;

public class AppMenu extends MenuBar {

    public AppMenu(GraphManager graphManager, DrawingShape drawingShape) {
        Menu graphMenu = new Menu("Graphe");
        MenuItem rename = new MenuItem("Renommer le graphe");
        rename.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(graphManager.getCurrentGraphName());
            dialog.setHeaderText("Nouveau nom du graphe");
            dialog.setTitle("Renommer le graphe");
            dialog.showAndWait().ifPresent(name -> graphManager.renameGraph(name));
        });
        MenuItem load = new MenuItem("Charger un graphe");
        load.setOnAction(e -> {
            ChoiceDialog<String> dialog = new ChoiceDialog<>(
                    graphManager.getCurrentGraphName(),
                    graphManager.getAllGraphNames()
            );
            dialog.setTitle("Chargement d'un graphe");
            dialog.setHeaderText("Choisissez un graphe à charger");
            dialog.showAndWait().ifPresent(name -> {
                int id = graphManager.loadGraphByName(name);
                System.out.println(id);
                drawingShape.loadGraph(id);
            });
        });


        Menu menuFile = new Menu("Fichier");
        menuFile.getItems().addAll(
                new MenuItem("Nouveau"),
                new MenuItem("Ouvrir"),
                new MenuItem("Enregistrer"),
                new SeparatorMenuItem(),
                new MenuItem("Quitter")
        );


        graphMenu.getItems().addAll(rename, load);
        getMenus().addAll(menuFile, graphMenu);
    }
}
