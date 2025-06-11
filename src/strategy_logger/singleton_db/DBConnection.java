package strategy_logger.singleton_db;

import java.sql.*;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/patterns?useSSL=false&serverTimezone=UTC";

    private final String user = "root";
    private final String password = "";

    private DBConnection() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de connexion à la base de données");
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                this.connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return connection;}
}
