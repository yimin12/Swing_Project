package project.swing.path_search.bfs;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 18:04
 *   @Description :
 *
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;

    // Store ur own data
    private MazeData data; // data
    private AlgoFrame frame; // visual graphics
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
        LinkedList<Position> queue = new LinkedList<Position>();
        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        queue.addLast(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;
        boolean isSolved = false;
        while(queue.size() != 0){
            Position curPos = queue.pop();
            setData(curPos.getX(), curPos.getY(), true);
            if(curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()){
                isSolved = true;
                findPath(curPos);
                break;
            }
            for(int i = 0; i < 4; i++){
                int neiX = curPos.getX() + dir[i][0];
                int neiY = curPos.getY() + dir[i][1];
                if(data.inArea(neiX, neiY) && !data.visited[neiX][neiY] && data.getMaze(neiX, neiY) == MazeData.ROAD){
                    queue.addLast(new Position(neiX, neiY, curPos)); // record the prev position
                    data.visited[neiX][neiY] = true;
                }
            }
        }
        if(!isSolved){
            System.out.println("The maze has no Solution");
        }
        setData(-1, -1, false);
    };

    private void findPath(Position des){
        Position cur = des;
        while(cur != null){
            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }
    }

    private void setData(int x, int y, boolean isPath){
        if(data.inArea(x, y)) data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }


    private class AlgoKeyListener extends KeyAdapter {}
    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args) {

        String mazeFile = "maze_101_101.txt";

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer vis = new AlgoVisualizer(mazeFile);
    }
}
