package project.swing.probability.money_expriment;

import java.awt.*;
import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 20:21
 *   @Description :
 *
 */
public class AlgoVisualizer {


    private static int DELAY = 40;
    private int[] money;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight){
        money = new int[100];
        for(int i = 0; i < money.length; i++){
            money[i] = 100;
        }
        // init graphic
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Money Problem", sceneWidth, sceneHeight);
            new Thread(()->{
               run();
            }).start();
        });
    }

    public void run(){
        while(true){
//            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(DELAY);

            for(int k = 0;k < 50; k++){
                for(int i = 0; i < money.length; i++){
                    int j = (int)(Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
                }
            }
        }
    }

    public static void main(String ... args){
        int sceneWidth = 1000;
        int sceneHight = 800;
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHight);
    }
}
