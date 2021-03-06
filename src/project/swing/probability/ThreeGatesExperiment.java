package project.swing.probability;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/6 22:08
 *   @Description :
 *
 */
public class ThreeGatesExperiment {

    private int N;

    public ThreeGatesExperiment(int n) {
        if(n <= 0) throw new IllegalArgumentException("N must be larger than 0");
        N = n;
    }

    public void run(boolean changeDoor){
        int wins = 0;
        for(int i = 0; i < N; i++){
            if(play(changeDoor)){
                wins ++;
            }
        }
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println("winning rate: " + (double)wins/N);
    }

    private boolean play(boolean changeDoor){
        // There are three door here
        int prizeDoor = (int)(Math.random() * 3);
        int playerChoice = (int)(Math.random() * 3);
        if(playerChoice == prizeDoor){
            return changeDoor ? false : true;
        } else {
            return changeDoor ? true : false;
        }
    }

    public static void main(String[] args) {
        int N = 10000000;
        ThreeGatesExperiment exp = new ThreeGatesExperiment(N);
        exp.run(true);
        System.out.println();
        exp.run(false);
    }

}
