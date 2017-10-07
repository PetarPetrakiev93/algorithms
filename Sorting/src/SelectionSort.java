import java.util.Arrays;
import java.util.stream.Collectors;

public class SelectionSort {
    public static <T extends Comparable> void swap(T[] collection, int from, int to){
        T temp = collection[to];
        collection[to] = collection[from];
        collection[from] = temp;
    }

    public static boolean less(Comparable first, Comparable second)
    {
        return first.compareTo(second) < 0;
    }

    static <T extends Comparable> void sort(T[] collection){
        for(int i = 0; i < collection.length; i++){
            int min = i;
            for(int j = i; j < collection.length; j++){
                if (less(collection[j], collection[min]))
                {
                    min = j;
                }
            }
            swap(collection, i, min);
        }
    }

    public static void main(String[] args){
        Integer[] collection = {3,2,6,9,7,3};
        sort(collection);
        System.out.println(Arrays.stream(collection).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}