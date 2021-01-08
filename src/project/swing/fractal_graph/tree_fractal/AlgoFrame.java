package project.swing.fractal_graph.tree_fractal;

import javax.swing.*;
import java.awt.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/8 0:19
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

    // data
    private FractalData data;
    public void render(FractalData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{
        public AlgoCanvas(){
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            drawFractal(g2d, canvasWidth/2, canvasHeight, canvasHeight/2, 0, 0);
        }

        private void drawFractal(Graphics2D g, double x1, double y1, double side, double angle, int depth){
            if(side <= 0){
                return;
            }
            if(depth == data.depth){
                double x2 = x1 - side * 2 * Math.sin(angle*Math.PI/180.0);
                double y2 = y1 - side * 2 * Math.cos(angle*Math.PI/180.0);
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.drawLine(g, x1, y1, x2, y2);
                return;
            }
            double x2 = x1 - side * Math.sin(angle*Math.PI/180.0);
            double y2 = y1 - side * Math.cos(angle*Math.PI/180.0);
            AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
            AlgoVisHelper.drawLine(g, x1, y1, x2, y2);
            drawFractal(g, x2, y2, side/2, angle + data.splitAngle/2, depth + 1);
            drawFractal(g, x2, y2, side/2, angle - data.splitAngle/2, depth + 1);
            return;
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
