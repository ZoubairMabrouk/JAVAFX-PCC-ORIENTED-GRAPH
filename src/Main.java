import facade.GraphManager;
import factory_logger.LoggerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import observer_shape.DrawingShape;
import observer_shape.ShapePalette;
import strategy_logger.ConsoleLogger;
import strategy_logger.LoggerMethod;
import strategy_logger.singleton_db.DBConnection;
import view.AppMenu;
import view.DecorMenu;
import view.LoggerCheckMethod;
import view.LoggerEspace;

import java.sql.Connection;
import java.sql.SQLException;


public class Main extends Application {
    private LoggerMethod currentLogger;

    @Override
    public void start(Stage primaryStage) throws SQLException {
        primaryStage.setTitle("Mini Projet JavaFX");

        Connection connection = DBConnection.getInstance().getConnection();

        ShapePalette palette = new ShapePalette();
        LoggerEspace loggerEspace = new LoggerEspace();
        LoggerFactory loggerFactory = new LoggerFactory(loggerEspace, connection);

        TextArea loggerArea = new TextArea();
        loggerArea.setEditable(false);
        loggerArea.setPrefRowCount(4);
        loggerArea.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 12;");

        currentLogger = loggerFactory.createLogger("Console");

        DrawingShape drawingShape = new DrawingShape(600,600,currentLogger);
        palette.addObserver(drawingShape);
        palette.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10;");

        LoggerCheckMethod loggerCheckMenu = new LoggerCheckMethod(selectedLogger -> {
            currentLogger = loggerFactory.createLogger(selectedLogger);
            drawingShape.setLoggerMethod(currentLogger);
        });
        Menu loggerMenu = new Menu("Logger");
        loggerMenu.getItems().add(loggerCheckMenu);

        GraphManager graphManager = new GraphManager();
        drawingShape.setGraphManager(graphManager);
        int graphId = graphManager.createGraph("Graphe Initial");
        AppMenu appBar = new AppMenu(graphManager, drawingShape);
        appBar.getMenus().add(loggerMenu);



        BorderPane root = new BorderPane();
        root.setTop(appBar);
        root.setLeft(palette);
        root.setCenter(drawingShape);
        root.setBottom(loggerEspace);
        root.setStyle("-fx-background-color: #ffffff;");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
