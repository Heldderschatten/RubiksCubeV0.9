package org.example.rubikscubev09.data;


public class Node implements Cloneable {
private Point point;
private int[] postion;

    public Node(Point point, int[] postion) {
        this.point = point;
        this.postion = postion;
    }


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int[] getPostion() {
        return postion;
    }

    public void setPostion(int[] postion) {
        this.postion = postion;
    }

    @Override
    public String toString() {
        String postionsdatas ="[";
        int i = 0;
        for(int n : postion){
            i++;
            postionsdatas += n;
            if(i != postion.length){
                postionsdatas += ",";
            }
        }
        postionsdatas += "],\n";
        return "\t\t\"Node\" : \n\t\t\t{\n" +
                point.toString() +
                "\n\t\t\tpostion : " + postionsdatas+
                "\n\t\t\t}";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("Node.clone");
        Node n = (Node) super.clone();
        return n;
    }
}
