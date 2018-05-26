package lab;

import java.util.Scanner;

public class Guitar {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] elementsArgs = scr.nextLine().split(", ");
        int[] c = new int[elementsArgs.length];
        for(int i = 0; i < elementsArgs.length; i++){
            c[i] = Integer.parseInt(elementsArgs[i]);
        }
        int base = Integer.parseInt(scr.nextLine());
        int max = Integer.parseInt(scr.nextLine());

        boolean[][] possibleVolumes = new boolean[c.length+1][];
        for(int row = 0; row < possibleVolumes.length; row++){
            possibleVolumes[row] = new boolean[max + 1];
        }

        possibleVolumes[0][base] = true;

        for(int row = 1; row <= c.length; row++){
            for(int preVolume = 0; preVolume <= max; preVolume++){
                if(!possibleVolumes[row-1][preVolume]){
                    continue;
                }
                int current = c[row-1];
                int upVolume = preVolume + current;
                int downVolume = preVolume - current;
                if (upVolume <= max){
                    possibleVolumes[row][upVolume] = true;
                }

                if (downVolume >= 0){
                    possibleVolumes[row][downVolume] = true;
                }
            }
        }

        int maxResult = -1;
        boolean[] lastRow = possibleVolumes[possibleVolumes.length-1];
        for(int col = lastRow.length - 1; col >= 0; col--){
            if (lastRow[col])
            {
                maxResult = col;
                break;
            }
        }

        System.out.println(maxResult);
    }
}
