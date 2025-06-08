import decorator_shape.ShapeDecorator;
import decorator_shape.decorator_implementation.BorderDecorator;
import decorator_shape.decorator_implementation.ColorDecorator;
import factory_shape.FactoryShape;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import observer_shape.DrawingShape;
import observer_shape.ShapePalette;
import strategy_logger.ConsoleLogger;
import strategy_logger.LoggerMethod;
import view.AppMenu;
import view.DecorMenu;
import view.LoggerCheckMethod;
import view.LoggerEspace;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mini Projet JavaFX");

        // Barre de menu
        AppMenu appBar = new AppMenu();

        ShapePalette palette = new ShapePalette();
        LoggerCheckMethod loggerCheckMethod = new LoggerCheckMethod();
        LoggerEspace loggerEspace = new LoggerEspace();

        TextArea loggerArea = new TextArea();
        loggerArea.setEditable(false);
        loggerArea.setPrefRowCount(4);
        loggerArea.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 12;");

        LoggerMethod loggerMethod = new ConsoleLogger(loggerEspace);
        DrawingShape drawingShape = new DrawingShape(600,600,loggerMethod);
        palette.addObserver(drawingShape);
        palette.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10;");

        DecorMenu decorMenu = new DecorMenu();




        BorderPane root = new BorderPane();
        root.setRight(decorMenu);
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
