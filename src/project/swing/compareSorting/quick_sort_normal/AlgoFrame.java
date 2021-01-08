package project.swing.compareSorting.quick_sort_normal;

import javax.swing.*;
import java.awt.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/7 12:35
 *   @Description :
 *
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private QuickSortData data;
    public void render(QuickSortData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            super(true);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            int w = canvasWidth/data.N();
            for(int i = 0; i < data.N(); i++){
                if( i >= data.left && i <= data.right) AlgoVisHelper.setColor(g2d, AlgoVisHelper.Green);
                else AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);
                if(i == data.curPivot) AlgoVisHelper.setColor(g2d, AlgoVisHelper.Indigo);
                if(i == data.curElement) AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                if(data.fixedPivots[i]) AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
