import java.util.Scanner;

public class VariationsWithoutRepetition {
    private String[] elements;
    private boolean[] used;
    private String[] var;
    private int k;


    public static void main(String args[]){
        VariationsWithoutRepetition  variationsWithoutRepetition = new VariationsWithoutRepetition();
        variationsWithoutRepetition.loadArguments();
        variationsWithoutRepetition.var(0);
    }

    public void loadArguments(){
        Scanner scr = new Scanner(System.in);
        this.elements = scr.nextLine().split("\\s");
        this.k = Integer.parseInt(scr.nextLine());
        this.used = new boolean[this.elements.length];
        this.var = new String[this.k];

    }

    public void var(int index){
        if (index >= this.k) {
            System.out.println(String.join(" ", this.var));
        }else {
            for (int i = 0; i < this.elements.length; i++) {
                if (!this.used[i]) {
                    this.used[i] = true;
                    this.var[index] = this.elements[i];
                    var(index + 1);
                    this.used[i] = false;
                }
            }
        }
    }
}
