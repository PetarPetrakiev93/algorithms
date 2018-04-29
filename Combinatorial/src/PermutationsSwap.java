import java.util.Scanner;

public class PermutationsSwap {
    private String[] elements;

    public static void main(String args[]){
        PermutationsSwap permutationsSwap = new PermutationsSwap();
        permutationsSwap.loadArguments();
        permutationsSwap.per(0);
    }

    public void loadArguments(){
        Scanner scr = new Scanner(System.in);
        this.elements = scr.nextLine().split("\\s");
    }

    public void per(int index){
        if (index >= this.elements.length) {
            System.out.println(String.join(" ", this.elements));
        }
        else {
            per(index + 1);
            for (int i = index + 1; i < elements.length; i++) {
                swap(index, i);
                per(index + 1);
                swap(index, i);
            }
        }

    }

    private void swap(int index, int i) {
        String temp = this.elements[index];
        this.elements[index]=this.elements[i];
        this.elements[i] = temp;
    }
}
