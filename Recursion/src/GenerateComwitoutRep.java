import java.util.Arrays;
import java.util.Scanner;

public class GenerateComwitoutRep {
    static int n;
    static int k;
    static int[] set;

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        n = Integer.parseInt(scr.nextLine());
        k = Integer.parseInt(scr.nextLine());
        set = new int[n];
        for (int i = 1; i <= n; i++) {
            set[i - 1] = i;
        }
        GenPerm(set, new int[k], 0, 0);
    }

    private static void GenPerm(int[] set, int[] vector, int index, int border) {
        if (index >= vector.length) {
            for (int i = 0; i < vector.length; i++) {
                System.out.print(vector[i] + " ");
            }
            System.out.println();
        } else {
            for (int j = border; j < set.length; j++) {
                vector[index] = set[j];
                GenPerm(set, vector, index + 1, j + 1);
            }
        }
    }
}
