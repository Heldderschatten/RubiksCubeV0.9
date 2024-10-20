package org.example.rubikscubev09.data;

import javafx.scene.paint.Color;

public class Point implements Cloneable{
    private String name;
    private Color color = Color.BLACK;
    //private String colorString = Color.BLACK;

    public Point(String name){
        this.name = name;
        System.out.println("create point:" + name);
    }
    public Point(int ID){
        this.name = "P" + ID;
        System.out.println("create point:" + this.name);
    }
    public String getName() {
        //System.out.println("Point.getName");
        return this.name;
    }

    public Point(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    public Point(int ID, Color color) {
        this.name = "P" + ID;
        this.color = color;
    }


    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "\t\t\t\t\"Point\" : " +
                "\n\t\t\t\t{" +
                "\n\t\t\t\t\t\"name\" : \"" + name + "\"," +
                "\n\t\t\t\t\t\"color\" : \"" + color +"\","+
                "\n\t\t\t\t}";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("Point.clone");
        return super.clone();
    }
}
