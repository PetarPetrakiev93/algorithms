package exercises;

import java.util.Scanner;

public class BinomialCoefficient {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        int k = Integer.parseInt(scr.nextLine());
        Memory memory = new Memory(new int[n+1][k+1]);

        int coeff = findBinomCoeff(memory, n, k);

        System.out.println(coeff);
    }

    private static int findBinomCoeff(Memory memory, int n, int k) {
        if(k>n){
            return 0;
        }else if(k == n || k == 0){
            return 1;
        }else if(memory.binom[n][k] == 0){
            memory.binom[n][k] = findBinomCoeff(memory,n-1, k-1) + findBinomCoeff(memory, n - 1, k);
        }

        return memory.binom[n][k];
    }
}

class Memory{
    public int[][] binom;

    public Memory(int[][] binom) {
        this.binom = binom;
    }
}
