package strategy_pcc;

import model.GraphEdge;
import model.GraphNode;

import java.util.List;

public class StrategyPCC {
    private PCC_Srategy strategy;

    public void setStrategy(PCC_Srategy strategy) {
        this.strategy = strategy;
    }

    public List<GraphNode> execute(List<GraphNode> nodes, List<GraphEdge> edges, GraphNode start, GraphNode end) {
        if (strategy == null) throw new IllegalStateException("Aucune stratégie définie");
        return strategy.findPath(nodes, edges, start, end);
    }
}
