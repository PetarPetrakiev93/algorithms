package judge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class Searching {
    private Integer[] aux;
    public void sort(Integer[] arr){
        this.aux = new Integer[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable> void shuffle(T[] arr){
        Random random = new Random();
        for(int i = 0; i < arr.length; i++){
            int randIndex = random.nextInt(arr.length - i);

            swap(arr, i, randIndex);
        }
    }

    private void merge(Integer[] arr, int lo, int mid, int hi){
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

    private void sort(Integer[] arr, int lo, int hi){
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

    public static <T extends Comparable> int binarySearchRecursive(T[] arr, T key, int start, int end){
        if (end < start)
            return -1;
        else {
            int mid = (start + end) / 2;
            if (arr[mid].compareTo(key) > 0)
                return binarySearchRecursive(arr, key, start, mid - 1);
            else if (arr[mid].compareTo(key) < 0)
                return binarySearchRecursive(arr, key, mid + 1, end);
            else
                return mid;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("\\s+");
        Integer[] collection = new Integer[input.length];
        Integer key = Integer.parseInt(br.readLine());
        for(int i = 0; i < input.length; i++){
            collection[i] = Integer.valueOf(input[i]);
        }
        shuffle(collection);
        new Searching().sort(collection);
        System.out.println(binarySearchRecursive(collection, key, 0, collection.length - 1));
    }
}
