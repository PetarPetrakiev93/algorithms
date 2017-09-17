import java.util.Scanner;

/**
 * Created by Home on 7/12/2017.
 */
public class RecursivDrawing {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        PrintFigure(n);
    }

    private static void PrintFigure(int n) {
        if(n <= 0){
            return;
        }

        System.out.println(new String(new char[n]).replace('\0', '*'));

        PrintFigure(n - 1);

        System.out.println(new String(new char[n]).replace('\0', '#'));
    }
}
