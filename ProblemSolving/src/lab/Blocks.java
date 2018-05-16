package lab;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Blocks {
    private static Set<String> blocks;
    private static Set<String> rotatedBlocks;
    private static char[] letters;
    private static int k = 4;
    private static char[] permutations;
    private static boolean[] used;

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int numberOfBlocks = Integer.parseInt(scr.nextLine());
        generateLetters(numberOfBlocks);
        generateBlocks(0);
        System.out.println("Number of blocks: " + blocks.size());
        for (String block : blocks) {
            System.out.println(block);
        }
    }

    private static void generateLetters(int numberOfBlocks) {
        letters = new char[numberOfBlocks];
        for(int i = 0; i < numberOfBlocks; i++){
            letters[i] = (char)('A' + i);
        }
        permutations = new char[k];
        used = new boolean[numberOfBlocks];
        blocks = new TreeSet<>();
        rotatedBlocks = new HashSet<>();
    }

    private static void generateBlocks(int index){
        if (index >= k) {
            String block = new String(permutations);
            if(!blocks.contains(block) && !rotatedBlocks.contains(block)){
                addBlock(permutations);
                rotateBlock(permutations);
            }
        }else {
            for (int i = 0; i < letters.length; i++)
                if (!used[i]) {
                    used[i] = true;
                    permutations[index] = letters[i];
                    generateBlocks(index + 1);
                    used[i] = false;
                }
        }
    }

    private static void rotateBlock(char[] result){
        rotatedBlocks.add(new String(new char[]{result[3], result[0], result[2], result[1]}));
        rotatedBlocks.add(new String(new char[]{result[2], result[3], result[1], result[0]}));
        rotatedBlocks.add(new String(new char[]{result[1], result[2], result[0], result[3]}));
    }

    private static void addBlock(char[] result){
        blocks.add(new String(result));
    }
}
