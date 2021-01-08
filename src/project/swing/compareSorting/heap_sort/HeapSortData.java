package project.swing.compareSorting.heap_sort;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 23:18
 *   @Description :
 *
 */
public class HeapSortData {

    private int[] numbers;
    public int heapIndex;

    public HeapSortData(int N, int randomBound){
        numbers = new int[N];
        heapIndex = N;
        for(int i = 0; i < N; i++){
            numbers[i] = (int)(Math.random()*randomBound) + 1;
        }
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
