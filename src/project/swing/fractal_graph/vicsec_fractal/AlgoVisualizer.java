package project.swing.fractal_graph.vicsec_fractal;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 18:04
 *   @Description :
 *
 */
public class AlgoVisualizer {

    private static int DELAY = 40;
    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int maxDepth){
        data = new FractalData(maxDepth);
        int sceneWidth = (int)Math.pow(3, maxDepth);
        int sceneHeight = (int)Math.pow(3, maxDepth);

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

    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent event){
            //System.out.println("Key released:" + event);
            if(event.getKeyChar() >= '0' && event.getKeyChar() <= '9'){
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {
        int maxDepth = 6;
        AlgoVisualizer vis = new AlgoVisualizer(maxDepth);
    }
}
