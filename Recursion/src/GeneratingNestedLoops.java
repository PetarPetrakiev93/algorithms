import java.util.Scanner;

public class GeneratingNestedLoops {
    static int n;

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        n = Integer.parseInt(scr.nextLine());
        GenLoops(new int[n], 0);
    }

    private static void GenLoops(int[] vector, int index) {
        if (index >= vector.length) {
            for (int i = 0; i < vector.length; i++) {
                System.out.print(vector[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= n; i++) {
                vector[index] = i;
                GenLoops(vector, index + 1);
            }
        }
    }
}
