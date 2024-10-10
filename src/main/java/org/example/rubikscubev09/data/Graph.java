package org.example.rubikscubev09.data;


import java.util.Arrays;

public class Graph {

    private int n;
    private int numberOfNodes = 0;
    private int[][] adjacencylists;
    private Node[] nodes;

    public Graph(int n) {
        System.out.println("Graph.Graph");
        this.n = n;
        this.adjacencylists = new int[n][n];
        nodes = new Node[n];
        System.out.println("Der Graph ist " + n + " Knoten groß");
    }

    public Graph(int n, int numberOfNodes, int[][] adjacencylists, Node[] nodes) {
        this.n = n;
        this.numberOfNodes = numberOfNodes;
        this.adjacencylists = adjacencylists;
        this.nodes = nodes;
    }

    public boolean addNode(Node theNode) {
        System.out.println("Graph.addNode");
        if (numberOfNodes < n) {
            nodes[numberOfNodes] = theNode;
            numberOfNodes++;
            return true;
        } else {
            return false;
        }
    }

    public boolean addNode(Node theNode, int index) {
        System.out.println("Graph.addNode");
        if (index < n && index >= 0) {
            nodes[index] = theNode;
            return true;
        } else {
            return false;
        }
    }

    public boolean addPath(int start, int end, int wight) {
        System.out.println("Graph.addPath");
        if (start < n && start >= 0 && end < n && end >= 0) {
            adjacencylists[start][end] = wight;
            System.out.println("Path Create from: " + start + " to " + end + " whight: " + wight);
            return true;
        } else {
            System.out.println("Error--create Path failed");
            return false;
        }
    }

    public Node[] getNodes() {
        System.out.println("Graph.getNodes");
        return nodes;
    }

    public void turn(int wight) {
        System.out.println("Graph.turn");
        System.out.println("Go step:" + wight);
        int counter = 0;
        int firstStart = -1;
        int firstEnd = -1;
        boolean found = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencylists[i][j] == wight) {
                    found = true;
                    firstStart = i;
                    firstEnd = j;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (firstStart < 0 || firstEnd < 0) {
            return;
        }
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (adjacencylists[firstStart][i] == wight) {
                end = i;
                break;
            }
        }
        turnTwo(firstStart, wight, firstStart, true);

    }

    private void turnTwo(int start, int wight, int firstStart, boolean firstTime) {
        System.out.println("Graph.turnTwo");
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (adjacencylists[start][i] == wight) {
                end = i;
                break;
            }
        }
        if (end == -1) {
            System.out.print("ERROR -1 in: ");
            System.out.println("Graph.turnTwo with:");
            System.out.println("start = " + start + ", wight = " + wight + ", firstStart = " + firstStart + ", firstTime = " + firstTime);
            return;
        }
        Point p = nodes[start].getPoint();
        System.out.println(p.getName());
        if (start != firstStart || firstTime) {
            turnTwo(end, wight, firstStart, false);
        }
        nodes[end].setPoint(p);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        //Safe n
        sb.append("\n\t\"n\" : " + n + ",");
        //safe number of Nodes
        sb.append("\n\t\"numberOfNodes\" : " + numberOfNodes + ",");

        //make String for AdjeacnyList
        sb.append("\n\t\"adjacencylists\" : ");
        sb.append("[");
        for (int i = 0; i < adjacencylists.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append("[");
            for (int j = 0; j < adjacencylists[i].length; j++) {
                sb.append(adjacencylists[i][j]);
                if (j != adjacencylists[i].length - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
        }
        sb.append("],");
        //Safe nodes
        sb.append("\n\t\"nodes\" : [");
        int counter = 0;
        for (Node n : nodes) {
            sb.append("\n" + n.toString());
            if (counter < nodes.length-1) {
                sb.append(",");
            }
            counter++;

        }
        sb.append("\n]");
        String s = sb.toString() +
                "\n}";
       // System.out.println(s);
        return s;
    }

}
