package project.swing.maze_generation.iterative_dfs;

import java.awt.*;
import java.util.Stack;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/7 20:56
 *   @Description :
 *
 */
public class AlgoVisualizer {

    private static int DELAY = 20;
    private static int blockSide = 8;

    private MazeData data;
    private AlgoFrame frame;
    private static final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

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
        Stack<Position> stack = new Stack<Position>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        stack.push(first);
        data.visited[first.getX()][first.getY()] = true;
        while(!stack.isEmpty()){
            Position curPos = stack.pop();
            for(int i = 0; i < 4; i++){
                int neiX = curPos.getX() + d[i][0]*2;
                int neiY = curPos.getY() + d[i][1]*2;
                if(data.inArea(neiX, neiY) && !data.visited[neiX][neiY]){
                    stack.push(new Position(neiX, neiY));
                    data.visited[neiX][neiY] = true;
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
        setData(-1, -1);
    }

    private void setData(int x, int y){
        if(data.inArea(x, y)){
            data.maze[x][y] = MazeData.ROAD;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int N = 101;
        int M = 101;
        AlgoVisualizer vis = new AlgoVisualizer(N, M);
    }
}
