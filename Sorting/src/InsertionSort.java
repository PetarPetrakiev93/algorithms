import java.util.Arrays;
import java.util.stream.Collectors;

public class InsertionSort {
    public static <T extends Comparable> void swap(T[] collection, int from, int to) {
        T temp = collection[to];
        collection[to] = collection[from];
        collection[from] = temp;
    }

    public static boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }

    public static <T extends Comparable> void sort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int prev = i - 1;
            int curr = i;
            while (prev >= 0){
                if(less(arr[prev], arr[curr])){
                    break;
                }
                swap(arr, curr, prev);
                prev--;
                curr--;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] collection = {3, 6, 2, 9, 7, 3, 1};
        sort(collection);
        System.out.println(Arrays.stream(collection).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
