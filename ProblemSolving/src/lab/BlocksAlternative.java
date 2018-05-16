package lab;

import java.util.Scanner;

public class BlocksAlternative {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int numberOfLetters = Integer.parseInt(scr.nextLine());
        int numberOfBlocks = (numberOfLetters - 3) *(numberOfLetters - 2) * (numberOfLetters-1) * numberOfLetters/4;
        System.out.println("Number of blocks: " + numberOfBlocks);
        char lastLetter = (char) ('A' + numberOfLetters - 1);
        for (char l1 = 'A'; l1 <= lastLetter; l1++) {
            for (char l2 = (char)(l1+1); l2 <= lastLetter; l2++) {
                for (char l3 = (char)(l1+1); l3 <= lastLetter; l3++) {
                    if(l3 != l2){
                        for (char l4 = (char)(l1+1); l4 <= lastLetter; l4++) {
                            if(l4 != l3 && l4 != l2){
                                System.out.println(new String(new char[]{l1,l2,l3,l4}));
                            }
                        }
                    }
                }
            }
        }
    }
}
