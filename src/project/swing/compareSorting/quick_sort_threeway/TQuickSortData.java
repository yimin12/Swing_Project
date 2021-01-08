package project.swing.compareSorting.quick_sort_threeway;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/7 13:19
 *   @Description :
 *
 */
public class TQuickSortData {

    public enum Type{
        Default,
        NearlyOrdered,
        Identical
    }

    private int[] numbers;
    public int left, right;
    public boolean[] fixedPivots;
    public int curPivot;
    public int curL, curR;

    public TQuickSortData(int N, int randomBound, Type dataType){
        numbers = new int[N];
        fixedPivots = new boolean[N];
        int leftBound = 1;
        int rightBound = randomBound;
        if(dataType == Type.Identical){
            int avgNumber = (leftBound + rightBound)/2;
            leftBound = avgNumber;
            rightBound = avgNumber;
        }
        for(int i = 0; i < N; i++){
            numbers[i] = (int)(Math.random() * (rightBound - leftBound + 1)) + leftBound;
            fixedPivots[i] = false;
        }
        if(dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(0.01*N);
            for(int i = 0; i < swapTime; i++){
                int a = (int)(Math.random()*N);
                int b = (int)(Math.random()*N);
                swap(a, b);
            }
        }
    }

    public TQuickSortData(int N, int randomBound){
        this(N, randomBound, Type.Default);
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if( index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        return numbers[index];
    }

    public void swap(int i, int j) {

        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }


}
