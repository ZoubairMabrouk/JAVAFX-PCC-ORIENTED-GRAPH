package strategy_logger;

import model.ShapeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBLoggeer implements LoggerMethod{
    private final Connection connection;

    public DBLoggeer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void logger(ShapeEntity shape) {
        String sql = "INSERT INTO shapes (type, x, y, x2, y2) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, shape.getType());
            stmt.setDouble(2, shape.getX());
            stmt.setDouble(3, shape.getY());
            stmt.setDouble(4, shape.getX2());
            stmt.setDouble(5, shape.getY2());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
