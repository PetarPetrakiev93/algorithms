import java.util.HashSet;
import java.util.Scanner;

public class PermutationWithRepetition {
    private String[] elements;

    public static void main(String args[]) {
        PermutationWithRepetition permutations = new PermutationWithRepetition();
        permutations.loadArguments();
        permutations.per(0);
    }

    public void loadArguments() {
        Scanner scr = new Scanner(System.in);
        this.elements = scr.nextLine().split("\\s");
    }

    public void per(int index) {
        if (index >= this.elements.length) {
            System.out.println(String.join(" ", this.elements));
        } else {
            HashSet<String> swapped = new HashSet<>();
            for (int i = index; i < this.elements.length; i++) {
                if (!swapped.contains(elements[i])) {
                    swap(index, i);
                    per(index + 1);
                    swap(index, i);
                    swapped.add(this.elements[i]);
                }
            }
        }
    }

    private void swap(int index, int i) {
        String temp = this.elements[index];
        this.elements[index] = this.elements[i];
        this.elements[i] = temp;
    }
}
