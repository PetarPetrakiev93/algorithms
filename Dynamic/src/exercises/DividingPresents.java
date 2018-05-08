package exercises;

import java.util.*;

public class DividingPresents{
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] presentsTokens = scr.nextLine().split(" ");
        int[] presents = new int[presentsTokens.length];
        for(int i = 0; i < presentsTokens.length; i++){
            presents[i] = Integer.parseInt(presentsTokens[i]);
        }


        int maxValue = Arrays.stream(presents).sum();
        int[] sums = new int[maxValue + 1];
        for(int i = 1; i < sums.length; i++){
            sums[i] = -1;
        }
        for(int presIndex = 0; presIndex < presents.length; presIndex++){
            for(int prevSumIndex = maxValue - presents[presIndex]; prevSumIndex >= 0 ; prevSumIndex--){
                if(sums[prevSumIndex] != -1 && sums[prevSumIndex + presents[presIndex]] == -1){
                    sums[prevSumIndex + presents[presIndex]] = presIndex;
                }
            }
        }


        int half = maxValue / 2;

        for(int i = half; i >= 0; i--){
            if(sums[i] == -1){
                continue;
            }

            System.out.println("Difference: " + (maxValue - i - i));
            System.out.println("Alan:" + i + " Bob:" + (maxValue - i));
            System.out.print("Alan takes:");

            while(i != 0){
                System.out.print(" " + presents[sums[i]]);
                i -= presents[sums[i]];
            }
            System.out.println();
            System.out.println("Bob takes the rest.");
        }

    }


}