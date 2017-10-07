import java.util.HashSet;

public class  EightQueens {
    static int solutionsFound = 0;
    static final int size = 8;
    static boolean[][] chessboard = new boolean[size][size];
    static HashSet<Integer> attackedRows = new HashSet<>();
    static HashSet<Integer> attackedColums = new HashSet<>();
    static HashSet<Integer> attackedLeftDiagonals = new HashSet<>();
    static HashSet<Integer> attackedRightDiagonals = new HashSet<>();
    public static void main(String[] args){
        putQueens(0);
    }

    static void putQueens(int row){
        if(row == size){
            printSolution();

        }else{
            for(int col = 0; col < size; col++){
                if(canPlaceQueen(row, col)){
                    markAllAttackedPositions(row,col);
                    putQueens(row + 1);
                    unmarkAllAttackedPositions(row, col);
                }
            }
        }
    }

    private static void printSolution() {
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                if(chessboard[row][col]){
                    System.out.print("* ");
                }else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();

        solutionsFound++;
    }

    private static void unmarkAllAttackedPositions(int row, int col) {
        attackedRows.remove(row);
        attackedColums.remove(col);
        attackedLeftDiagonals.remove(col-row);
        attackedRightDiagonals.remove(col+row);
        chessboard[row][col] = false;
    }

    private static void markAllAttackedPositions(int row, int col) {
        attackedRows.add(row);
        attackedColums.add(col);
        attackedLeftDiagonals.add(col-row);
        attackedRightDiagonals.add(col+row);
        chessboard[row][col] = true;
    }

    private static boolean canPlaceQueen(int row, int col) {
        boolean positionOccupied = attackedRows.contains(row) ||
                attackedColums.contains(col) ||
                attackedLeftDiagonals.contains(col - row) ||
                attackedRightDiagonals.contains(row + col);
        return !positionOccupied;
    }
}
