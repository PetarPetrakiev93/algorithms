import java.util.HashSet;
import java.util.Scanner;

public class Snakes {
    private String[] elements;
    private String[] var;
    private int k;
    private HashSet<String> memory;
    public static void main(String args[]){
        Snakes snakes = new Snakes();
        snakes.loadArguments();
        snakes.var(0);
    }
    public void loadArguments(){
        Scanner scr = new Scanner(System.in);
        this.elements = new String[]{"R", "D", "L", "U"};
        this.k = Integer.parseInt(scr.nextLine());
        this.var = new String[this.k-1];
        this.memory = new HashSet<String>();
    }
    public void var(int index){
        if (index >= this.k-1) {
            if(this.memory.size() > 0){
                if(!checkForExisting("S" + String.join("", this.var))){
                    System.out.println("S" + String.join("", this.var));
                    this.memory.add("S" + String.join("", this.var));
                }
            }else{
                System.out.println("S" + String.join("", this.var));
                this.memory.add("S" + String.join("", this.var));
            }

        }else {
            for (int i = 0; i < this.elements.length; i++)
            {
                if(index == 0 && !this.elements[i].equals("R")){
                    continue;
                }
                if(index != 0){
                    if(this.var[index-1].equals("R") && this.elements[i].equals("L")){
                        continue;
                    }
                    if(this.var[index-1].equals("L") && this.elements[i].equals("R")){
                        continue;
                    }
                    if(this.var[index-1].equals("U") && this.elements[i].equals("D")){
                        continue;
                    }
                    if(this.var[index-1].equals("D") && this.elements[i].equals("U")){
                        continue;
                    }
                }

                this.var[index] = this.elements[i];
                var(index + 1);
            }
        }
    }

    public boolean checkForExisting(String snake) {
        boolean check = false;
        System.out.println(snake);
        for (String s : this.memory) {
            int[][] matrix = new int[s.length() * 2 - 1][s.length() * 2 - 1];
            int x = s.length() - 1;
            int y = s.length() - 1;
            matrix[x][y] = 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == 'R') {
                    y++;
                } else if (s.charAt(i) == 'L') {
                    y--;
                } else if (s.charAt(i) == 'D') {
                    x++;
                } else if (s.charAt(i) == 'U') {
                    x--;
                }
                matrix[x][y] = 1;
            }
            int left = matrix.length;
            int right = 0;
            int top = matrix.length;
            int bottom = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int k = 0; k < matrix[i].length; k++) {
                    if (matrix[i][k] == 1) {
                        if(i < top){
                            top = i;
                        }
                        if(i > bottom){
                            bottom = i;
                        }
                        if(k < left){
                            left = k;
                        }
                        if(k > right){
                            right = k;
                        }
                    }
                }
            }
            int[][] sMatrix = new int[(bottom-top)+1][(right-left)+1];

            for (int i = top; i <= bottom; i++) {
                for (int k = left; k <= right; k++) {
                    sMatrix[i - top][k - left] = matrix[i][k];
                }
            }

            for (int i = 0; i < sMatrix.length; i++) {
                for (int k = 0; k < sMatrix[i].length; k++) {
                    System.out.print(sMatrix[i][k]);
                }
                System.out.println();
            }
            System.out.println();

            int[][] matrix2 = new int[snake.length() * 2 - 1][snake.length() * 2 - 1];
            x = snake.length() - 1;
            y = snake.length() - 1;
            matrix2[x][y] = 1;
            for (int i = 1; i < snake.length(); i++) {
                if (snake.charAt(i) == 'R') {
                    y++;
                } else if (snake.charAt(i) == 'L') {
                    y--;
                } else if (snake.charAt(i) == 'D') {
                    x++;
                } else if (snake.charAt(i) == 'U') {
                    x--;
                }
                matrix2[x][y] = 1;
            }

            left = matrix.length;
            right = 0;
            top = matrix.length;
            bottom = 0;
            for (int i = 0; i < matrix2.length; i++) {
                for (int k = 0; k < matrix2[i].length; k++) {
                    if (matrix2[i][k] == 1) {
                        if(i < top){
                            top = i;
                        }
                        if(i > bottom){
                            bottom = i;
                        }
                        if(k < left){
                            left = k;
                        }
                        if(k > right){
                            right = k;
                        }
                    }
                }
            }

            int[][] sMatrix2 = new int[(bottom-top)+1][(right-left)+1];

            for (int i = top; i <= bottom; i++) {
                for (int k = left; k <= right; k++) {
                    sMatrix2[i - top][k - left] = matrix2[i][k];
                }
            }

            for (int i = 0; i < sMatrix2.length; i++) {
                for (int k = 0; k < sMatrix2[i].length; k++) {
                    System.out.print(sMatrix2[i][k]);
                }
                System.out.println();
            }
            System.out.println();

        }
        return true;
    }
}
