package exercises;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnightsTour {
    private static final int[] directions = { 1, 2, -1, 2, 2, 1, 1, -2, -1, -2, -2, 1, 2, -1, -2, -1 };

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int size = Integer.parseInt(scr.nextLine());
        int[][] board = new int[size][size];
        int boardSize = size * size;
        int startRow = 0;
        int startCol = 0;
        int movesCount = 1;

        while (boardSize > 0){
            List<Tuple> possiblePositions = calcAllPossiblePositions(startRow, startCol, board);
            Tuple bestPosition = new Tuple(0, 0);
            int minMovesCount = Integer.MAX_VALUE;

            for (Tuple position : possiblePositions){
                int posibleMovesCount =
                        calcAllPossiblePositions(position.getX(), position.getY(), board).size();
                if (posibleMovesCount < minMovesCount){
                    minMovesCount = posibleMovesCount;
                    bestPosition = position;
                }
            }

            board[startRow][startCol] = movesCount++;
            boardSize--;
            startRow = bestPosition.getX();
            startCol = bestPosition.getY();
        }

        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                System.out.printf(" %3d", board[i][j]);
            }
            System.out.println();
        }
    }

    private static List<Tuple> calcAllPossiblePositions(int row, int col, int[][] board){
        List<Tuple> result = new ArrayList<>();
        for (int i = 0; i < directions.length; i += 2){
            int currRow = row + directions[i];
            int currCol = col + directions[i + 1];
            if (isInBounds(currRow, currCol, board.length)
                    && board[currRow][currCol] == 0){
                result.add(new Tuple(currRow, currCol));
            }
        }

        return result;
    }

    private static boolean isInBounds(int row, int col, int size    ) {
        return !(row >= size || col >= size || row < 0 || col < 0);
    }
}

class Tuple{
    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}