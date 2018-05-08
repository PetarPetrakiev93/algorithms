package lab;

import java.util.Scanner;

public class LCS {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        char[] firstSequence = scr.nextLine().toCharArray();
        char[] secondSequence = scr.nextLine().toCharArray();
        int[][] lcs = new int[firstSequence.length+1][secondSequence.length+1];

        for(int row = 1; row <= firstSequence.length; row++){
            for(int col = 1; col <= secondSequence.length; col++){
                int up = lcs[row-1][col];
                int left = lcs[row][col-1];

                int result = Math.max(up, left);

                if(firstSequence[row-1] == secondSequence[col-1]){
                    result = Math.max(1 + lcs[row - 1][col-1], result);
                }

                lcs[row][col] = result;
            }
        }

        System.out.println(lcs[firstSequence.length][secondSequence.length]);
    }
}
