package strategy_logger;

import javafx.scene.control.TextArea;
import model.ShapeEntity;

public class ConsoleLogger implements LoggerMethod{
    private final TextArea textArea;

    public ConsoleLogger(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void logger(ShapeEntity shape) {
        String s = "["+ConsoleLogger.class.getSimpleName()+"] : "+shape.getType()+ " x = "+shape.getX()+" , y = "+shape.getY()+"\n";
        textArea.appendText(s);
    }
}
