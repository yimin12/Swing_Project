package project.swing.path_search.iterative_dfs;


import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 18:04
 *   @Description :
 *
 */
public class AlgoVisualizer {

    // Store ur own data
    private static int DELAY = 5;
    private static int blockSide = 8;

    private MazeData data;
    private AlgoFrame frame;
    private static final int dir[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(String mazeFile){
        // init your data here
        data = new MazeData(mazeFile);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze Solver Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // define the animation logic here
    private void run(){
        setData(-1, -1, false);
        Deque<Position> stack = new LinkedList<>();
        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        stack.push(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;
        boolean isSolved = false;
        while(!stack.isEmpty()){
            Position curPos = stack.pop();
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
                    stack.push(new Position(neiX, neiY, curPos));
                    data.visited[neiX][neiY] = true;
                }
            }
        }
        if(!isSolved){
            System.out.println("The maze has no Solution!");
        }
        setData(-1, -1, false);
    }

    private void findPath(Position des){
        Position cur = des;
        while(cur != null){
            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }
    }

    private void setData(int x, int y, boolean isPath){
        if(data.inArea(x, y))
            data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String mazeFile = "maze_101_101.txt";
        AlgoVisualizer vis = new AlgoVisualizer(mazeFile);
    }
}
