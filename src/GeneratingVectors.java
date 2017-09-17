import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


public class GeneratingVectors {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int index = Integer.parseInt(scr.nextLine());
        Gen01(new int[index], 0);
    }

    public static void Gen01(int[] vector, int index){
        if(index > vector.length - 1){
            System.out.println(Arrays.stream(vector).mapToObj(String::valueOf).collect(Collectors.joining("")));
        }else{
            vector[index] = 0;
            Gen01(vector, index+1);
            vector[index] = 1;
            Gen01(vector, index+1);
        }
    }
}
