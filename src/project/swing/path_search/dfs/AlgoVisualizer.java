package project.swing.path_search.dfs;

import java.awt.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 18:04
 *   @Description :
 *
 */
public class AlgoVisualizer {

    private static int DELAY = 1;
    private static int blockSide = 8;

    // Store ur own data
    private MazeData data;
    private AlgoFrame frame;
    private static final int dir[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(String mazeFile){
        // init your data here
        data = new MazeData(mazeFile);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // define the animation logic here
    private void run(){
        setData(-1, -1, false);
        if(!dfs(data.getEntranceX(), data.getEntranceY())){
            System.out.println("The maze has no solution!");
        }
        setData(-1, -1, false);
    }

    private boolean dfs(int x, int y){
        if(!data.inArea(x, y)) throw new IllegalArgumentException("this point is out of bound");
        data.visited[x][y] = true;
        setData(x, y, true);
        // base case
        if(x == data.getExitX() && y == data.getExitY()) return true;
        for(int i = 0; i < 4; i++){
            int neiX = x + dir[i][0];
            int neiY = y + dir[i][1];
            if(data.inArea(neiX, neiY) && data.getMaze(neiX, neiY) == MazeData.ROAD && !data.visited[neiX][neiY]){
                if(dfs(neiX, neiY)){
                    return true;
                }
            }
        }
        // backtracking
        setData(x, y, false);
        return false;
    }

    private void setData(int x, int y, boolean isPath){
        if(data.inArea(x, y)) data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String mazeFile = "maze_101_101.txt";
        AlgoVisualizer vis = new AlgoVisualizer(mazeFile);
    }
}
