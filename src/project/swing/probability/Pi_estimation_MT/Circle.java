package project.swing.probability.Pi_estimation_MT;

import java.awt.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 21:45
 *   @Description :
 *
 */
public class Circle {

    private int x, y, r;

    public Circle(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getR(){ return r; }

    public boolean contain(Point p){
        return Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) <= r*r;
    }
}
