package strategy_logger;

import model.ShapeEntity;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements LoggerMethod{
    @Override
    public void logger(ShapeEntity shape) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            fw.write(shape.getType()+" x : "+shape.getX() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
