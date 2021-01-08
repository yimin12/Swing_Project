package project.swing.compareSorting.quick_sort_threeway;


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

    private static int DELAY = 20;
    // Store ur own data
    private TQuickSortData data; // data
    private AlgoFrame frame; // visual graphics

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, TQuickSortData.Type type){
        // init your data here

        data = new TQuickSortData(N, sceneHeight, type);

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Three Ways Quick Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // define the animation logic here
    private void run(){
        setData(-1, -1, -1, -1, -1, -1);
        quickSort3Ways(0, data.N() - 1);
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort3Ways(int left, int right){
        if(left  > right){
            return;
        }
        if(left == right){
            setData(left, right, left, -1, -1, -1);
            return;
        }
        setData(left, right, -1, -1, -1, -1);
        int pivot = (int)(Math.random() * (right - left + 1)) + left;
        setData(left, right, -1, pivot, -1, -1);

        data.swap(left, pivot);
        int v = data.get(left);
        setData(left, right, -1, left, -1, -1);

        int lt = left;
        int gt = right + 1;
        int i = left + 1;
        setData(left, right, -1, left, lt, gt);
        while(i < gt){
            if(data.get(i) < v){
                data.swap(i, lt + 1);
                i++;
                lt++;
            } else if(data.get(i) > v){
                data.swap(i, gt - 1);
                gt--;
            } else i++;
        }
        data.swap(left, lt);
        setData(left, right, lt, -1, -1, -1);
        quickSort3Ways(left, lt -1);
        quickSort3Ways(gt, right);
    }

    private void setData(int left, int right, int fixedPivot, int curPivot, int curL, int curR){
        data.left = left;
        data.right = right;
        if(fixedPivot != -1){
            data.fixedPivots[fixedPivot] = true;
            int i = fixedPivot;
            while(i < data.N() && data.get(i) == data.get(fixedPivot)){
                data.fixedPivots[i] = true;
                i++;
            }
        }
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }


    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

//        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N, TQuickSortData.Type.Default);
//         AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N, TQuickSortData.Type.NearlyOrdered);
         AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N, TQuickSortData.Type.Identical);
    }
}
