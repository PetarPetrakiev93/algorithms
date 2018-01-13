package lab;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MergeSort<T extends Comparable> {
    private T[] aux;
    public void sort(T[] arr){
        this.aux = (T[])new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    private void merge(T[] arr, int lo, int mid, int hi){
        if(less(arr[mid], arr[mid + 1])){
            return;
        }
        for(int index = lo; index < hi + 1; index++){
            this.aux[index] = arr[index];
        }
        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <= hi; k++){
            if(i > mid){
                arr[k] = this.aux[j++];
            }else if(j > hi){
                arr[k] = this.aux[i++];
            }else if(less(this.aux[i], this.aux[j])){
                arr[k] = aux[i++];
            }else{
                arr[k] = aux[j++];
            }
        }
    }

    private void sort(T[] arr, int lo, int hi){
        if(lo >= hi){
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
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
        Integer[] collection = {3,2,6,9,7,3,-23,-45,65,39};
        MergeSort<Integer> mergeSort = new MergeSort<>();
        mergeSort.sort(collection);
        System.out.println(Arrays.stream(collection).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}

