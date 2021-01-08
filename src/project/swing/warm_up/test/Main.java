package project.swing.warm_up.test;

import java.awt.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 0:21
 *   @Description :
 *
 */
public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            // AlgoFrame frame = new AlgoFrame("Welcome", 500, 500);
            AlgoFrame frame = new AlgoFrame("Welcome");
        });
    }
}
