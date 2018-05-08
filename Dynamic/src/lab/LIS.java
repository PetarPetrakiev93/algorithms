package lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LIS {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] seqArgs = scr.nextLine().split("\\s+");
        int[] seq = new int[seqArgs.length];
        for(int i = 0; i < seqArgs.length; i++){
            seq[i] = Integer.parseInt(seqArgs[i]);
        }
        int[] len = new int[seq.length];
        int[] prev = new int[seq.length];
        int maxSolution = 0;
        int maxSolutionIndex = 0;
        for(int i = 0; i < seq.length; i++){
            int solution = 1;
            int currentNumber = seq[i];
            int prevIndex = -1;
            for(int j = 0; j < i; j++){
                int prevNum = seq[j];
                int prevSol = len[j];
                if(currentNumber > prevNum && solution <= prevSol){
                    solution = prevSol + 1;
                    prevIndex = j;
                }
            }
            len[i] = solution;
            prev[i] = prevIndex;
            if(maxSolution < solution){
                maxSolution = solution;
                maxSolutionIndex = i;
            }
        }
        List<Integer> numbers =  new ArrayList<>();
        while (maxSolutionIndex != -1){
            int currentNumber = seq[maxSolutionIndex];
            numbers.add(currentNumber);
            maxSolutionIndex = prev[maxSolutionIndex];
        }

        for(int i = numbers.size() - 1; i >= 0; i--){
            System.out.print(numbers.get(i) + " ");
        }
    }
}
