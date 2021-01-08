package project.swing.warm_up.template;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 18:04
 *   @Description :
 *
 */
public class AlgoVisualizer {

    // Store ur own data
    private Object data; // data
    private AlgoFrame frame; // visual graphics

    public AlgoVisualizer(int sceneWidth, int sceneHeight){
        // init your data here

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // define the animation logic here
    private void run(){

    };


    private class AlgoKeyListener extends KeyAdapter {}
    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
