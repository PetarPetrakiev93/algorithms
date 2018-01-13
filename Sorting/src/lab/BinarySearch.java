package lab;

public class BinarySearch<T extends Comparable> {

    public int binarySearchRecursive(T[] arr, T key, int start, int end){
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

    public int binarySearchIterative(T[] arr, T key, int start, int end){
        while (end >= start) {
            int mid = (start + end) / 2;
            if (arr[mid].compareTo(key) < 0)
                start = mid + 1;
            else if (arr[mid].compareTo(key) > 0)
                end = mid - 1;
            else
                return mid;
        }
        return -1;


    }

    public static void main(String[] args){
        Integer[] collection = {3,2,6,9,7,3,-23,-45,65,39};
        QuickSort<Integer> mergeSort = new QuickSort<>();
        mergeSort.sort(collection);
        BinarySearch<Integer> s = new BinarySearch<>();
        System.out.println(s.binarySearchRecursive(collection, 65, 0, collection.length-1));
        System.out.println(s.binarySearchIterative(collection, 2, 0, collection.length-1));
    }
}
