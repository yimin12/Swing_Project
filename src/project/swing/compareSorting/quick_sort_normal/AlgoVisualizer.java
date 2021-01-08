package project.swing.compareSorting.quick_sort_normal;

import java.awt.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/7 12:55
 *   @Description :
 *
 */
public class AlgoVisualizer {

    private static int DELAY = 40;
    private QuickSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, QuickSortData.Type dataType){
        data = new QuickSortData(N, sceneHeight, dataType);
        EventQueue.invokeLater(()->{
           frame = new AlgoFrame("Quick Sort Visualization", sceneWidth, sceneHeight);
           new Thread(()->{
               run();
           }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }

    public void run(){
        setData(-1, -1, -1, -1, -1);
        quickSort(0, data.N() - 1);
        setData(0, data.N() - 1, -1, -1, -1);
    }

    public void quickSort(int left, int right){
        if(left > right){
            return;
        }
        if(left == right){
            setData(left, right, left, -1, -1);
            return;
        }
        setData(left, right, -1, -1, -1);
        int p = partition(left, right);
        quickSort(left, p - 1);
        quickSort(p + 1, right);
    }

    private int partition(int left, int right){
        int pivot = (int)(Math.random() * (right - left + 1)) + left;
        setData(left, right, -1, pivot, -1);
        data.swap(left, pivot);
        int v = data.get(left);
        setData(left, right, -1, left, -1);

        int j = left;
        for(int i =left + 1; i <= right; i++){
            setData(left, right, -1, left, i);
            if(data.get(i) < v){
                j++;
                data.swap(j, i);
                setData(left, right, -1, left, i);
            }
        }
        data.swap(left, j);
        setData(left, right, j, -1, -1);
        return j;
    }

    private void setData(int left, int right, int fixedPivot, int curPivot, int curElement){
        data.left = left;
        data.right = right;
        if(fixedPivot != -1){
            data.fixedPivots[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curElement = curElement;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N, QuickSortData.Type.NearlyOrdered);
    }
}
