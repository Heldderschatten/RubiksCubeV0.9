package org.example.rubikscubev09.data;

import java.util.HashMap;
import java.util.Map;

public class Cube implements IlogicalCubes{
    private Graph workingGraph;
    private Graph solvedGraph;
    private int sizeStone;
    private int[] possibleMovesIntger = {1, 2, 3, 4, 5, 6};
    private Map<String, Integer> possibleMovesStringToInt = new HashMap<>();
    private String name;



    public Cube(Graph graph, int sizeStone, String name) {
        try{
            this.workingGraph = (Graph) graph.clone();
            this.solvedGraph = (Graph) graph.clone();
        }catch (CloneNotSupportedException ex){
            System.out.println(ex);
        }
        this.sizeStone = sizeStone;
        this.name = name;
    }
    public void setPossibleMovesStringToInt(Map<String, Integer> possibleMovesStringToInt) {
        this.possibleMovesStringToInt = possibleMovesStringToInt;
    }

    @Override
    public void start() {
        System.out.println("Cube.start");
        try {
            this.workingGraph = (Graph) this.solvedGraph.clone();
        }catch (CloneNotSupportedException ex){
            System.out.println(ex);
        }
    }

    @Override
    public boolean doStep(int step) {
        System.out.println("Cube.doStep");
        for (int i : possibleMovesIntger) {
            if (i == step) {
                workingGraph.turn(step);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean doStep(String step) {
        System.out.println("Cube.doStep");
        return doStep(possibleMovesStringToInt.get(step));
    }

    @Override
    public Point[] getPoints() {
        System.out.println("Cube.getPoints");
        Point[] points = new Point[8];
        int counter = 0;
        for (Node n : workingGraph.getNodes()) {
            points[counter] = n.getPoint();
            counter++;
        }
        return points;
    }

    @Override
    public boolean checkCubeSolved() {
        boolean solved = true;
        Node[] gNodes = workingGraph.getNodes();
        Node[] nodes = solvedGraph.getNodes();
        for (int i = 0; i < gNodes.length; i++) {
            if (!gNodes[i].getPoint().getName().equals(nodes[i].getPoint().getName())){
                solved = false;
            }
        }
        return solved;
    }

    /**
     * Getter and Setter:
     * @return
     */
    public Graph getWorkingGraph() {
        return workingGraph;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkingGraph(Graph workingGraph) {
        this.workingGraph = workingGraph;
    }

    public Graph getSolvedGraph() {
        return solvedGraph;
    }

    public void setSolvedGraph(Graph solvedGraph) {
        this.solvedGraph = solvedGraph;
    }

    public int getSizeStone() {
        return sizeStone;
    }

    public void setSizeStone(int sizeStone) {
        this.sizeStone = sizeStone;
    }

    public int[] getPossibleMovesIntger() {
        return possibleMovesIntger;
    }

    public void setPossibleMovesIntger(int[] possibleMovesIntger) {
        this.possibleMovesIntger = possibleMovesIntger;
    }

    public Map<String, Integer> getPossibleMovesStringToInt() {
        return possibleMovesStringToInt;
    }
}
