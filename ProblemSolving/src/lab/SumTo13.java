package lab;

import java.util.*;

public class SumTo13{
    public static int targetSum = 13;

    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        String[] numberArgs = scr.nextLine().split(" ");
        int a = Integer.parseInt(numberArgs[0]);
        int b = Integer.parseInt(numberArgs[1]);
        int c = Integer.parseInt(numberArgs[2]);

        boolean isTargetSum =
                a + b + c == targetSum
                        || a + b - c == targetSum
                        || a - b + c == targetSum
                        || a - b - c == targetSum
                        || -a + b + c == targetSum
                        || -a + b - c == targetSum
                        || -a - b + c == targetSum
                        || -a - b - c == targetSum;

        System.out.println(isTargetSum ? "Yes" : "No");
    }
}
