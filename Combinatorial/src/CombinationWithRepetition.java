import java.util.Scanner;

public class CombinationWithRepetition {
    private String[] elements;
    private String[] comb;
    private int k;


    public static void main(String args[]){
        CombinationWithRepetition  combinationWithRepetition = new CombinationWithRepetition();
        combinationWithRepetition.loadArguments();
        combinationWithRepetition.comb(0, 0);
    }

    public void loadArguments(){
        Scanner scr = new Scanner(System.in);
        this.elements = scr.nextLine().split("\\s");
        this.k = Integer.parseInt(scr.nextLine());
        this.comb = new String[this.k];
    }

    public void comb(int index, int start){
        if (index >= this.k) {
            System.out.println(String.join(" ", this.comb));
        }else {
            for (int i = start; i < this.elements.length; i++)
            {
                this.comb[index] = this.elements[i];
                comb(index + 1, i);
            }

        }
    }
}
