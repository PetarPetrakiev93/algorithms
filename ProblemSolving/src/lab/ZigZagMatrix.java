package lab;

import java.util.*;
import java.util.stream.Collectors;

public class ZigZagMatrix{
    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        int rows = Integer.parseInt(scr.nextLine());
        int cols = Integer.parseInt(scr.nextLine());
        int[][] matrix = new int[rows][cols];

        for(int i = 0; i < rows; i++){
            String[] lineArgs = scr.nextLine().split(",");
            for(int j = 0; j < lineArgs.length; j++){
                matrix[i][j] = Integer.parseInt(lineArgs[j]);
            }
        }

        int[][] maxPaths = new int[rows][cols];
        int[][] previousRowIndex = new int[rows][cols];

        for(int row = 1; row < rows; row++){
            maxPaths[row][0] = matrix[row][0];
        }

        for(int col = 1; col < cols; col++){
            for(int row = 0; row < rows; row++){
                int previousMax = 0;
                if(col % 2 != 0){
                    for(int i = row + 1; i < rows; i++){
                        if(maxPaths[i][col - 1] > previousMax){
                            previousMax = maxPaths[i][col-1];
                            previousRowIndex[row][col] = i;
                        }
                    }
                }else{
                    for(int i = 0; i <= row-1; i++){
                        if(maxPaths[i][col - 1] > previousMax){
                            previousMax = maxPaths[i][col-1];
                            previousRowIndex[row][col] = i;
                        }
                    }
                }
                maxPaths[row][col] = previousMax + matrix[row][col];
            }
        }

        int currentRowIndex = -1;
        int globalMax = 0;

        for(int row = 0; row < maxPaths.length; row++){
            if(maxPaths[row][cols-1] > globalMax){
                globalMax = maxPaths[row][cols-1];
                currentRowIndex = row;
            }
        }

        List<Integer> path = new ArrayList<>();
        int columnIndex = cols - 1;

        while(columnIndex >= 0){
            path.add(matrix[currentRowIndex][columnIndex]);
            currentRowIndex = previousRowIndex[currentRowIndex][columnIndex];
            columnIndex--;
        }

        List<String> pathStrings = path.stream().map(a -> a + "").collect(Collectors.toList());
        Collections.reverse(pathStrings);

        System.out.printf("%d = %s", globalMax, String.join(" + ", pathStrings));
    }
}