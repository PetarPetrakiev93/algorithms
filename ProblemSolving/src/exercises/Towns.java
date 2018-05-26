package exercises;

import java.util.*;

public class Towns{
    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        int[] towns = new int[n];

        for(int i = 0; i < n; i++){
            towns[i] = Integer.parseInt(scr.nextLine().split(" ")[0]);
        }

        int[] lenLIS = LIS(towns);
        int[] townsReversesd = invertArray(towns);
        int[] lenLDS = invertArray(LIS(townsReversesd));
        int max = 0;
        for(int i = 0; i < lenLIS.length; i++){
            if(max < (lenLIS[i] + lenLDS[i]) - 1){
                max = (lenLIS[i] + lenLDS[i]) - 1;
            }
        }

        System.out.println(max);
    }


    private static int[] LIS(int[] seq){
        int[] len = new int[seq.length];
        for(int i = 0; i < seq.length; i++){
            int solution = 1;
            int currentNumber = seq[i];
            for(int j = 0; j < i; j++){
                int prevNum = seq[j];
                int prevSol = len[j];
                if(currentNumber > prevNum && solution <= prevSol){
                    solution = prevSol + 1;
                }
            }
            len[i] = solution;
        }
        return len;
    }

    private static int[] invertArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}