package factory_logger;

import javafx.scene.control.TextArea;
import strategy_logger.ConsoleLogger;
import strategy_logger.*;

import java.sql.Connection;

public class LoggerFactory {
    private final TextArea textArea;
    private final Connection connection;

    public LoggerFactory(TextArea textArea, Connection connection) {
        this.textArea = textArea;
        this.connection = connection;
    }

    public LoggerMethod createLogger(String type) {
        switch (type) {
            case "Console":
                return new ConsoleLogger(textArea);
            case "Fichier":
                return new FileLogger();
            case "Base de données":
                return new DBLoggeer(connection);
            default:
                throw new IllegalArgumentException("Type de logger inconnu: " + type);
        }
    }
}
