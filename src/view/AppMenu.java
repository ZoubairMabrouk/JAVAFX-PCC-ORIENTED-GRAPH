package view;

import facade.GraphManager;
import javafx.scene.control.*;
import model.GraphNode;
import observer_shape.DrawingShape;
import strategy_pcc.Djekestra;
import strategy_pcc.StrategyPCC;

import java.util.List;

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
        MenuItem pcc = new MenuItem("Calculer le plus court chemin");
        pcc.setOnAction(e -> {
            TextInputDialog startDialog = new TextInputDialog();
            startDialog.setTitle("Départ");
            startDialog.setHeaderText("Nom du nœud de départ :");

            TextInputDialog endDialog = new TextInputDialog();
            endDialog.setTitle("Arrivée");
            endDialog.setHeaderText("Nom du nœud d'arrivée :");

            startDialog.showAndWait().ifPresent(startName -> {
                endDialog.showAndWait().ifPresent(endName -> {
                    GraphNode startNode = drawingShape.findNodeByName(startName);
                    GraphNode endNode = drawingShape.findNodeByName(endName);

                    if (startNode != null && endNode != null) {
                        StrategyPCC context = new StrategyPCC();
                        context.setStrategy(new Djekestra()); // par exemple

                        List<GraphNode> path = context.execute(drawingShape.getGraphNodes(), drawingShape.getGraphEdges(), startNode, endNode);
                        System.out.println("Chemin trouvé : ");
                        path.forEach(n -> System.out.println(n.getId()));

                    } else {
                        System.out.println("Nœud(s) non trouvé(s)"+ startName);
                    }
                });
            });
        });
        Menu outilsMenu = new Menu("Outils");
        outilsMenu.getItems().add(pcc);




        graphMenu.getItems().addAll(rename, load);
        getMenus().addAll(menuFile, graphMenu,outilsMenu);
    }
}
