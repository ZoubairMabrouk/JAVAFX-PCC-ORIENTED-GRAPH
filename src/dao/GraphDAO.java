package dao;

import strategy_logger.singleton_db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraphDAO {
    public int createGraph(String name) {
        String baseName = "Graphe Initial";
        String selectSQL = "SELECT id FROM graphs WHERE name = ?";
        String insertSQL = "INSERT INTO graphs (name) VALUES (?)";

        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String uniqueName = baseName;
            int counter = 1;

            while (true) {
                try (PreparedStatement checkStmt = conn.prepareStatement(selectSQL)) {
                    checkStmt.setString(1, uniqueName);
                    ResultSet rs = checkStmt.executeQuery();
                    if (!rs.next()) {
                        break; // nom libre
                    }
                    uniqueName = baseName + " " + counter++;
                }
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, uniqueName);
                insertStmt.executeUpdate();

                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // retourne l'id du graphe créé
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // erreur
    }

    public void renameGraph(int id, String newName) {
        String sql = "UPDATE graphs SET name = ? WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllGraphNames() {
        List<String> names = new ArrayList<>();
        String sql = "SELECT name FROM graphs";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public int getGraphIdByName(String name) {
        String sql = "SELECT id FROM graphs WHERE name = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
