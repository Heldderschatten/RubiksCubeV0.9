package org.example.rubikscubev09.data;


public class Node {
private Point point;

    public Node(Point point) {
        this.point = point;
    }


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "\t\t\"Node\" : \n\t\t\t{\n" +
                point.toString() +
                "\n\t\t\t}";
    }
}
