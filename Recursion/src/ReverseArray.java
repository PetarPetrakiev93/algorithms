import java.util.Scanner;

public class ReverseArray {

    static String[] tokens;

    public static void main(String[] args){

        Scanner scr = new Scanner(System.in);
        tokens = scr.nextLine().split(" ");
        printReverse(0);
    }

    private static void printReverse(int index){
        if(index >= tokens.length){
            return;
        }

        printReverse(index + 1);

        System.out.print(tokens[index] + " ");
    }
}
