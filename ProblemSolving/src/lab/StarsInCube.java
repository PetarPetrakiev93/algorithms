package lab;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StarsInCube {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        String[][][] cube = new String[n][n][n];

        for(int i = 0; i < n; i++){
            String[] lineArgs = scr.nextLine().split(" \\| ");
            for(int k = 0; k < lineArgs.length; k++){
                String[] elements = lineArgs[k].split(" ");
                for(int j = 0; j < elements.length; j++){
                    cube[k][i][j] = elements[j];
                }
            }
        }

        int totalCount = 0;
        Map<String, Integer> stars = new TreeMap<>();

        for(int i = 0; i < cube.length-2; i++){
            for(int k = 1; k < cube.length-1; k++){
                for(int j = 1; j < cube.length -1; j++){
                    String letter = cube[i][k][j];
                    if(cube[i+1][k][j].equals(letter) &&
                            cube[i+1][k-1][j].equals(letter) &&
                            cube[i+1][k+1][j].equals(letter) &&
                            cube[i+1][k][j-1].equals(letter) &&
                            cube[i+1][k][j+1].equals(letter) &&
                            cube[i+2][k][j].equals(letter)){
                        totalCount++;
                        if(!stars.containsKey(letter)){
                            stars.put(letter,1);
                        }else{
                            stars.put(letter,stars.get(letter)+1);
                        }
                    }
                }
            }
        }

        System.out.println(totalCount);
        for (Map.Entry<String, Integer> entry : stars.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(),  entry.getValue());
        }
    }
}
