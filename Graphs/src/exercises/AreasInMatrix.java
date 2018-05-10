package exercises;

import java.util.*;

public class AreasInMatrix{
    static char[][] matrix;
    static int areasCount = 0;
    static Map<Character, Integer> areasCountByChar;
    static boolean[][] visited;

    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        int numberOfRows = Integer.parseInt(scr.nextLine());
        matrix = new char[numberOfRows][];
        areasCountByChar = new TreeMap<>();
        visited = new boolean[numberOfRows][];

        for (int i = 0; i < numberOfRows; i++){
            String line = scr.nextLine().trim();
            matrix[i] = line.toCharArray();
            visited[i] = new boolean[line.length()];
        }

        for (int row = 0; row < numberOfRows; row++){
            for (int col = 0; col < matrix[row].length; col++){
                if (!visited[row][col]){
                    areasCount++;
                    if (!areasCountByChar.containsKey(matrix[row][col])){
                        areasCountByChar.put(matrix[row][col], 0);
                    }
                    areasCountByChar.put(matrix[row][col], areasCountByChar.get(matrix[row][col]) + 1);
                    DFSArea(matrix[row][col], row, col);
                }
            }
        }

        System.out.println("Areas: " + areasCount);
        for(Map.Entry<Character, Integer> area : areasCountByChar.entrySet()){
            System.out.printf("Letter '%s' -> %d%n", area.getKey() + "", area.getValue());
        }
    }

    private static void DFSArea(char ch, int row, int col){
        if (isInsideMatrix(row, col)){
            char currentChar = matrix[row][col];
            if (currentChar == ch && !visited[row][col]){
                visited[row][col] = true;
                DFSArea(matrix[row][col], row - 1, col);
                DFSArea(matrix[row][col], row, col + 1);
                DFSArea(matrix[row][col], row + 1, col);
                DFSArea(matrix[row][col], row, col - 1);
            }
        }
        return;
    }
    private static boolean isInsideMatrix(int row, int col){

        return (row >= 0 && row < matrix.length)
                && (col >= 0 && col < matrix[row].length);
    }

}