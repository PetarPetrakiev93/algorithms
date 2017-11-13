import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Home on 11/14/2017.
 */
public class QuickSort<T extends Comparable> {

    public void sort(T[] arr){
        Shuffle.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    private void sort(T[] arr, int lo, int hi){
        if(lo >= hi){
            return;
        }
        if(hi <= lo){
            return;
        }
        int p = Partition(arr, lo, hi);
        sort(arr, lo, p - 1);
        sort(arr, p + 1, hi);
    }

    private int Partition(T[] arr, int lo, int hi) {
        if(lo >= hi){
            return lo;
        }
        int i = lo;
        int j = hi + 1;
        while (true){
            while (less(arr[++i], arr[lo])){
                if(i == hi){
                    break;
                }
            }
            while (less(arr[lo], arr[--j])){
                if(j==lo){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    public static <T extends Comparable> void swap(T[] collection, int from, int to) {
        T temp = collection[to];
        collection[to] = collection[from];
        collection[from] = temp;
    }

    public static boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }

    public static void main(String[] args){
        Integer[] collection = {3,2,6,9,7,3,-23,-45,65,39};
        QuickSort<Integer> mergeSort = new QuickSort<>();
        mergeSort.sort(collection);
        System.out.println(Arrays.stream(collection).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
