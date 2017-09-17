import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


public class GeneratingCombinations {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        Integer[] set = Arrays.stream(scr.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        int index = Integer.parseInt(scr.nextLine());
        GenComb(set, new int[index], 0, 0);
    }

    private static void GenComb(Integer[] set, int[] vector, int index, int border){
        if(index >= vector.length){
            System.out.println(Arrays.stream(vector).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }else{
            for(int i = border; i < set.length; i++){
                vector[index] = set[i];
                GenComb(set, vector, index + 1, i + 1);
            }
        }
    }
}
