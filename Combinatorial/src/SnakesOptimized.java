import java.util.HashSet;
import java.util.Scanner;

public class SnakesOptimized {
    private int n;
    private static HashSet<String> visited = new HashSet<>();
    private static HashSet<String> snakes = new HashSet<>();
    private char[] currentSnake;
    private static int count;

    public static void main(String[] args) {
        SnakesOptimized snakesOptimized = new SnakesOptimized();
        snakesOptimized.loadArguments();
        visited.add(0 + " " + 0);
        snakesOptimized.gen(1, 0, 1, 'R');
        System.out.println("Snakes count = " + count);
    }

    private void gen(int index, int row, int col, char direction) {
        if (index >= n) {
            String snake = new String(this.currentSnake);
            if (!this.snakes.contains(snake)) {
                markSnake(snake);
                return;
            }

        } else {
            String currentCell = row + " " + col;
            if (!this.visited.contains(currentCell)) {

                this.visited.add(currentCell);

                this.currentSnake[index] = direction;

                gen(index + 1, row, col + 1, 'R');
                gen(index + 1, row + 1, col, 'D');
                gen(index + 1, row, col - 1, 'L');
                gen(index + 1, row - 1, col, 'U');
                this.visited.remove(currentCell);
            }
        }
    }

    private void markSnake(String snake) {

        String flipped = flip(snake);
        String reversed = reverse(snake);
        String flippedReversed = reverse(flipped);
        String reversedFlip = flip(reversed);

        if (!snakes.contains(snake) && !snakes.contains(flipped)
                && !snakes.contains(reversed) && !snakes.contains(flippedReversed) && !snakes.contains(reversedFlip)) {
            System.out.println(snake);
            count++;
            snakes.add(flipped);
            snakes.add(reversed);
            snakes.add(flippedReversed);
            snakes.add(reversedFlip);
        }
    }

    private char[] rotate(char[] snake) {
        while (snake[1] != 'R') {
            for (int i = 0; i < snake.length; i++) {
                switch (snake[i]) {
                    case 'R':
                        snake[i] = 'D';
                        break;
                    case 'L':
                        snake[i] = 'U';
                        break;
                    case 'D':
                        snake[i] = 'L';
                        break;
                    case 'U':
                        snake[i] = 'R';
                        break;
                }
            }
        }
        return snake;
    }

    private String reverse(String snake) {
        char[] reversed = new char[snake.length()];
        reversed[0] = 'S';
        for (int i = snake.length() - 1; i >= 1; i--) {
            reversed[snake.length() - i] = snake.charAt(i);
        }
        return new String(rotate(reversed));
    }

    private String flip(String snake) {
        char[] flipped = new char[snake.length()];
        for (int i = 0; i < snake.length(); i++) {
            if (snake.charAt(i) == 'U') {
                flipped[i] = 'D';
            } else if (snake.charAt(i) == 'D') {
                flipped[i] = 'U';
            } else {
                flipped[i] = snake.charAt(i);
            }
        }
        return new String(rotate(flipped));
    }

    public void loadArguments() {
        Scanner scr = new Scanner(System.in);
        this.n = Integer.parseInt(scr.nextLine());
        this.currentSnake = new char[this.n];
        this.currentSnake[0] = 'S';
    }
}
