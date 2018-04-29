import java.util.Scanner;

public class PermutationsRecursive {
     private String[] elements;
     private boolean[] used;
     private String[] perm;


    public static void main(String args[]){
        PermutationsRecursive permutationsRecursive = new PermutationsRecursive();
        permutationsRecursive.loadArguments();
        permutationsRecursive.per(0);
    }

    public void loadArguments(){
        Scanner scr = new Scanner(System.in);
        this.elements = scr.nextLine().split("\\s");
        this.used = new boolean[this.elements.length];
        this.perm = new String[this.elements.length];
    }

    public void per(int index){
        if (index >= this.elements.length)
            System.out.println(String.join(" ", this.perm));
        else
            for (int i = 0; i < this.elements.length; i++) {
                if (!this.used[i]) {
                    this.used[i] = true;
                    this.perm[index] = this.elements[i];
                    per(index + 1);
                    this.used[i] = false;
                }
            }
    }

}
