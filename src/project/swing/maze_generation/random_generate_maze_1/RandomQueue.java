package project.swing.maze_generation.random_generate_maze_1;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/7 21:28
 *   @Description :
 *
 */
public class RandomQueue<E> {

    private ArrayList<E> queue;

    public RandomQueue(){
        queue = new ArrayList<E>();;
    }

    public void add(E e){
        queue.add(e);
    }

    public E remove(){
        if(queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Qeuue");
        int randIndex = (int)(Math.random()*queue.size());
        E randElement = queue.get(randIndex);
        queue.set(randIndex, queue.get(queue.size()-1));
        queue.remove(queue.size()-1);
        return randElement;
    }

    public int size(){
        return queue.size();
    }

    public boolean empty(){
        return size() == 0;
    }
}
