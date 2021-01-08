package project.swing.maze_generation.maze_gen_solve;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/7 21:52
 *   @Description :
 *
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;

    private MazeData data;
    private AlgoFrame frame;
    private static final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(int N, int M){

        data = new MazeData(N, M);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){
        setRoadData(-1, -1);
        RandomQueue<Position> queue = new RandomQueue<Position>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;
        data.openMist(first.getX(), first.getY());
        while(queue.size() != 0){
            Position curPos = queue.remove();
            for(int i = 0; i < 4; i++){
                int neiX = curPos.getX() + d[i][0]*2;
                int neiY = curPos.getY() + d[i][1]*2;
                if(data.inArea(neiX, neiY) && !data.visited[neiX][neiY]){
                    queue.add(new Position(neiX, neiY));
                    data.visited[neiX][neiY] = true;
                    data.openMist(neiX, neiY);
                    setRoadData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
        setRoadData(-1, -1);
    }

    private boolean dfs(int x, int y){
        if(!data.inArea(x,y))
            throw new IllegalArgumentException("x,y are out of index in go function!");
        data.visited[x][y] = true;
        setPathData(x, y, true);
        if(x == data.getExitX() && y == data.getExitY()) return true;
        for(int i= 0; i < 4; i++){
            int neiX = x + d[i][0];
            int neiY = y + d[i][1];
            if(data.inArea(neiX, neiY) && data.maze[neiX][neiY] == MazeData.ROAD && !data.visited[neiX][neiY]){
                if(dfs(neiX, neiY)) return true;
            }
        }
        setPathData(x, y, false);
        return false;
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar() == ' '){
                for(int i = 0; i < data.N(); i++){
                    for(int j = 0; j < data.M(); j++){
                        data.visited[i][j] = false;
                    }
                }
                new Thread(() -> {
                    dfs(data.getEntranceX(), data.getEntranceY());
                }).start();
            }
        }
    }

    private void setRoadData(int x, int y){
        if(data.inArea(x, y))
            data.maze[x][y] = MazeData.ROAD;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void setPathData(int x, int y, boolean isPath){
        if(data.inArea(x, y))
            data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int N = 101;
        int M = 101;
        AlgoVisualizer vis = new AlgoVisualizer(N, M);
    }
}
