package facade;

import dao.GraphDAO;

import java.util.List;

public class GraphManager {

    private final GraphDAO graphDAO = new GraphDAO();
    private int currentGraphId;
    private String currentGraphName;

    public int createGraph(String name) {
        currentGraphId = graphDAO.createGraph(name);
        currentGraphName = name;
        return currentGraphId;
    }

    public void renameGraph(String newName) {
        if (currentGraphId != 0) {
            graphDAO.renameGraph(currentGraphId, newName);
            currentGraphName = newName;
        }
    }

    public List<String> getAllGraphNames() {
        return graphDAO.getAllGraphNames();
    }

    public int loadGraphByName(String name) {
        currentGraphId = graphDAO.getGraphIdByName(name);
        currentGraphName = name;
        return currentGraphId;
    }

    public int getCurrentGraphId() {
        return currentGraphId;
    }

    public String getCurrentGraphName() {
        return currentGraphName;
    }
}
