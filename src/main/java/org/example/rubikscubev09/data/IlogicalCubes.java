package org.example.rubikscubev09.data;

public interface IlogicalCubes {
    public void start();
    public boolean doStep(int step);
    public boolean doStep(String step);
    public Point[] getPoints();
    public boolean checkCubeSolved();
}
