package project.swing.path_search.bfs;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/7 15:23
 *   @Description :
 *
 */
public class Position {

    private int x, y;
    private Position prev;

    public Position(int x, int y, Position prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
    }

    public Position(int x, int y){
        this(x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPrev() {
        return prev;
    }
}
