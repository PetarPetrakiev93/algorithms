import java.util.Scanner;

public class Binomial {

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        System.out.println(binom(Integer.parseInt(scr.nextLine()),Integer.parseInt(scr.nextLine())));
    }

    private static long binom(int n, int k){
        if (k > n)
            return 0;
        if (k == 0 || k == n)
            return 1;
        return binom(n - 1, k - 1) + binom(n - 1, k);
    }

}
