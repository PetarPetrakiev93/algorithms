package exercises;

import java.util.*;

public class SumLimitedCoins{
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] coinsTokens = scr.nextLine().split(" ");
        int[] coins = new int[coinsTokens.length];
        for(int i = 0; i < coins.length; i++){
            coins[i] = Integer.parseInt(coinsTokens[i]);
        }
        int target = Integer.parseInt(scr.nextLine());
        System.out.println(calPossibleSumSet(coins, target));
    }

    private static int calPossibleSumSet(int[] nums, int targetSum){
        int counter = 0;
        Set<Integer> possibleSums = new HashSet<>();
        possibleSums.add(0);
        for(int i = 0; i < nums.length; i++){
            Set<Integer> newSums = new HashSet<>();
            for(Integer sum : possibleSums){
                Integer newSum = sum + nums[i];
                newSums.add(newSum);
                if(newSum == targetSum){
                    counter++;
                }
            }
            possibleSums.addAll(newSums);
        }

        return counter;
    }
}