package dao;
import model.GraphEdge;
import model.GraphNode;
import strategy_logger.singleton_db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphEdgeDAO {
    GraphNodeDAO graphNodeDAO = new GraphNodeDAO();
    public void save(GraphEdge edge, int currentGraphId) throws SQLException {
        String sql = "INSERT INTO graph_edges (from_id, to_id, weight,graph_id) VALUES (?, ?, ?,?)";
        Connection conn = DBConnection.getInstance().getConnection();
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, edge.getFrom().getId());
            stmt.setString(2, edge.getTo().getId());
            stmt.setDouble(3, edge.getWeight());
            stmt.setInt(4,currentGraphId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GraphEdge> loadAll(Map<String, GraphNode> nodeMap) {
        List<GraphEdge> edges = new ArrayList<>();
        String sql = "SELECT * FROM graph_edges";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                GraphNode from = nodeMap.get(rs.getString("from_id"));
                GraphNode to = nodeMap.get(rs.getString("to_id"));
                double weight = rs.getDouble("weight");

                if (from != null && to != null) {
                    edges.add(new GraphEdge(from, to, weight));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return edges;
    }
    public List<GraphEdge> getByGraphId(int graphId, Map<String, GraphNode> nodeMap) {
        List<GraphEdge> edges = new ArrayList<>();
        String sql = "SELECT * FROM graph_edges WHERE graph_id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, graphId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String fromId = rs.getString("from_id");
                    String toId = rs.getString("to_id");
                    double weight = rs.getDouble("weight");

                    GraphNode from = nodeMap.get(fromId);
                    GraphNode to = nodeMap.get(toId);

                    if (from != null && to != null) {
                        edges.add(new GraphEdge(from, to, weight));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return edges;
    }

}
