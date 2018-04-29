import java.util.Scanner;

public class VariationsWithRepetitions {
    private String[] elements;
    private String[] var;
    private int k;


    public static void main(String args[]){
        VariationsWithRepetitions variationsWithoutRepetition = new VariationsWithRepetitions();
        variationsWithoutRepetition.loadArguments();
        variationsWithoutRepetition.var(0);
    }

    public void loadArguments(){
        Scanner scr = new Scanner(System.in);
        this.elements = scr.nextLine().split("\\s");
        this.k = Integer.parseInt(scr.nextLine());
        this.var = new String[this.k];

    }

    public void var(int index){
        if (index >= this.k) {
            System.out.println(String.join(" ", this.var));
        }else {
            for (int i = 0; i < this.elements.length; i++) {
                this.var[index] = this.elements[i];
                var(index + 1);
            }
        }
    }
}
