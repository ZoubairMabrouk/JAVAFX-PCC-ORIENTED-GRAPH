package strategy_pcc;

import model.GraphNode;

import java.util.List;

public class StrategyPCC {
    private PCC_Srategy pccSrategy;
    public void setPccSrategy(PCC_Srategy pccSrategy){
        this.pccSrategy = pccSrategy;
    }
    public List<GraphNode> caluculate(){
       return pccSrategy.pcc();
    }
}
