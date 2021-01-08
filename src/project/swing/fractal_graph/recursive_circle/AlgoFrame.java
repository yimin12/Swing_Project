package project.swing.fractal_graph.recursive_circle;

import javax.swing.*;
import java.awt.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 18:03
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

    public AlgoFrame(String title){
        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // TODO: 设置自己的数据
    private CircleData data;
    public void render(CircleData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // TODO： 绘制自己的数据data
            drawCircle(g2d, data.getStartX(), data.getStartY(), data.getStartR(), 0);
        }

        private void drawCircle(Graphics2D g, int x, int y, int r, int depth){
            if(depth == data.getDepth() || r < 1){
                return;
            }
            if(depth % 2 == 0) AlgoVisHelper.setColor(g, AlgoVisHelper.Red);
            else AlgoVisHelper.setColor(g, AlgoVisHelper.LightBlue);
            AlgoVisHelper.fillCircle(g, x, y, r);
            drawCircle(g, x, y, r - data.getStep(), depth + 1);
            return;
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
