package project.swing.box_game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

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
    private GameData data;
    public void render(GameData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        private ArrayList<Color> colorList;
        private HashMap<Character, Color> colorMap;
        public AlgoCanvas(){
            super(true);
            colorList = new ArrayList<Color>();
            colorList.add(AlgoVisHelper.Red);
            colorList.add(AlgoVisHelper.Purple);
            colorList.add(AlgoVisHelper.Blue);
            colorList.add(AlgoVisHelper.Teal);
            colorList.add(AlgoVisHelper.LightGreen);
            colorList.add(AlgoVisHelper.Lime);
            colorList.add(AlgoVisHelper.Amber);
            colorList.add(AlgoVisHelper.DeepOrange);
            colorList.add(AlgoVisHelper.Brown);
            colorList.add(AlgoVisHelper.BlueGrey);
            colorMap = new HashMap<Character, Color>();
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

            // 具体绘制
            // TODO： 绘制自己的数据data
            int w = canvasWidth/data.M();
            int h = canvasHeight/data.N();

            Board showBoard = data.getShowBoard();
            for(int i = 0; i < showBoard.N(); i++){
                for(int j = 0; j < showBoard.M(); j++){
                    char c = showBoard.getData(i, j);
                    if(c != Board.EMPTY){
                        if(!colorMap.containsKey(c)){
                            int size = colorMap.size();
                            colorMap.put(c, colorList.get(size));
                        }
                        Color color = colorMap.get(c);
                        AlgoVisHelper.setColor(g2d, color);
                        AlgoVisHelper.fillRectangle(g2d, j*h+2, i*w+2, w-4, h-4);

                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.White);
                        String text = String.format("( %d , %d )", i, j);
                        AlgoVisHelper.drawText(g2d, text, j*h + h/2, i*w + w/2);
                    }
                    if( i == data.clickx && j == data.clicky) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                        AlgoVisHelper.setStrokeWidth(g2d, 4);
                        AlgoVisHelper.strokeRectangle(g2d, j * h + 2, i * w + 2, w - 4, h - 4);
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
