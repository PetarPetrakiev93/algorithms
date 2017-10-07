import java.util.Scanner;
import java.util.Stack;

public class TowerOfHonoi {
    private static int stepsTaken = 0;
    static Stack<Integer> start = new Stack<>();
    static Stack<Integer> end = new Stack<>();
    static Stack<Integer> aux = new Stack<>();

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        for(int i = n; i >= 1; i--){
            start.push(i);
        }
        printRods();
        moveDisk(n, start, aux, end);
    }

    private static void moveDisk(int bottomDisk, Stack<Integer> start, Stack<Integer> aux, Stack<Integer> end ){
        if(bottomDisk == 1){
            stepsTaken++;
            end.push(start.pop());
            System.out.printf("Step #%d: Moved disk\n", stepsTaken);
            printRods();
        }else{
            moveDisk(bottomDisk - 1, start, end, aux);
            stepsTaken++;
            end.push(start.pop());
            System.out.printf("Step #%d: Moved disk\n", stepsTaken);
            printRods();
            moveDisk(bottomDisk - 1, aux, start, end);
        }
    }

    private static void printRods(){
        System.out.printf("Source: %s\n", reverse(start));
        System.out.printf("Destination: %s\n", reverse(end));
        System.out.printf("Spare: %s\n", reverse(aux));
        System.out.println();
    }

    private static String reverse(Stack<Integer> stack){
        String temp = "";
        Integer[] arr = new Integer[stack.size()];
        stack.toArray(arr);
        for(int i = 0; i < arr.length; i++){
            temp += arr[i].toString();
            temp += ", ";
        }
        if(temp.length() > 0) {
            return temp.substring(0, temp.length() - 2);
        }else{
            return temp;
        }
    }
}
