package exercises;

import java.util.Scanner;

public class MinimalEditDistance {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int costReplace = Integer.parseInt(scr.nextLine().substring("cost-replace = ".length()));
        int costInsert = Integer.parseInt(scr.nextLine().substring("cost-insert = ".length()));
        int costDelete = Integer.parseInt(scr.nextLine().substring("cost-delete = ".length()));

        String s1 = scr.nextLine().substring("s1 = ".length());
        String s2 = scr.nextLine().substring("s2 = ".length());

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for(int i = 1; i <= s2.length(); i++){
            dp[0][i] = dp[0][i -1] + costInsert;
        }

        for(int i = 1; i <= s1.length(); i++){
            dp[i][0] = dp[i-1][0] + costDelete;
        }

        for(int i = 1; i <= s1.length(); i++){
            for(int j = 1; j <= s2.length(); j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int delete = dp[i-1][j] + costDelete;
                    int insert = dp[i][j-1] + costInsert;
                    int replace = dp[i-1][j-1] + costReplace;

                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        System.out.println("Minimum edit distance: " + dp[s1.length()][s2.length()]);
    }
}
