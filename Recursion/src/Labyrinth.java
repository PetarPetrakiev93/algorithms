import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Labyrinth {
    static int rows, cols;
    static char[][] matrix;
    static boolean[][] progress;
    static List<Character> path = new ArrayList<>();

    public static void main(String[] args){
        matrix = readMatrix();
        findPath(0, 0, 'S');
    }

    private static void findPath(int row, int col, char direction) {
        if(!isInBounds(row, col)){
            return;
        }

        path.add(direction);

        if(isExit(row,col)){
            PrintPath();
        }else if(!isVisited(row, col) && isFree(row, col)){
            mark(row, col);
            findPath(row, col + 1, 'R');
            findPath(row, col - 1, 'L');
            findPath(row + 1, col, 'D');
            findPath(row - 1, col, 'U');
            unmark(row, col);
        }

        path.remove(path.size() - 1);
    }

    private static void unmark(int row, int col) {
        progress[row][col] = false;
    }

    private static void mark(int row, int col) {
        progress[row][col] = true;
    }

    private static boolean isFree(int row, int col) {
        return matrix[row][col] != '*';
    }

    private static boolean isVisited(int row, int col) {
        return progress[row][col];
    }

    private static void PrintPath() {
        for(int i = 1; i < path.size(); i++){
            System.out.print(path.get(i));
        }
        System.out.println( );
    }

    private static boolean isExit(int row, int col) {
        return matrix[row][col] == 'e';
    }

    private static boolean isInBounds(int row, int col) {
        if(row < rows && row >= 0 && col < cols && col >= 0){
            return true;
        }
        return false;
    }

    private static char[][] readMatrix() {
        Scanner scr = new Scanner(System.in);
        rows = Integer.parseInt(scr.nextLine());
        cols = Integer.parseInt(scr.nextLine());
        progress = new boolean[rows][cols];
        char[][] m = new char[rows][cols];
        for(int i = 0; i < rows; i++){
            String[] line = scr.nextLine().split("");
            for(int j = 0; j < line.length; j++){
                m[i][j] = line[j].charAt(0);
            }
        }
        return m;
    }
}
