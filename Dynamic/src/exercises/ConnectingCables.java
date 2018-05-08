package exercises;

import java.util.*;

public class ConnectingCables{
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] firstSequenceTokens = scr.nextLine().split(" ");
        int[] firstSequence = new int[firstSequenceTokens.length];
        int[] secondSequence = new int[firstSequenceTokens.length];
        for(int i = 0; i < firstSequenceTokens.length; i++){
            secondSequence[i] = i+1;
            firstSequence[i] = Integer.parseInt(firstSequenceTokens[i]);
        }

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

        System.out.println("Maximum pairs connected: " + lcs[firstSequence.length][secondSequence.length]);
    }
}