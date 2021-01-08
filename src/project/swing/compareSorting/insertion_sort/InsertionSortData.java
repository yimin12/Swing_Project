package project.swing.compareSorting.insertion_sort;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 22:29
 *   @Description :
 *
 */
public class InsertionSortData {

    public enum Type{
        Default,
        NearlyOrdered
    }

    private int[] numbers;
    public int orderedIndex;
    public int currentIndex;

    public InsertionSortData(int N, int randomBound, Type dataType){
        numbers = new int[N];
        for(int i = 0; i < N; i++){
            numbers[i] = (int)(Math.random() * randomBound) + 1;
        }
        // disordered the order
        if(dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(0.02*N);
            for(int i = 0; i < swapTime; i++){
                int a = (int)(Math.random()*N);
                int b = (int)(Math.random()*N);
                swap(a, b);
            }
        }
    }

    public InsertionSortData(int N, int randomBound){
        this(N, randomBound, Type.Default);
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if(index < 0 || index >= numbers.length){
            throw new IllegalArgumentException("Invalid index to access Sort Data");
        }
        return numbers[index];
    }

    public void swap(int i, int j){
        if(i < 0 || i >= numbers.length || j < 0 || j >= numbers.length){
            throw new IllegalArgumentException("Invalid index to access Sort Data");
        }
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
