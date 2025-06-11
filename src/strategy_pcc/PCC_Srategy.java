package strategy_pcc;

import model.GraphEdge;
import model.GraphNode;

import java.util.List;

public interface PCC_Srategy {
    List<GraphNode> findPath(List<GraphNode> nodes, List<GraphEdge> edges, GraphNode start, GraphNode end);
}
