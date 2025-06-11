package dao;

import model.GraphNode;
import strategy_logger.singleton_db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraphNodeDAO {
    public GraphNode save(GraphNode node,int currentGrapheId) throws SQLException {
        String sql = "INSERT INTO graph_nodes (x, y,graph_id,type) VALUES (?, ?,?,?)";
        Connection conn = DBConnection.getInstance().getConnection();
        try (
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, node.getX());
            stmt.setDouble(2, node.getY());
            stmt.setString(4,node.getType());
            stmt.setInt(3,currentGrapheId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Échec de la création du nœud, aucune ligne affectée.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    node.setId(String.valueOf(generatedKeys.getInt(1))); // ⚠️ ID sous forme de String
                    return node;
                } else {
                    throw new SQLException("Échec de la récupération de l'ID du nœud.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return node;
    }

    public List<GraphNode> loadAll() {
        List<GraphNode> nodes = new ArrayList<>();
        String sql = "SELECT * FROM graph_nodes";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                nodes.add(new GraphNode(
                        rs.getString("id"),
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getString("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nodes;
    }
    public List<GraphNode> getByGraphId(int graphId) {
        List<GraphNode> nodes = new ArrayList<>();
        String sql = "SELECT * FROM graph_nodes WHERE graph_id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, graphId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                nodes.add(new GraphNode(
                        rs.getString("id"),
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getString("type")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nodes;
    }
    public GraphNode getNodeById(int nodeId) throws SQLException {
        String sql = "SELECT * FROM graph_nodes WHERE id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nodeId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new GraphNode(
                            String.valueOf(rs.getInt("id")),
                            rs.getDouble("x"),
                            rs.getDouble("y"),
                            rs.getString("type")
                    );
                } else {
                    return null; // Aucun nœud trouvé
                }
            }
        }
    }


}
