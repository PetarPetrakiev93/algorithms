package exercises;

import java.util.Scanner;

public class Parentheses {
    static char leftParenthesis = '(';
    static char rightParenthesis = ')';
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        char[] variation = new char[n * 2];
        variations(0, variation, 0, 0);
        System.out.println(result.toString().trim());
    }

    private static void variations(int index, char[] variation, int usedLeft, int usedRight) {
        if(index >= variation.length){
            result.append(new String(variation));
            result.append(System.lineSeparator());
        }

        if(usedLeft < variation.length / 2){
            variation[index] = leftParenthesis;
            variations(index+1, variation, usedLeft+1, usedRight);
        }

        if(usedLeft > usedRight){
            variation[index] = rightParenthesis;
            variations(index+1, variation, usedLeft, usedRight +1);
        }
    }
}
