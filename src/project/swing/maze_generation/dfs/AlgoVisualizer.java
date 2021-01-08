package project.swing.maze_generation.dfs;

import java.awt.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/7 20:31
 *   @Description :
 *
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;

    private MazeData data;
    private AlgoFrame frame;
    private static final int dir[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(int N, int M){

        // 初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){
        setData(-1, -1);
        dfs(data.getEntranceX(), data.getEntranceY() + 1);
        setData(-1, -1);
    }

    private void dfs(int x, int y){
        if(!data.inArea(x, y)){
            throw new IllegalArgumentException("x,y are out of index in go function!");
        }
        data.visited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int neiX = x + dir[i][0] * 2; // move 2 step in a time
            int neiY = y + dir[i][1] * 2;
            if(data.inArea(neiX, neiY) && !data.visited[neiX][neiY]){
                setData(x + dir[i][0], y + dir[i][1]);
                dfs(neiX, neiY);
            }
        }
    }

    private void setData(int x, int y){
        if(data.inArea(x, y)) data.maze[x][y] = MazeData.ROAD;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int N = 101;
        int M = 101;
        AlgoVisualizer vis = new AlgoVisualizer(N, M);
    }
}
