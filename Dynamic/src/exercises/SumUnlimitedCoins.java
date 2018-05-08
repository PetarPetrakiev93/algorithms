package exercises;

import java.util.*;

public class SumUnlimitedCoins{
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] coinsTokens = scr.nextLine().split(" ");
        int[] coins = new int[coinsTokens.length];
        for(int i = 0; i < coins.length; i++){
            coins[i] = Integer.parseInt(coinsTokens[i]);
        }
        int target = Integer.parseInt(scr.nextLine());
        System.out.println(calPossibleSums(coins, target));
    }

    private static int calPossibleSums(int[] nums, int targetSum){
        int[][] matrix = new int[nums.length + 1][targetSum+1];

        for (int i = 0; i <= nums.length; i++){
            matrix[i][0] = 1;
        }

        for (int i = 1; i <= targetSum; i++){
            matrix[0][i] = 0;
        }

        for (int i = 1; i <= nums.length; i++){
            for (int j = 1; j <= targetSum; j++){
                if (nums[i - 1] <= j){
                    int currentCoin = nums[i - 1];
                    matrix[i][j] = matrix[i][j - currentCoin] + matrix[i - 1][j];
                }
                else
                {
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }

        return matrix[nums.length][targetSum];
    }
}