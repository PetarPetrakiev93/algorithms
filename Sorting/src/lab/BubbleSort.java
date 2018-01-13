package lab;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BubbleSort {
    public static <T extends Comparable> void sort(T[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++) {
                if (!less(arr[j], arr[j+1])) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static <T extends Comparable> void swap(T[] collection, int from, int to){
        T temp = collection[to];
        collection[to] = collection[from];
        collection[from] = temp;
    }

    public static boolean less(Comparable first, Comparable second)
    {
        return first.compareTo(second) < 0;
    }


    public static void main(String[] args){
        Integer[] collection = {3,2,6,9,7,3};
        sort(collection);
        System.out.println(Arrays.stream(collection).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
