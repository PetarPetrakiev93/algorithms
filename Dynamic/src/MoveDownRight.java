import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoveDownRight {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        int m = Integer.parseInt(scr.nextLine());
        int[][] numbers = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] lineArgs = scr.nextLine().split("\\s+");
            for (int j = 0; j < lineArgs.length; j++) {
                numbers[i][j] = Integer.parseInt(lineArgs[j]);
            }
        }
        int[][] sums = new int[n][m];
        sums[0][0] = numbers[0][0];
        for (int row = 1; row < n; row++) {
            sums[row][0] = sums[row - 1][0] + numbers[row][0];
        }

        for (int col = 1; col < n; col++) {
            sums[0][col] = sums[0][col - 1] + numbers[0][col];
        }

        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                int result = Math.max(sums[row - 1][col], sums[row][col - 1]) + numbers[row][col];
                sums[row][col] = result;
            }
        }

        List<String> result = new ArrayList<>();
        result.add(String.format("[%d, %d]", n - 1, m - 1));
        int currentRow = n-1;
        int currentCol = m-1;

        while (currentRow != 0 || currentCol != 0){
            int top = -1;
            if(currentRow - 1 >= 0){
                top = sums[currentRow-1][currentCol];
            }
            int left = -1;
            if(currentCol - 1 >= 0){
                left = sums[currentRow][currentCol-1];
            }
            if(top > left){
                result.add(String.format("[%d, %d]", currentRow- 1, currentCol));
                currentRow -= 1;
            }else{
                result.add(String.format("[%d, %d]", currentRow, currentCol-1));
                currentCol -= 1;
            }
        }

        for(int i = result.size()-1; i >= 0; i--){
            System.out.print(result.get(i) + " ");
        }
    }
}
