package lab;

import java.util.Scanner;

public class FibonacciTopDown {
    private long[] memory;

    public FibonacciTopDown(int memorySize) {
        this.memory = new long[memorySize + 1];
    }

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int count = Integer.parseInt(scr.nextLine());
        FibonacciTopDown fibonacciTopDown = new FibonacciTopDown(count);
        System.out.println(fibonacciTopDown.fibonacci(count));
    }

    public long fibonacci(int count){
        if(this.memory[count] != 0){
            return this.memory[count];
        }

        if(count == 1 || count == 2){
            return 1;
        }

        this.memory[count] = fibonacci(count-1) + fibonacci(count-2);

        return this.memory[count];
    }
}
