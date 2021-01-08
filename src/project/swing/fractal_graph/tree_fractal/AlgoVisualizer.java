package project.swing.fractal_graph.tree_fractal;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/8 0:27
 *   @Description :
 *
 */
public class AlgoVisualizer {

    private static int DELAY = 40;
    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int depth, double splitAngle){
        data = new FractalData(depth, splitAngle);
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){
        setData(data.depth);
    }

    private void setData(int depth){
        data.depth = depth;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public void addAlgoKeyListener(){
        frame.addKeyListener(new AlgoKeyListener());
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9'){
                int depth = e.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {
        int depth = 21;
        double splitAngle = 60.0;
        int sceneWidth = 800;
        int sceneHeight = 800;
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, depth, splitAngle);
    }
}
