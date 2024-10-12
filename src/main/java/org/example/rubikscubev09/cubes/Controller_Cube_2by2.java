package org.example.rubikscubev09.cubes;

import javafx.scene.paint.Color;
import org.example.rubikscubev09.data.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Controller_Cube_2by2 implements IlogicalCubes {
    private Graph g;
    private Graph solvedGraph;
    private int[] possibleMovesIntger = {1, 2, 3, 4, 5, 6};
    private Node[] nodes;
    private Map<String, Integer> possibleMovesStringToInt = new HashMap<>();
    int n = 8;

    public Controller_Cube_2by2() {
        System.out.println("Controller_Cube_2by2.Controller_Cube_2by2");
        start();
        possibleMovesStringToInt.put("U", 1);
        possibleMovesStringToInt.put("D", 2);
        possibleMovesStringToInt.put("R", 3);
        possibleMovesStringToInt.put("L", 4);
        possibleMovesStringToInt.put("F", 5);
        possibleMovesStringToInt.put("B", 6);
    }

    @Override
    public void start() {
        System.out.println("Controller_Cube_2by2.start");


        Point p1 = new Point(1, Color.BLUE);
        Point p2 = new Point(2, Color.RED);
        Point p3 = new Point(3, Color.GREEN);
        Point p4 = new Point(4, Color.PURPLE);
        Point p5 = new Point(5, Color.BLUE);
        Point p6 = new Point(6, Color.RED);
        Point p7 = new Point(7, Color.GREEN);
        Point p8 = new Point(8, Color.PURPLE);

        nodes = new Node[n];

        nodes[0] = new Node(p1, new int[]{4, 2});
        nodes[1] = new Node(p2, new int[]{2, 0});
        nodes[2] = new Node(p3, new int[]{0, 2});
        nodes[3] = new Node(p4, new int[]{2, 4});
        nodes[4] = new Node(p5, new int[]{3, 2});
        nodes[5] = new Node(p6, new int[]{2, 1});
        nodes[6] = new Node(p7, new int[]{1, 2});
        nodes[7] = new Node(p8, new int[]{2, 3});

        g = new Graph(n);
        for (Node n : nodes) {
            //g.addNode(n);
            g.addNode(new Node(new Point(n.getPoint().getName(), n.getPoint().getColor()),n.getPostion()));
        }

        g.addPath(1, 0, 1);
        g.addPath(2, 1, 1);
        g.addPath(3, 2, 1);
        g.addPath(0, 3, 1);

        g.addPath(7, 4, 2);
        g.addPath(4, 5, 2);
        g.addPath(5, 6, 2);
        g.addPath(6, 7, 2);

        g.addPath(4, 0, 3);
        g.addPath(0, 1, 3);
        g.addPath(5, 4, 3);
        g.addPath(1, 5, 3);

        g.addPath(6, 2, 4);
        g.addPath(2, 3, 4);
        g.addPath(7, 6, 4);
        g.addPath(3, 7, 4);

        g.addPath(3, 0, 5);
        g.addPath(7, 3, 5);
        g.addPath(0, 4, 5);
        g.addPath(4, 7, 5);

        g.addPath(5, 1, 6);
        g.addPath(1, 2, 6);
        g.addPath(6, 5, 6);
        g.addPath(2, 6, 6);

    }

    /*public void something() {
        Point p1 = new Point(1, Color.BLUE);
        Point p2 = new Point(2, Color.RED);
        Point p3 = new Point(3, Color.GREEN);
        Point p4 = new Point(4, Color.PURPLE);
        Point p5 = new Point(5, Color.BLUE);
        Point p6 = new Point(6, Color.RED);
        Point p7 = new Point(7, Color.GREEN);
        Point p8 = new Point(8, Color.PURPLE);

        Node n0 = new Node(p1);
        Node n1 = new Node(p2);
        Node n2 = new Node(p3);
        Node n3 = new Node(p4);
        Node n4 = new Node(p5);
        Node n5 = new Node(p6);
        Node n6 = new Node(p7);
        Node n7 = new Node(p8);

        solvedGraph = new Graph(8);
        solvedGraph.addNode(n0);
        solvedGraph.addNode(n1);
        solvedGraph.addNode(n2);
        solvedGraph.addNode(n3);
        solvedGraph.addNode(n4);
        solvedGraph.addNode(n5);
        solvedGraph.addNode(n6);
        solvedGraph.addNode(n7);
    }*/

    @Override
    public boolean doStep(int step) {
        System.out.println("Controller_Cube_2by2.doStep");
        for (int i : possibleMovesIntger) {
            if (i == step) {
                g.turn(step);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean doStep(String step) {
        System.out.println("Controller_Cube_2by2.doStep");
        return doStep(possibleMovesStringToInt.get(step));
    }

    @Override
    public Point[] getPoints() {
        System.out.println("Controller_Cube_2by2.getPoints");
        Point[] points = new Point[8];
        int counter = 0;
        for (Node n : g.getNodes()) {
            points[counter] = n.getPoint();
            counter++;
        }
        return points;

    }

    @Override
    public boolean checkCubeSolved() {
        System.out.println("Controller_Cube_2by2.checkCubeSolved");
        boolean solved = true;
        Node[] gNodes = g.getNodes();
        for (int i = 0; i < gNodes.length; i++) {
            if (!gNodes[i].getPoint().getName().equals(nodes[i].getPoint().getName())){
                solved = false;
            }
        }
        return solved;
    }

    public Graph getSolvedGraph() {
        return solvedGraph;
    }

    public int[] getPossibleMovesIntger() {
        return possibleMovesIntger;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Map<String, Integer> getPossibleMovesStringToInt() {
        return possibleMovesStringToInt;
    }

    public int getN() {
        return n;
    }

    public Graph getG() {
        return g;
    }

    @Override
    public String toString() {
        return g.toString();
    }
}
