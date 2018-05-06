import java.util.Scanner;

public class FibonacciBottomUp {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int count = Integer.parseInt(scr.nextLine());
        System.out.println(FibonacciBottomUp.fibonacci(count));
    }

    public static long fibonacci(int count){
        long x = 0;
        long y = 1;

        while (count > 0){
            long temp = x;
            x = y;
            y = temp + y;
            count--;
        }

        return x;
    }
}
